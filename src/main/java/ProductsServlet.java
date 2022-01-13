import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "ProductsServlet", value = "/ProductsServlet")
public class ProductsServlet extends HttpServlet {
    /*
    Shows list of products from productArray.
    Quantity of product can be inserted.
    Add to cart option redirects to CartServlet.
    */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();

        ArrayList<Product> productArray = (ArrayList<Product>) session.getAttribute("productArray");

        PrintWriter out = response.getWriter();
        out.println("<form method = \"post\" action=\"LogoutServlet\"><input type=\"submit\" value = \"Log Out\"> </form>");
        out.println("<form method = \"get\" action=\"CartServlet\"><input type=\"submit\" value = \"Your Cart\"> </form>");
        out.println("<h1>Products: </h1>");
        for(int i=0;i< productArray.size();i++)
        {
            out.println("<h1>ID: " + productArray.get(i).getId() + "</h1>");
            out.println("<h1>Name: " + productArray.get(i).getName() + "</h1>");
            out.println("<h1>Price: " + productArray.get(i).getPrice() + " BDT</h1>");
            out.println("<form method =\"get\" action=\"CartServlet\"> <input type=\"hidden\" name=\"pcid\" value="+i+"> <input type=\"text\" name=\"pcquantity\"/> <input type=\"submit\" value=\"Add To Cart\"/> </form>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
