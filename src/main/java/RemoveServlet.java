import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "RemoveServlet", value = "/RemoveServlet")
public class RemoveServlet extends HttpServlet {
    /*
    Fetches the id of the product to be removed and removes the product from itemArray which stores current cart items.
    */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();

        String temp2 = request.getParameter("rid");

        ArrayList<Item> itemArray = (ArrayList<Item>) session.getAttribute("itemArray");

        if(temp2 != null)
        {
            for (int i=0; i<itemArray.size(); i++)
            {
                if (itemArray.get(i).getProduct().getId().equalsIgnoreCase(temp2)) {
                    itemArray.remove(i);
                    break;
                }
            }
        }

        response.sendRedirect("CartServlet");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
