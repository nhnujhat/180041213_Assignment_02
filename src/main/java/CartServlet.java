import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "CartServlet", value = "/CartServlet")
public class CartServlet extends HttpServlet {
    /*
    CartServlet adds items to cart and allows updating the quantities as well as removing products.
    The id and quantity is fetched from ProductsServlet and added to itemArray which stores users current cart items.
    The uniqueproduct array keeps track of unique items and if an item already exists, only the quantity is updated.
    Manually updating the quantity is also possible from this servlet.
    Checkout redirects to the CheckoutServlet.
    */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();

        String temp = request.getParameter("pcid");
        String pcquantity = request.getParameter("pcquantity");
        if(pcquantity == "") pcquantity = "1";

        ArrayList<Product> productArray = (ArrayList<Product>) session.getAttribute("productArray");
        ArrayList<Item> itemArray = (ArrayList<Item>) session.getAttribute("itemArray");
        ArrayList<String> uniqueproduct = (ArrayList<String>) session.getAttribute("uniqueproduct");

        if(temp != null) {
            String pcid = productArray.get(Integer.parseInt(temp)).getId();
            if(uniqueproduct.contains(pcid))
            {
                for (int i=0; i<itemArray.size(); i++)
                {
                    if (itemArray.get(i).getProduct().getId().equalsIgnoreCase(pcid)) {
                        itemArray.get(i).setQuantity(Integer.parseInt(pcquantity));
                        break;
                    }
                }
            }
            else
            {
                itemArray.add(new Item(new Product(pcid, productArray.get(Integer.parseInt(temp)).getName(), productArray.get(Integer.parseInt(temp)).getPrice()),Integer.parseInt(pcquantity)));
                uniqueproduct.add(pcid);
            }
        }

        PrintWriter out = response.getWriter();
        out.println("<form method = \"post\" action=\"LogoutServlet\"><input type=\"submit\" value = \"Log Out\"> </form>");
        out.println("<form method = \"get\" action=\"ProductsServlet\"><input type=\"submit\" value = \"Products\"> </form>");
        out.println("<h1>Your Cart: </h1>");

        for(int i=0; i<itemArray.size(); i++)
        {
            out.println("<h1>Name: " + itemArray.get(i).getProduct().getName() + "</h1>");
            out.println("<h1>Price: " + itemArray.get(i).getProduct().getPrice() + " BDT</h1>");
            out.println("<h1>Quantity: " + itemArray.get(i).getQuantity() + "</h1>");
            out.println("<form method =\"get\" action=\"UpdateServlet\"> <input type=\"hidden\" name=\"uid\" value="+itemArray.get(i).getProduct().getId()+"> <input type=\"text\" name=\"pcquantity\"/> <input type=\"submit\" value=\"Update Quantity\"/> </form>");
            out.println("<form method =\"get\" action=\"RemoveServlet\"> <input type=\"hidden\" name=\"rid\" value="+itemArray.get(i).getProduct().getId()+"> <input type=\"submit\" value=\"Remove Item\"/> </form>");
        }

        out.println("<form method = \"get\" action=\"CheckoutServlet\"><input type=\"submit\" value = \"Checkout\"> </form>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
