
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class CheckoutPage extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        String userid = request.getParameter("userid");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        System.out.println((Double)session.getAttribute("TotalPrice"));
        Map<String, Integer> idNum = (Map)session.getAttribute("idNum");
        System.out.println("the id and num is : " + idNum);
		String MESSAGE = Utilities.PrintCheckoutForm();
        showPage(response,MESSAGE);
    } 
    

    protected void showPage(HttpServletResponse response, String message)
    throws ServletException, java.io.IOException {
        response.setContentType("text/html");
        java.io.PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Check out</title>");  
        out.println("</head>");
        out.println("<body>");
        out.println(message);
        out.println( 
             "<FORM ACTION=\"./Home\">\n" +
             "<center><INPUT TYPE=\"SUBMIT\"\n" +
             "       VALUE=\"Continute to shopping\">\n" +
             "</center></FORM>");
        out.println("</body>");
        out.println("</html>");
        out.close();
 
    }
    

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        processRequest(request, response);
    } 


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        processRequest(request, response);
    }
}