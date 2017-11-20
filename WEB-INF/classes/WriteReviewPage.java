
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class WriteReviewPage extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        String itemName = request.getParameter("ItemName");
        System.out.println("the name is : " + itemName);
		String message = "";
        message += "<h1>Review";
        message +="<br><center><h4>Product: ";
        message += itemName;
        message +="<center><form method=\"post\" action=\"./WriteReviewServlet\">";
        message +="<table cellpadding='2' cellspacing='1'>";
        message +="<tr><td>ProductModelName</td><td><input type=\"TEXT\" size=\"15\" name=\"itemName\"></input></td></tr>";
        message +="<tr><td>ProductCategory</td><td><input type=\"radio\" name=\"itemType\" value=\"Phone\" cheked/> Phone &nbsp";
        message += "<input type=\"radio\" name=\"itemType\" value=\"Laptop\" /> Laptop &nbsp";
        message += "<input type=\"radio\" name=\"itemType\" value=\"HeadPhone\" /> HeadPhone &nbsp";
        message += "<input type=\"radio\" name=\"itemType\" value=\"SmartWatch\" /> SmartWatch &nbsp";
        message += "<input type=\"radio\" name=\"itemType\" value=\"Speakers\" /> Speakers &nbsp";
        message += "<input type=\"radio\" name=\"itemType\" value=\"Storage\" /> Storage";
        message +="</td></tr>";
        message +="<tr><td>ProductPrice</td><td><input type=\"TEST\" size=\"30\" name=\"price\"/></td></tr>";
        message +="<tr><td>RetailerName</td><td><input type=\"radio\" name=\"rName\" value=\"SmartPortables\"/> SmartPortables</td></tr>";
        message +="<tr><td>RetailerZip</td><td><input type=\"TEST\" size=\"30\" name=\"zipcode\"/></td></tr>";
        message +="<tr><td>RetailerCity</td><td><input type=\"TEST\" size=\"30\" name=\"city\"/></td></tr>";
        message +="<tr><td>RetailerState</td><td><input type=\"TEST\" size=\"30\" name=\"state\"/></td></tr>";

        message +="<tr><td>ProductOnSale</td><td><input type=\"radio\" name=\"onSale\" value=\"yes\"/> Yes &nbsp";
        message +="<input type=\"radio\" name=\"onSale\" value=\"no\"/> No";
        message +="</td></tr>";

        message +="<tr><td>ManufacturerName</td><td><input type=\"TEST\" size=\"30\" name=\"mName\"/></td></tr>";
        
        message +="<tr><td>ManufacturerRebate</td><td><input type=\"radio\" name=\"rebate\" value=\"yes\"/> Yes &nbsp";
        message +="<input type=\"radio\" name=\"rebate\" value=\"no\"/> No";
        message +="</td></tr>";

        message +="<tr><td>UserID</td><td><input type=\"TEST\" size=\"30\" name=\"userId\"/></td></tr>";
        message +="<tr><td>UserAge</td><td><input type=\"TEST\" size=\"30\" name=\"age\"/></td></tr>";

        message +="<tr><td>UserGender</td><td><input type=\"radio\" name=\"gender\" value=\"male\" cheked /> Male &nbsp";
        message += "<input type=\"radio\" name=\"gender\" value=\"female\" /> Female &nbsp";
        message += "<input type=\"radio\" name=\"gender\" value=\"other\" /> Other";        
        message +="</td></tr>";


        message +="<tr><td>UserOccupation</td><td><input type=\"TEST\" size=\"30\" name=\"userOcc\"/></td></tr>";
        message +="<tr><td>ReviewRating</td><td><input type=\"TEST\" size=\"30\" name=\"rate\"/></td></tr>";
        message +="<tr><td>ReviewDate</td><td><input type=\"date\" name=\"rdate\"/></td></tr>";
        message +="<tr><td>ReviewText</td><td><textarea rows=\"4\" cols=\"50\" name=\"comment\"></textarea></td></tr>";
        message +="</table><center><input type=\"submit\" value=\"Confirm\" /></center>";
        message +="</form></center>";
        
        showPage(response,message);
    } 
    

    protected void showPage(HttpServletResponse response, String message)
    throws ServletException, java.io.IOException {
        response.setContentType("text/html");
        java.io.PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Write Review</title>");  
        out.println("</head>");
        out.println("<body>");
        out.println(message);
        out.println( 
             "<FORM ACTION=\"./Home\">\n" +
             "<center><INPUT TYPE=\"SUBMIT\"\n" +
             "       VALUE=\"Home\">\n" +
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