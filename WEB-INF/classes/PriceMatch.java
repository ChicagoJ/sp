import java.io.*; 
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class PriceMatch extends HttpServlet {

	  private String TAGLOGIN = "<li class=\"right\"><a href=\"./LoginPage\">Login</a></li>";
      private String TAGSIGNUP = "<li class=\"right\"><a href=\"./SignupPage\">Signup</a></li>";
      private String SignupForm = Utilities.PrintSignupForm();
      String PRODUCT = "<li class=\"\"><a href=\"./ProductServlet\">Products</a></li>";
      String NOTLOGIN = "<li style=\"float:right\"><a href=\"./LoginPage\">Login</a></li>";
      String NOTSIGNUP = "<li style=\"float:right\"><a href=\"./SignupPage\">Signup</a></li>";
      String LOGOUT = "<li style=\"float:right\"><a href=\"./LogoutServlet\">Logout</a></li>";
      int numCart;


            protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                        response.setContentType("text/html");
                        PrintWriter pw = response.getWriter();
                        HttpSession session = request.getSession();
                        Map<String, Item> items = MySqlDataStoreUtilities.getItems();
                        User users = (User)session.getAttribute("users");

                        //get HTML content
                        String hd = Utilities.PrintHeader();
                        String ct = Utilities.PrintContent();
                        String sb = Utilities.PrintSidebar();
                        String ft = Utilities.PrintFooter();
                        ShoppingCart cart = (ShoppingCart)session.getAttribute("shoppingCart");
                        
                        if (cart != null){
                              System.out.println("num of item in cart: " + cart.getItemNumber());
                              numCart = cart.getItemNumber();
                        } else{
                              numCart = 0;
                        }
                        if (numCart != 0){
                              hd += "<li style=\"float:right\"><a href=\"./CartPage\">Cart";
                              hd += "(" + numCart + ")";
                              hd +="</a></li>";
                        }else{
                              hd += "<li style=\"float:right\"><a href=\"./CartPage\">Cart</a></li>";
                        }                          //login or notlogin in
                        if (users!= null){
                              // System.out.println(items);
                              // System.out.println("Products Page");
                              hd += "<li style=\"float:right\"><a href=\"#\">Hi ";
                              hd += users.getUserId();
                              if (users.getLevel() > 1){
                                    hd += PRODUCT;
                              }
                              if (users.getLevel() == 1){
                                    hd += "<li style=\"float:right\"><a href=\"./Registration.html\">creat account</a></li>";
                                    hd += "<li style=\"float:right\"><a href=\"./ManagerOrder\">Manager Orders</a></li>";
                              }
                              hd += LOGOUT;

                        } else {
                     //       //add signup and login tag
                              hd += NOTSIGNUP;
                              hd += NOTLOGIN;
                        }
                        hd +=("<li class=\"\" style=\"float: right\" class=\"iu\"><a href=\"./OrdersServlet\">Orders");
                        if (users != null){
                              List<Order> orderList1 = MySqlDataStoreUtilities.getOrder(users.getUserId());
                              if (orderList1 != null ) {
                                    hd += "( " + orderList1.size() + " )";
                              }
                        }
                        hd +=("</a></li>");                        //Write the real content into content




                        pw.print(hd);
                        ct += "<article><h2>We beat our competitors in all aspects,<br>Price-Match Guaranteed</h2><br><hr />";
                        File file = new File(request.getServletContext().getRealPath("/") +"DealMatches.txt");
                        FileReader fileReader = new FileReader(file);
                        BufferedReader bufferedReader = new BufferedReader(fileReader);
                        StringBuffer stringBuffer = new StringBuffer();
                        String line;
                        ArrayList<String> pmnmae = new ArrayList<String>();
                        int count = 0;
                        while ((line = bufferedReader.readLine()) != null) {
                            // stringBuffer.append(line);
                            // stringBuffer.append("\n");
                            // System.out.println(line);
                            ct += line;
                            ct += "<br>";
                            for (Item item : items.values()){
                                if(line.toLowerCase().contains(item.getItemName().toLowerCase())){
                                    System.out.println(item.getItemName());
                                    pmnmae.add(item.getItemName());
                                    count ++;
                                }
                                if (count == 2)
                                    break;
                            }
                            if (pmnmae.size() == 2)
                              break;
                        }
                        fileReader.close();
                        // System.out.println("Contents of file:");
                        // System.out.println(pmnmae);
                        // System.out.println(stringBuffer.toString());
                        ct += "<hr /></article>";



                        pw.println(ct);

                        pw.println("<article class=\"expanded\"><table>");
                        if(pmnmae.size() == 0)
                              pw.println("<tr><td>No offer found</td></tr>");
                        for (String name : pmnmae){
                            System.out.println(name);
                            for (Item item:items.values()){
                                if (name == item.getItemName()){
                                    pw.println("<tr>");
                                    pw.println("<td>" + item.getItemName() + "<br><br>");
                                    pw.println("<form action=\"./WriteReviewPage\">");
                                    pw.println("<input type=\"hidden\" name=\"ItemName\" value=\""+ item.getItemName() + "\">");
                                    pw.println("<input class=\"submit-button\" type=\"submit\" value=\"Write Review\" style=\"background-color:red\">");
                                    pw.println("</form>");
                                    pw.println("<form action=\"./ViewReviewPage\">");
                                    pw.println("<input type=\"hidden\" name=\"ItemName\" value=\""+ item.getItemName() + "\">");
                                    pw.println("<input class=\"submit-button\" type=\"submit\" value=\"View Review\" style=\"background-color:red\">");
                                    pw.println("</form></td>");

                                    pw.println("<td> Price: $" + item.getPrice() + "<br><br>");
                                    pw.println("Discount: " + item.getDiscount() + "<br><br>");
                                    
                                    pw.println("<form action=\"./CartPage\">");
                                    pw.println("<input type=\"hidden\" name=\"method\" value=\"addToCart\">");
                                    pw.println("<input type=\"hidden\" name=\"ItemId\" value=\""+ item.getItemId() + "\">");
                                    pw.println("<input type=\"hidden\" name=\"ItemName\" value=\""+ item.getItemName() + "\">");
                                    pw.println("<input type=\"hidden\" name=\"Stocks\" value=\""+ item.getStock() + "\">");
                                    pw.println("<input class=\"submit-button\" type=\"submit\" value=\"Add to cart\" style=\"background-color:red\">");
                                    pw.println("</form></td></tr>");
                                }
                            }
                        }
                        pw.println("</table></article>");

                        pw.println(sb);
                        pw.println(ft);
            }

}