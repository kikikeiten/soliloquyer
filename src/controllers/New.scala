package controllers

import java.io.IOException
import javax.servlet.RequestDispatcher
import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import models.Message

@WebServlet("/new")
@SerialVersionUID(1L)
class NewServlet()

  extends Nothing {
  @throws[ServletException]
  @throws[IOException]
  protected def doGet(request: Nothing, response: Nothing): Unit = {
    request.setAttribute("_token", request.getSession.getId)
    request.setAttribute("message", new Message)

    val rd = request.getRequestDispatcher("/WEB-INF/views/messages/new.jsp")

    rd.forward(request, response)
  }
}
