import java.io.*; 
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class ReportServlet extends HttpServlet {

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
                        String tableType = request.getParameter("tableType");
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

                        if (tableType.equals("table1")){
                            String CONTENT = Utilities.PrintProductTableWithNamePriceStock();
                            ct += CONTENT;
                            pw.println(ct);
                            int realnum;
                            // int cnt = 0; 
                            for (Item item : items.values()) {
                                  pw.println("<tr>");
                                  // pw.println("<td align=\"\">"+ item.getItemId() + "</td>");
                                  pw.println("<td align=\"\">"+ item.getItemName() + "</td>");
                                  pw.println("<td align=\"\">" + item.getPrice() + "</td>");
                                  pw.println("<td align=\"\">"+ item.getStock()+ "</td></tr>");

                             }

                            pw.println("</table></article>");
                            pw.println("<hr />");
                      }else if(tableType.equals("table2")){
                            String CONTENT2 = Utilities.PrintProductTableOnsales();
                            ct += CONTENT2;
                            pw.println(ct);
                            for (Item item : items.values()) {
                                  if(item.getStock()> 0){
                                        pw.println("<tr>");
                                        pw.println("<td align=\"\">"+ item.getItemName() + "</td>");
                                        pw.println("<td align=\"\">" + item.getPrice() + "</td>");
                                        pw.println("<td align=\"\">"+ item.getStock()+ "</td></tr>");
                                  }

                             }                        
                            pw.println("</table></article>");
                            pw.println("<hr />");
                      }else if (tableType.equals("table3")) {
                          
                        

                            String CONTENT3 = Utilities.PrintProductTableRebates();
                            ct += CONTENT3;
                            pw.println(ct);
                            for (Item item : items.values()) {
                                  if(item.getRebates()> 0){
                                        pw.println("<tr>");
                                        pw.println("<td align=\"\">"+ item.getItemName() + "</td>");
                                        pw.println("<td align=\"\">" + item.getPrice() + "</td>");
                                        pw.println("<td align=\"\">"+ item.getStock()+ "</td>");
                                        pw.println("<td align=\"\">"+ item.getRebates()+ "</td></tr>");
                                  }

                         }

                        pw.println("</table></article>");
                        pw.println("<hr />");
                      }else if (tableType.equals("table4")) {
                          
                        

                            String CONTENT4 = Utilities.PrintOrderTable();
                            ct += CONTENT4;
                            pw.println(ct);
                            List<Order> oList = MySqlDataStoreUtilities.getTotalSold();
                            for (Order order : oList) {
                                  
                                  pw.println("<tr>");
                                  pw.println("<td align=\"\">"+ order.getItemName() + "</td>");
                                  pw.println("<td align=\"\">" + items.get(MySqlDataStoreUtilities.getItemId(order.getItemName())).getPrice() + "</td>");
                                  // System.out.println("the item name is " + order.getItemName());
                                  System.out.println("the item is " + MySqlDataStoreUtilities.getItemId(order.getItemName()));
                                  System.out.println();
                                  System.out.println();
                                  pw.println("<td align=\"\">"+ order.getTotalNum()+ "</td>");
                                  pw.println("<td align=\"\">"+ order.getTotalSales()+ "</td></tr>");
                                  

                         }

                         

                        pw.println("</table></article>");
                        pw.println("<hr />");
                      }else if (tableType.equals("table5")) {
                          
                        

                            String CONTENT5 = Utilities.PrintDailySaleTable();
                            ct += CONTENT5;
                            pw.println(ct);
                            List<Order> oList = MySqlDataStoreUtilities.getDaliySales();
                            for (Order order : oList) {
                                  
                                  pw.println("<tr>");
                                  pw.println("<td align=\"\">"+ order.getOTime() + "</td>");
                                  pw.println("<td align=\"\">"+ order.getTotalSales()+ "</td></tr>");
                                  

                         }

                         

                        pw.println("</table></article>");
                        pw.println("<hr />");
                      }



                        pw.println(sb);
                        pw.println(ft);
            }

}