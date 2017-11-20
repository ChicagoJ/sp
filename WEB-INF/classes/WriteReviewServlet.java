
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
// import com.mongodb.*;

public class WriteReviewServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
       
        String productCategory = request.getParameter("itemType");
        String productName = request.getParameter("itemName");
        double productPrice = Double.parseDouble(request.getParameter("price"));
        String retailerName = request.getParameter("rName");
        String retailerZip = request.getParameter("zipcode");
        String retailerCity = request.getParameter("city");
        String retailerState = request.getParameter("state");
        String productOnSale = request.getParameter("onSale");
        String manufacturerName = request.getParameter("mName");
        String manufacturerRebate = request.getParameter("rebate");
        String userID = request.getParameter("userId");
        String userAge = request.getParameter("age");
        String userGender = request.getParameter("gender");
        String userOccupation = request.getParameter("userOcc");
        int reviewRating = Integer.parseInt(request.getParameter("rate"));  
        String reviewDate = request.getParameter("reviewDate");
        String reviewText = request.getParameter("comment");
        MongoDBDataStoreUtilities.insertReview(productName, productCategory, productPrice, retailerName,
                                                retailerZip, retailerCity, retailerState, productOnSale,
                                                manufacturerName, manufacturerRebate, userID, userAge,
                                                userGender, userOccupation, reviewRating, reviewDate,
                                                reviewText);
        String message = "Review for " + productName + " stored";
        showPage(response, message);
  
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
        out.println("<CENTER><a href=\"./Home\">Home</a><CENTER>");
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