import java.io.*; 
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class SalesReport extends HttpServlet {

	private String TAGLOGIN = "<li class=\"right\"><a href=\"./LoginPage\">Login</a></li>";
      private String TAGSIGNUP = "<li class=\"right\"><a href=\"./SignupPage\">Signup</a></li>";
      private String SignupForm = Utilities.PrintSignupForm();
      String PRODUCT = "<li class=\"\"><a href=\"./ProductServlet\">Products</a></li>";
      String NOTLOGIN = "<li class=\"right\"><a href=\"./Login.html\">Login</a></li>";
      String NOTSIGNUP = "<li class=\"right\"><a href=\"./Registration.html\">Signup</a></li>";
      String LOGOUT = "<li class=\"right\"><a href=\"./LogoutServlet\">Logout</a></li>";



            protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                        response.setContentType("text/html");
                        PrintWriter pw = response.getWriter();
                        HttpSession session = request.getSession();
                        Map<String, Item> items = MySqlDataStoreUtilities.getItems();
                        // Map<String, Item> items = (Map)session.getAttribute("items");
                        User users = (User)session.getAttribute("users");

                        //get HTML content
                        String hd = Utilities.PrintHeader();
                        String ct = Utilities.PrintContent();
                        String sb = Utilities.PrintSidebar();
                        String ft = Utilities.PrintFooter();
                        

                        //login or notlogin in
                        if (users!= null){
                              // System.out.println("Products Page");
                              // System.out.println(items);
                              hd += "<li style=\"float:right\"><a href=\"#\">Hi ";
                              hd += users.getUserId();
                              if (users.getLevel() > 1){
                                    hd += PRODUCT;
                                    hd += "<li class=\"\"><a href=\"./Inventory\">Inventory</a></li>";
                                    hd += "<li class=\"\"><a href=\"./SalesReport\">SalesReport</a></li>";
                              }
                              if (users.getLevel() == 1){
                                    hd += "<li class=\"right\"><a href=\"./Registration.html\">creat account</a></li>";
                              }
                              hd += LOGOUT;

                        } else {
                     //       //add signup and login tag
                              hd += NOTSIGNUP;
                              hd += NOTLOGIN;
                        }





                        pw.print(hd);
                        ct += "<article><h1>Product Report</h2></article>";
                        // ct += "<li class=\"\"><a href=\"#product infor\">product infor</a></li>";
                        pw.println(ct);
                        pw.println("<table>");
                        pw.println("<tr><form action=\"./ReportServlet\">");
                        pw.println("<td>All sold products information</td>");
                        pw.println("<td>");
                        pw.println("<input type=\"hidden\" name=\"tableType\" value=\"table4\">");
                        pw.println("<input type=\"submit\" value=\"submit\"></td></form></tr>");

                        pw.println("<tr><form action=\"./ReportServlet\">");
                        pw.println("<td>total daily sales transactions</td>");
                        pw.println("<td>");
                        pw.println("<input type=\"hidden\" name=\"tableType\" value=\"table5\">");
                        pw.println("<input type=\"submit\" value=\"submit\"></td></form></tr>");

                        pw.println("<tr><form action=\"./testBar\">");
                        pw.println("<td>the total sales for every product</td>");
                        pw.println("<td>");
                        pw.println("<input type=\"hidden\" name=\"tableType\" value=\"table6\">");
                        pw.println("<input type=\"submit\" value=\"submit\"></td></form></tr></table>");                        
                        pw.println(sb);
                        pw.println(ft);
            }

}