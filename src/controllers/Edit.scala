import models.Message
import utils.DBUtil

import scala.sys.process.processInternal.IOException

@WebServlet("/edit")
@SerialVersionUID(1L)
class Edit()

  extends Nothing {
  @throws[ServletException]
  @throws[IOException]
  protected def doGet(request: Nothing, response: Nothing): Unit = {
    val em = DBUtil.createEntityManager
    val m = em.find(classOf[Message], request.getParameter("id").toInt)

    em.close
    request.setAttribute("message", m)
    request.setAttribute("_token", request.getSession.getId)

    if (m != null) request.getSession.setAttribute("message_id", m.getId)

    val rd = request.getRequestDispatcher("/WEB-INF/views/messages/edit.jsp")

    rd.forward(request, response)
  }
}
