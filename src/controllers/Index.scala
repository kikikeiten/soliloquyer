package controllers

import java.io.IOException
import java.util
import javax.persistence.EntityManager
import javax.servlet.RequestDispatcher
import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import models.Message
import utils.DBUtil

import java.util.List

@WebServlet("/index")
@SerialVersionUID(1L)
class Index()

  extends Nothing {
  @throws[ServletException]
  @throws[IOException]
  protected def doGet(request: Nothing, response: Nothing): Unit = {
    val em = DBUtil.createEntityManager
    var page = 1
    try page = request.getParameter("page").toInt
    catch {
      case e: NumberFormatException =>

    }
    val messages = em.createNamedQuery("getAllMessages", classOf[Message]).setFirstResult(15 * (page - 1)).setMaxResults(15).getResultList
    val messages_count = em.createNamedQuery("getMessagesCount", classOf[Long]).getSingleResult.asInstanceOf[Long]
    em.close

    request.setAttribute("messages", messages)
    request.setAttribute("messages_count", messages_count)

    request.setAttribute("page", page)

    if (request.getSession.getAttribute("flush") != null) {
      request.setAttribute("flush", request.getSession.getAttribute("flush"))
      request.getSession.removeAttribute("flush")
    }
    val rd = request.getRequestDispatcher("/WEB-INF/views/messages/index.jsp")
    rd.forward(request, response)
  }
}
