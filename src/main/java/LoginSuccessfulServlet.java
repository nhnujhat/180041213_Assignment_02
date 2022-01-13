import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class LoginSuccessfulServlet extends HttpServlet {
    /*
    On successful login, welcome message is shown.
    ProductsServlet, CartServlet can be viewed from here.
    Logout ends the current session.
    */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String user = null;
        HttpSession session = request.getSession();

        if (session.getAttribute("currentUser") == null)
        {
            response.sendRedirect("index.html");
        }
        else
        {
            user = session.getAttribute("currentUser").toString();
        }

        PrintWriter out = response.getWriter();
        out.println("<h1>Welcome " + user + "</h1>");
        out.println("<form method = \"get\" action=\"ProductsServlet\"><input type=\"submit\" value = \"Products\"> </form>");
        out.println("<form method = \"get\" action=\"CartServlet\"><input type=\"submit\" value = \"Your Cart\"> </form>");
        out.println("<form method = \"post\" action=\"LogoutServlet\"><input type=\"submit\" value = \"Log Out\"> </form>");
    }
}