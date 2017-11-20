import java.lang.*;
import java.util.*;
import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;


public class Utilities {
    public static final String TOMCAT_HOME = "/Users/junyipeng/apache-tomcat-7.0.34/webapps/hw/";
    private static HashMap<String, User> users = new HashMap<String, User>();

    public static String PrintHeader(){
        String str = "";
        str += "<!doctype html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />";
        str += "<title>Welcome to Smart Portables</title>";
        str += "<link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" />";
        str += "<meta name=\"viewport\" content=\"width=device-width, minimum-scale=1.0, maximum-scale=1.0\" />";
        str += "<script type=\"text/javascript\" src=\"javascript.js\"></script>";
        str += "</head>";
        str += "<body onload=\"init()\">";
        

        str += "<div name=\"autofillform\">";
        str += "<table border=\"0\" cellpadding=\"5\"> ";
        str += "<tbody><tr><td><strong>Products search:</strong></td><td>";
        str += "<input type=\"text\"size=\"40\" id=\"complete-field\" onkeyup=\"doCompletion()\"></td></tr>";
        str += "<tr><td id=\"auto-row\" colspan=\"2\"><table id=\"complete-table\" class=\"popupBox\" /></td></tr></tbody></table></div>";
        

        str +="<div id=\"container\">";
        str += "<header><div class=\"width\"><h1><a href=\"./Home\">Smart<span>Portables</span></a></h1></div></header>";
        str += "<nav><div class=\"width\"><ul><li class=\"start selected\"><a href=\"./Home\">Home</a></li>";
        str += "<li class=\"\"><a href=\"./PriceMatch\">PriceMatch</a></li>";
        // str += "<li style=\"float:right\"><a href=\"./CartPage\">Cart</a></li>";

    
        return str;

    }

    public static String PrintContent(){
        String str = "";
        str += "</ul></div></nav>";
        str += "<div id=\"body\" class=\"width\">";
        str += "<section id=\"content\">";
        // str += "<article><h2>Welcome to Smart Portables</h2></article>";
        // str += "<article>##Content</article></section>";
        // str += "##CONTENT##";
        return str;
    }

    public static String PrintSidebar(){
        String str = "";
        str += "</section>";
        str += "<aside class=\"sidebar\">";
        str += "<ul>";
        //categories
        str += "<li><h4>Categories</h4><ul>";
        str += "<li><a href=\"./PhoneServlet\">Phone</a></li>";
        str += "<li><a href=\"./LaptopServlet\">Laptop</a></li>";
        str += "<li><a href=\"./HeadPhoneServlet\">HeadPhone</a></li>";
        str += "<li><a href=\"./WatchServlet\">SmartWatch</a></li>";
        str += "<li><a href=\"./SpeakersServlet\">Speakers</a></li>";
        str += "<li><a href=\"./EStorageServlet\">External Storage</a></li></ul></li>";
        // Trending page
        str += "<li><h4>Trending</h4><ul>";
        str += "<li><a href=\"./TrendPage\">Trend</a></li></ul></li>";

        // str += "<li><a href=\"./InsuranceServlet\">Warranty</a></li></ul></li>";
        //search site
        // str += "<li><h4>Search site</h4><ul>";
        // str += "<li class=\"text\">";
        // str += "<form method=\"get\" class=\"searchform\" action=\"#\">";
        // str += "<p><input type=\"text\" size=\"31\" value=\"\" name=\"s\" class=\"s\"/>";
        // str += "</p></form></li></ul></li>";  
        str += "</ul></aside><div class=\"clear\"></div></div>";
        return str;
    }

    public static String PrintFooter(){
        String str = "";
        str += "<footer><div class=\"footer-content width\">";
        str += "<ul><li><h4>About us</h4></li>";
        str += "<li><a href=\"#\">CSP584 Page</a></li>";
        str += "<li><a href=\"https://www.pengjunyi.com/\">AmazingJunyi</a></li></ul>";
        
        str += "<ul><li><h4>Support</h4></li>";
        str += "<li><a href=\"#\">Online suppoty</a></li>";
        str += "<li><a href=\"#\">Email</a></li></ul>";
        
        str += "<ul class=\"endfooter\"><li><h4>Location</h4></li>";
        str += "<li><a href=\"#\">Find a Storage</a></li>";
        str += "<li><a href=\"#\">Web map</a></li></ul>";

        str += "<div class=\"clear\"></div></div>";
        str += "<div class=\"footer-bottom\">";
        str += "<p>&copy; CSP584 by Peng Junyi</p></div>";
        str += "</footer></div></body></html>";
        return str;

    }

    public static String PrintLoginForm(){
        String str = "";
        str += "<article><h4>Login</h4>";
        str += "<center><form method=\"post\" action=\"./LoginServlet\">";
        str += "<table cellpadding='2' cellspacing='1'>";
        str += "<tr><td>User ID</td><td><input type=\"TEXT\" size=\"15\" name=\"userid\"></input></td></tr>";
        str += "<tr><td>Password</td><td><input type=\"password\" size=\"15\" name=\"password\"style=\"background-color:#FFF\"/></td></tr>";
        str += "<tr><td colspan='2'><center><input type=\"submit\" value=\"sumbit\"style=\"background-color:#FFF\" /></center></td></tr>";
        str += "</table></form></center></article>";
        return str;
    }

    public static String PrintSignupForm(){
        String str = "";
        str += "<h4>Registration</h4>";
        str += "<center><form method=\"post\" action=\"./SignupServlet\">";

        str += "<table cellpadding='2' cellspacing='1'>";
        str += "<tr><td>User ID</td><td><input type=\"TEXT\" size=\"15\" name=\"userid\"></input></td></tr>";
        str += "\n<tr><td>Password</td><td><input type=\"PASSWORD\" size=\"15\" name=\"password\"/></td></tr>";
        str += "<tr><td>Re-enter Password</td><td><input type=\"PASSWORD\" size=\"15\" name=\"repassword\"/></td></tr>";
        str += "<tr><td colspan='2'><center><input type=\"submit\" value=\"submit\" /></center></td></tr>";
        str += "</table></form></center>";
        return str;
    }

    public static String PrintProductTable(){
        String str = "";
        str += "<article class=\"expanded\"><table><tr>";
        str += "<td align=\"\">Id</td>";
        str += "<td align=\"\">Name</td>";
        str += "<td align=\"\">Price</td>";
        str += "<td align=\"\">Discount</td>";
        str += "<td align=\"\">Rebate</td>";
        str += "<td align=\"\">Stock</td></tr>";


        return str;
    }    
    public static String PrintProductTableWithNamePriceStock(){
        String str = "";
        str += "<article class=\"expanded\"><h3>All products information</h3><hr /><table><tr>";
        // str += "<td align=\"\">Id</td>";
        str += "<td align=\"\">Name</td>";
        str += "<td align=\"\">Price</td>";
        // str += "<td align=\"\">Discount</td>";
        // str += "<td align=\"\">Rebate</td>";
        str += "<td align=\"\">Stock</td></tr>";


        return str;
    }    

    public static String PrintProductTableOnsales(){
        String str = "";
        str += "<article class=\"expanded\"><h3>Products on sales(where there are more than one in stotcks for each product)</h3><hr /><table><tr>";
        // str += "<td align=\"\">Id</td>";
        str += "<td align=\"\">Name</td>";
        str += "<td align=\"\">Price</td>";
        // str += "<td align=\"\">Discount</td>";
        // str += "<td align=\"\">Rebate</td>";
        str += "<td align=\"\">Stock</td></tr>";


        return str;
    }
    public static String PrintProductTableRebates(){
        String str = "";
        str += "<article class=\"expanded\"><h3>Products where has manufacturer Rebates(1:yes, 0:no, where rebates = 1 )</h3><hr /><table><tr>";
        // str += "<td align=\"\">Id</td>";
        str += "<td align=\"\">Name</td>";
        str += "<td align=\"\">Price</td>";
        // str += "<td align=\"\">Discount</td>";
        str += "<td align=\"\">Stock</td>";
        str += "<td align=\"\">Rebate</td></tr>";


        return str;
    }    
    public static String PrintOrderTable(){
        String str = "";
        str += "<article class=\"expanded\"><h3>All sold products information</h3><hr /><table><tr>";
        // str += "<td align=\"\">Id</td>";
        str += "<td align=\"\">Name</td>";
        str += "<td align=\"\">Price</td>";
        // str += "<td align=\"\">Discount</td>";
        str += "<td align=\"\">Number Sold</td>";
        str += "<td align=\"\">Total Sales</td></tr>";


        return str;
    }
    public static String PrintDailySaleTable(){
        String str = "";
        str += "<article class=\"expanded\"><h3>All sold products information</h3><hr /><table><tr>";
        // str += "<td align=\"\">Id</td>";
        str += "<td align=\"\">Dates</td>";
        str += "<td align=\"\">Total Sales</td></tr>";


        return str;
    }    
    public static String PrintCheckoutForm(){
        String str = "";
        str +="<center><form method=\"post\" action=\"./CheckoutServlet\">";
        str +="<h4>Enter your User ID and Card number and Address and click Confirm</h4><table cellpadding='2' cellspacing='1'>";
        str +="<tr><td>User ID</td><td><input type=\"TEXT\" size=\"15\" name=\"userId\"></input></td></tr>";
        str +="<tr><td>Card number</td><td><input type=\"TEST\" size=\"15\" name=\"cardnum\"/></td></tr>";
        str +="<tr><td>Address</td><td><input type=\"TEST\" size=\"30\" name=\"address\"/></td></tr>";
        str +="</table><center><input type=\"submit\" value=\"Confirm\" /></center>";
        str +="</form></center>";
        return str;
    }


    public static HashMap<String, User> getAllUsers() {
        return users;
    }



}