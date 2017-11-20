import java.io.*; 
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class ProductServlet extends HttpServlet {

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
                        //Write the real content into content
                        String CONTENT = Utilities.PrintProductTable();
                        ct += "<article><h2>Product</h2></article>";
                        ct += CONTENT;
                        pw.println(ct);
                        pw.println("<li> <a href=\"./Additem.html\">Add an Item</a></li>");
                        pw.println("<li> <a href=\"./Removeitem.html\">Remove an Item</a></li>");
                        pw.println("<li> <a href=\"./Updateitem.html\">Update an Item</a></li>");
                        int realnum;
                        // int cnt = 0;                     
                        for (Item item : items.values()) {
                              // if(request.getParameter("stocks")!= null && request.getParameter("ItemId") != null &&request.getParameter("ItemId").equals(item.getItemId())){
                              //       realnum = Integer.parseInt(request.getParameter("stocks"));
                              //       // if (cnt == 0){
                              //       item.setStock(realnum);
                              //       //       cnt ++;
                              //       // } else{
                              //       //       cnt = 0;
                              //       // }
                              // }
                              
                              pw.println("<tr>");
                              pw.println("<td align=\"\">"+ item.getItemId() + "</td>");
                              pw.println("<td align=\"\">"+ item.getItemName() + "</td>");
                              pw.println("<td align=\"\">" + item.getPrice() + "</td>");
                              pw.println("<td align=\"\">"+ item.getDiscount() +"</td>");
                              pw.println("<td align=\"\">"+item.getRebates() +"</td>");
                              // pw.println("<td align=\"\">"+item.getStock()+"</td></tr>");
                              pw.println("<td align=\"\">"+ item.getStock()+ "</td></tr>");
                              // pw.println("<tr>");
                              // pw.println("<td align=\"center\">" + item.getItemName() + "</td>");
                              // // out.println("<td>" + item.getTitle() + "</td>");
                              // pw.println("<td align=\"center\"><input id=\"Price\" class=\"" + item.getPrice() + "\" type=\"text\" size=\"6\" name=\"" + item.getItemId() + "\" value=\"" + String.format("%.2f", item.getPrice()) + "\"/></td>");
                              // pw.println("<td align=\"center\"><input id=\"Discount\" class=\"" + item.getDiscount() + "\" type=\"text\" size=\"3\" name=\"" + item.getItemId() + "\" value=\"" + item.getDiscount() + "\"/></td>");
                              // pw.println("<td align=\"center\"><input id=\"Rebate\" class=\"" + item.getRebates() + "\" type=\"text\" size=\"3\" name=\"" + item.getItemId() + "\" value=\"" + item.getRebates() + "\"/></td>");
                              // pw.println("<td align=\"center\"><input id=\"Stock\" class=\"" + item.getStock() + "\" type=\"text\" size=\"3\" name=\"" + item.getItemId() + "\" value=\"" + item.getStock() + "\"/></td></tr>");

                         }
                        // session.removeAttribute("items");
                        // session.setAttribute("items",items);
                        pw.println("</table></article>");
                        pw.println(sb);
                        pw.println(ft);
            }

}