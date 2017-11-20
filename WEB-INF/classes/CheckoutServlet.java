import java.io.*;
import java.util.*;
import java.util.regex.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CheckoutServlet extends HttpServlet {
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String cNum = request.getParameter("cardnum");
		String ads = request.getParameter("address");

		PrintWriter pw = response.getWriter();
		HttpSession session = request.getSession();

		User users = (User)session.getAttribute("users");
		//set Order ID
		String OrderId = users.getUserId() + Math.random()+ "";
		Random random = new Random(System.currentTimeMillis());
		String conformationId = Math.abs(random.nextInt()) + "";
		//get Order time
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Calendar orderTime = Calendar.getInstance();
		System.out.println(df.format(orderTime.getTime()));		
		//get cart item
        ShoppingCart cart = (ShoppingCart)session.getAttribute("shoppingCart");
		// if (cart != null) {
	    List itemsOrdered = cart.getItemsOrdered();
	        // ItemOrder order;
	        //   for(int i=0; i<itemsOrdered.size(); i++) {
	        //     order = (ItemOrder)itemsOrdered.get(i);
	        // 	System.out.println("item id: " + order.getItemId());
	        // 	System.out.println("item Name: " + order.getItemName());aa
	        // 	System.out.println("item number: " + order.getNumItems());
        	// }
		// }
        Order order = new Order(OrderId, userId, orderTime, conformationId);
        order.setItemsOrdered(itemsOrdered);
        double totalcost = 0;
        ItemOrder iorder;
        Map<String, Double> nameCost = new HashMap<String,Double>();
        for(int i=0; i<itemsOrdered.size(); i++) {
            iorder = (ItemOrder)itemsOrdered.get(i);
            nameCost.put(iorder.getItemName(),iorder.getTotalCost());
            totalcost += iorder.getTotalCost();
        }
        System.out.println("total cost is :" + totalcost);
        System.out.println("cost for each product :" + nameCost.values());
        System.out.println("cost name for each product :" + nameCost.keySet());
        order.setPrice(totalcost);
        users.addToOrders(order);
        // cart.clearCart();
		pw.println("<html><head><meta charset=\"UTF-8\"><title>Order successfully placed! </title></head>");
		pw.println("<body>");
		pw.println("<h2>ATTENTION: Please keep this number for your record.</h2><br>Your order's confirmation id is: <b>"+ conformationId + "<b><br>");
		pw.println("<br><br>You package will arrive in 14 days.<br><br>");
		orderTime.add(Calendar.DATE, 14);
		pw.println("<table><tr><td>Estimated Delivery</td><td>" + (orderTime.get(Calendar.MONTH) + 1) + "/" + orderTime.get(Calendar.DATE) + "/" + orderTime.get(Calendar.YEAR) + "</td></tr>");
		
		orderTime.add(Calendar.DATE, -5);
		order.setEndCancelTime(orderTime);
		pw.println("<tr><td>Last day to cancel order</td><td>" + (orderTime.get(Calendar.MONTH) + 1) + "/" + orderTime.get(Calendar.DATE) + "/" + orderTime.get(Calendar.YEAR) + "</td></tr></table>");	
        pw.println( 
             "<FORM ACTION=\"./Home\">\n" +
             "<INPUT TYPE=\"SUBMIT\"\n" +
             "       VALUE=\"Continute to shopping\">\n" +
             "</FORM>");
  		// pw.println("<a href=\"./OrdersServlet\">View your orders</a>");
		String homeURL = "./OrdersServlet";
		pw.println("<FORM ACTION=\"" + homeURL + "\">\n" +
               	   "<INPUT TYPE=\"HIDDEN\" NAME=\"orderId\"\n" +
                   "       VALUE=\"" + OrderId + "\">\n" +
                   "<INPUT TYPE=\"SUBMIT\"\n" +
                   "       VALUE=\"View your orders\">\n" +
             	   "</FORM>");
		pw.println("</body></html");
        Map<String, Integer> idNum = (Map)session.getAttribute("idNum");
        orderTime = Calendar.getInstance();
        String oTime = (orderTime.get(Calendar.MONTH)+1) + "/" + orderTime.get(Calendar.DATE) + "/" + orderTime.get(Calendar.YEAR);
        for (String itemId: idNum.keySet()){
        	// System.out.println(users.getUserId() + " " + OrderId + " " + itemId+ " " +idNum.get(itemId)+ " " + conformationId);
        	// System.out.println("the total sales for " + itemId + " is " + nameCost.get(itemId));
        	MySqlDataStoreUtilities.insertOrder(users.getUserId(), OrderId, itemId, idNum.get(itemId), conformationId, oTime, nameCost.get(itemId));
        	int stocks = MySqlDataStoreUtilities.getStock(itemId);
        	stocks = stocks - idNum.get(itemId);
        	MySqlDataStoreUtilities.updateItem(itemId, stocks);
        }



        session.removeAttribute("idNum");
		idNum = (Map)session.getAttribute("idNum");        
		if (idNum != null){
            System.out.println("idnum is null now");
        }
        cart.clearCart();
 	
    }

}