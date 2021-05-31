package controllers

import java.io.IOException
import java.sql.Timestamp
import java.util
import javax.persistence.EntityManager
import javax.servlet.RequestDispatcher
import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import models.Message
import models.validators.MessageValidator
import utils.DBUtil

import java.util.List

@WebServlet("/update")
@SerialVersionUID(1L)
class Update()

  extends Nothing {
  @throws[ServletException]
  @throws[IOException]
  protected def doPost(request: Nothing, response: Nothing): Unit = {

    val _token = request.getParameter("_token")

    if (_token != null && _token == request.getSession.getId) {
      val em = DBUtil.createEntityManager
      val m = em.find(classOf[Message], request.getSession.getAttribute("message_id").asInstanceOf[Integer])
      val title = request.getParameter("title")
      m.setTitle(title)
      val content = request.getParameter("content")
      m.setContent(content)
      val currentTime = new Timestamp(System.currentTimeMillis)
      m.setUpdated_at(currentTime)

      val errors = MessageValidator.validate(m)

      if (errors.size > 0) {
        em.close
        request.setAttribute("_token", request.getSession.getId)
        request.setAttribute("message", m)
        request.setAttribute("errors", errors)
        val rd = request.getRequestDispatcher("/WEB-INF/views/messages/edit.jsp")
        rd.forward(request, response)
      }
      else {
        em.getTransaction.begin
        em.getTransaction.commit
        request.getSession.setAttribute("flush", "更新が完了しました。")
        em.close
        request.getSession.removeAttribute("message_id")
        response.sendRedirect(request.getContextPath + "/index")
      }
    }
  }
}
