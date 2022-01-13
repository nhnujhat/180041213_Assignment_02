import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;

public class LoginServlet extends HttpServlet {
    /*
    Http session is used for login.
    productArray is hard coded in this servlet.
    All the necessary arrays are initialized.
    */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String pw = request.getParameter("pw");
        ArrayList<Product> productArray = new ArrayList<>();
        ArrayList<Item> itemArray = new ArrayList<Item>();
        ArrayList<String> uniqueproduct = new ArrayList<>();

        productArray.add(new Product("1","Bombay Sweets Potato Crackers","10"));
        productArray.add(new Product("2","Pran Mango Juice","15"));
        productArray.add(new Product("3","Ruchi BBQ Chanachur","35"));
        productArray.add(new Product("4","Bashundhara Facial Tissue","40"));
        productArray.add(new Product("5","Lux soap","20"));

        if(username.equals("nujhat") && pw.equals("1234"))
        {
            HttpSession session = request.getSession();
            session.setAttribute("currentUser", username);

            session.setAttribute("productArray", productArray);
            session.setAttribute("itemArray", itemArray);
            session.setAttribute("uniqueproduct", uniqueproduct);
            session.setMaxInactiveInterval(30*60);

            RequestDispatcher rd = request.getRequestDispatcher("LoginSuccessfulServlet");
            rd.forward(request,response);
        }
        else
        {
            response.sendRedirect("index.html");
        }
    }
}