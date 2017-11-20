import java.io.*; 
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class InsuranceServlet extends HttpServlet {

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
                        Map<String, Item> items = (Map)session.getAttribute("items");
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
                              if (users.getLevel() >= 1){
                                    hd += PRODUCT;
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
                        hd +=("<li class=\"\" style=\"float: right\" class=\"iu\"><a href=\"./OrdersServlet\">Orders");
                        if (users != null && users.getOrdersMap() != null ) {
                              hd += "( " + users.getOrdersMap().size() + " )";
                        }
                        hd +=("</a></li>");                        //Write the real content into content




                        pw.print(hd);
                        ct += "<article><h2>Warranty for 6 kinds products</h2></article>";

                        pw.println(ct);
                        pw.println("<article class=\"expanded\"><table>");
                        for (Item item : items.values()){
                              if (item.getItemId().charAt(0) == 'i'){
                                    pw.println("<tr>");
                                    pw.println("<td>" + item.getItemName() + "</td>");
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
                        pw.println("</table></article>");

                        pw.println(sb);
                        pw.println(ft);
            }

}