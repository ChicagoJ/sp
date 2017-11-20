
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.ajax.*;

public class ComposerPage extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {

        // Composer composer = (Composer)request.getParameter("composer");
        String itemId = request.getParameter("id");
        Map<String, Item> items = MySqlDataStoreUtilities.getItems();
        Item item = items.get(itemId);
        String MESSAGE = "";
        MESSAGE += "<table><tr>Product Information</th></tr><tr><td>Product Name: </td>";
        MESSAGE += "<td>" + item.getItemName() + "</td></tr>";
        MESSAGE += "<tr><td>Product Price: </td><td>" + item.getPrice() + "</td></tr>";
        MESSAGE += "<tr><td>Product Discount: </td><td>" + item.getDiscount() + "</td></tr>";
        
        String iType = "";
        String url = "";
        switch(itemId.charAt(0)){
            case 'p':
                iType += "Phone";
                url += "./PhoneServlet";
                break;
            case 'l':
                iType += "Laptop";
                url += "./LaptopServlet";
                break;
            case 'h':
                iType += "HeadPhone";
                url += "./HeadPhoneServlet";
                break;
            case 'w':
                iType += "Smart Watch";
                url += "./WatchServlet";
                break;
            case 's':
                iType += "Speakers";
                url += "./SpeakersServlet";
                break;
            case 'e':
                iType += "External Storage";
                url += "./EStorageServlet";
                break;
        }

        MESSAGE += "<tr><td>Product Category: </td><td>" + iType + "</td></tr>";

        MESSAGE += "<tr>";
        MESSAGE += "<td>";
        MESSAGE += "<form action=\"./WriteReviewPage\">";
        MESSAGE += "<input type=\"hidden\" name=\"ItemName\" value=\""+ item.getItemName() + "\">";
        MESSAGE += "<input class=\"submit-button\" type=\"submit\" value=\"Write Review\" style=\"background-color:red\">";
        MESSAGE += "</form></td><td>";
        MESSAGE += "<form action=\"./ViewReviewPage\">";
        MESSAGE += "<input type=\"hidden\" name=\"ItemName\" value=\""+ item.getItemName() + "\">";
        MESSAGE += "<input class=\"submit-button\" type=\"submit\" value=\"View Review\" style=\"background-color:red\">";
        MESSAGE += "</form></td>";

        MESSAGE += "<td>";
        MESSAGE += "<form action=\""; 
        MESSAGE += url; 
        MESSAGE += "\">";
        MESSAGE += "<input class=\"submit-button\" type=\"submit\" value=\"itemlist\" style=\"background-color:red\">";
        MESSAGE += "</form></td>";


        // MESSAGE += "<td><form action=\"./CartPage\">";
        // MESSAGE += "<input type=\"hidden\" name=\"method\" value=\"addToCart\">";
        // MESSAGE += "<input type=\"hidden\" name=\"ItemId\" value=\""+ item.getItemId() + "\">";
        // MESSAGE += "<input type=\"hidden\" name=\"ItemName\" value=\""+ item.getItemName() + "\">";
        // MESSAGE += "<input type=\"hidden\" name=\"Stocks\" value=\""+ item.getStock() + "\">";
        // MESSAGE += "<input class=\"submit-button\" type=\"submit\" value=\"Add to cart\" style=\"background-color:red\">";
        // MESSAGE += "</form></td></tr>";
        MESSAGE += "</tr></table>";



        showPage(response,MESSAGE);
    } 
    

    protected void showPage(HttpServletResponse response, String message)
    throws ServletException, java.io.IOException {
        response.setContentType("text/html");
        java.io.PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Item information</title>");  
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