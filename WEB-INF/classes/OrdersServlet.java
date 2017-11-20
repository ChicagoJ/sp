import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.*;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.text.*;

public class OrdersServlet extends HttpServlet {
	
	  private String TAGLOGIN = "<li class=\"right\"><a href=\"./LoginPage\">Login</a></li>";
      private String TAGSIGNUP = "<li class=\"right\"><a href=\"./SignupPage\">Signup</a></li>";
      private String SignupForm = Utilities.PrintSignupForm();
      String PRODUCT = "<li class=\"\"><a href=\"./ProductServlet\">Products</a></li>";
      String NOTLOGIN = "<li style=\"float:right\"><a href=\"./LoginPage\">Login</a></li>";
      String NOTSIGNUP = "<li style=\"float:right\"><a href=\"./SignupPage\">Signup</a></li>";
      String LOGOUT = "<li style=\"float:right\"><a href=\"./LogoutServlet\">Logout</a></li>";
      int numCart;    

      protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    	throws ServletException, java.io.IOException {
            response.setContentType("text/html");
            PrintWriter pw = response.getWriter();
            HttpSession session = request.getSession();
            // Map<String, Item> items = (Map)session.getAttribute("items");
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
        List<Order> orderList1 = MySqlDataStoreUtilities.getOrder(users.getUserId());
        if (orderList1 != null ) {
				hd += "( " + orderList1.size() + " )";
			}
		    hd +=("</a></li>");




            pw.print(hd);
            ct += "<article><h2>Orders</h2></article>";

            pw.println(ct);
            pw.println("<article class=\"expanded\">");
            pw.println("<article class=\"expanded\"><table>");
            pw.println("<tr><td align=\"center\">username</td>");
            pw.println("<td align=\"center\">Name</td><td align=\"center\">Amount</td><td align=\"center\">Confirmation number</td><td align=\"center\">Confirmation name</td><td>Cancel</td></tr>");
            // List<Order> orderList = MySqlDataStoreUtilities.getOrder(users.getUserId());

            if ( orderList1 != null){
              for ( Order order : orderList1){
                pw.println("<form action=\"./CancelOrder\" method=\"post\">");
                pw.println("<tr>");
                // pw.println("<td align=\"center\">" + order.getUserId() + "</a></td>");
                
                pw.println("<td>");
                pw.println("<input type=\"text\" name=\"userId\"><br><br>");
                pw.println( order.getUserId() + "</td>");                

                pw.println("<td align=\"center\">" + order.getItemName() + "</a></td>");
                pw.println("<td>");
                pw.println("<input type=\"text\" size=\"10\"name=\"ItemNumber\"><br><br>");
                pw.println( order.getItemNumber() + "</td>");                 
                pw.println("<td>");
                // pw.println("<input type=\"hidden\" name=\"userId\" value=\""+ order.getUserId() +"\" />");
                // pw.println("<input type=\"hide\" name=\"itemName\" value=\""+ order.getItemName() +"\" />");
                pw.println("<input type=\"text\" size=\"30\" name=\"cId\"  /><br /><br>");
                pw.println(order.getConformationId() + "</td>");
                pw.println("<td>");
                pw.println("<input type=\"\" name=\"itemName\"><br><br>");
                pw.println( order.getItemName() + "</td>");
                pw.println("<td><input class=\"submit-button\" type=\"submit\" value=\"Cancel\" style=\"background-color:red\" "
                    + "onclick='return confirm(\"Cancel the order?\")'></td></form>");

              }            
          }







 //            pw.println("<article class=\"expanded\"><table>");
 //            pw.println("<tr><td align=\"center\">Last Time to Cancel</td>");
 //            pw.println("<td align=\"center\">Total Cost</td><td>cancel number</td></tr>");
 //            // pw.println("</table></article>");
	// 		if(users != null && users.getOrdersMap() != null) {
	// 			// sort by order's time
	// 			List<Order> orderList = new ArrayList<Order>(users.getOrdersMap().values());
	// //	        List<Map.Entry<String, Order>> list = new ArrayList<Map.Entry<String, Order>>(user.getOrdersMap().entrySet());
		        
	// 			DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
	// 	        for (Order order : orderList) {
				
	// //			for (Order order : user.getOrders()) {
	// 				pw.println("<tr>");
					
	// //				String time = (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DATE) + "/" + cal.get(Calendar.YEAR);
	// 				pw.println("<td align=\"center\">" + order.getFormattedOrderTime() + "</a></td>");
	// 				pw.println("<td align=\"center\">$" + String.format("%.2f", order.getPrice()) + "</td>");
					
	// 				Calendar endCancelTime = order.getEndCancelTime();
	// 				Calendar now = Calendar.getInstance();
					
	// 				if (now.before(endCancelTime)) {
	// 					pw.println("<td>");
	// 					pw.println("<form action=\"./CancelOrder\" method=\"post\">");
	// 					// out.println("<input type=\"hidden\" name=\"flag\" value=\"" + flag + "\">");
	// 					pw.println("<input type=\"hidden\" name=\"orderId\" value=\"" + order.getOrderId() + "\">");
	// 					pw.println("<input type=\"text\" size=\"30\" name=\"cId\" placeholder=\"Confirmation ID to cancel\" />");
	// 					pw.println("<input class=\"submit-button\" type=\"submit\" value=\"Cancel\" style=\"background-color:red\" "
	// 							+ "onclick='return confirm(\"Cancel the order?\")'>");
	// 					pw.println("</form>");
	// 					pw.println("</td>");
	// 				} 
	// 				else {
	// 					pw.println("<td>Cancel deadline past.</td>");
	// 				}
	// 				pw.println("</tr>");
	// 			}
 //    }
			else {
				pw.println("<tr><td colspan=\"6\">No past orders found</td></tr>");
			} // end if
			
			pw.println("</table></article>");
            

            pw.println(sb);
            pw.println(ft);
      // session.removeAttribute("idNum");
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

