package controllers

import java.io.IOException
import javax.persistence.EntityManager
import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import models.Message
import utils.DBUtil

@WebServlet("/destroy")
@SerialVersionUID(1L)
class Destroy()

  extends Nothing {
  @throws[ServletException]
  @throws[IOException]
  protected def doPost(request: Nothing, response: Nothing): Unit = {
    val _token = request.getParameter("_token")

    if (_token != null && _token == request.getSession.getId) {
      val em = DBUtil.createEntityManager
      val m = em.find(classOf[Message], request.getSession.getAttribute("message_id").asInstanceOf[Integer])
      em.getTransaction.begin
      em.remove(m)
      em.getTransaction.commit
      request.getSession.setAttribute("flush", "削除が完了しました。")
      em.close
      request.getSession.removeAttribute("message_id")
      response.sendRedirect(request.getContextPath + "/index")
    }
  }
}
