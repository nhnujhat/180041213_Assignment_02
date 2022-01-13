import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "UpdateServlet", value = "/UpdateServlet")
public class UpdateServlet extends HttpServlet {
    /*
    Updates the quantity of the products.
    */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();

        String temp3 = request.getParameter("uid");
        String pcquantity = request.getParameter("pcquantity");

        ArrayList<Item> itemArray = (ArrayList<Item>) session.getAttribute("itemArray");

        if(temp3 != null)
        {
            for (int i=0; i<itemArray.size(); i++)
            {
                if (itemArray.get(i).getProduct().getId().equalsIgnoreCase(temp3)) {
                    itemArray.get(i).setQuantity(Integer.parseInt(pcquantity));
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
