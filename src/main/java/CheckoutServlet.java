import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "CheckoutServlet", value = "/CheckoutServlet")
public class CheckoutServlet extends HttpServlet {
    /*
    CheckoutServlet fetches the itemArray and uniqueproduct array from CartServlet and calculates the total amount.
    After calculating the amount, cart is emptied.
    If the cart is already empty, it doesn't show any amount.
    */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();

        ArrayList<Item> itemArray = (ArrayList<Item>) session.getAttribute("itemArray");
        ArrayList<String> uniqueproduct = (ArrayList<String>) session.getAttribute("uniqueproduct");

        if(!itemArray.isEmpty())
        {
            int cnt = 0;
            for (int i=0; i<itemArray.size(); i++)
            {
                cnt = cnt + (Integer.parseInt(itemArray.get(i).getProduct().getPrice()) * itemArray.get(i).getQuantity());
            }

            itemArray.clear();
            uniqueproduct.clear();

            PrintWriter out = response.getWriter();
            out.println("<h1>Order is Successfully Placed</h1>");

            out.println("<h1>Cash on Delivery Amount: " + cnt +"BDT</h1>");
            out.println("<form method = \"get\" action=\"ProductsServlet\"><input type=\"submit\" value = \"Continue Shopping\"> </form>");
        }
        else
        {
            PrintWriter out = response.getWriter();
            out.println("<h1>Your Cart is Empty</h1>");
            out.println("<form method = \"get\" action=\"ProductsServlet\"><input type=\"submit\" value = \"Buy Something\"> </form>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
