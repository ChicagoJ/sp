import java.io.*; 
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class Home extends HttpServlet {
		 String PRODUCT = "<li class=\"\"><a href=\"./ProductServlet\">Products</a></li>";
             String INVENTORY = "<li class=\"\"><a href=\"./Inventory\">Inventory</a></li>";
	       String NOTLOGIN = "<li style=\"float:right\"><a href=\"./LoginPage\">Login</a></li>";
      	 String NOTSIGNUP = "<li style=\"float:right\"><a href=\"./SignupPage\">Signup</a></li>";
		 String LOGOUT = "<li style=\"float:right\"><a href=\"./LogoutServlet\">Logout</a></li>";
		 String CONTENT = "<article><h2>Welcome to Smart Portables</h2></article><article class=\"expanded\">Happy shoping at Smart Portables!</article>";
		 private static Map<String, Item> items;
             int numCart;

            protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                        response.setContentType("text/html");
                        PrintWriter pw = response.getWriter();
                        HttpSession session = request.getSession();
                        //get user infor
                        User users = (User)session.getAttribute("users");
                        // get cart info & cart num info


                        //set product infor
                        items = SaxPaserDataStore.getItems(request.getServletContext().getRealPath("/") + "ProductCatalog.xml");
                        // items = MySqlDataStoreUtilities.getItems();
				// items = MySqlDataStoreUtilities.getItems();
                        session.setAttribute("items",items);
                        //get HTML content
                        String hd = Utilities.PrintHeader();
                        String ct = Utilities.PrintContent();
                        String sb = Utilities.PrintSidebar();
                        String ft = Utilities.PrintFooter();
                        

                        //login or notlogin in
                        if (users!= null){
                        	System.out.println(users.getUserId() + " Login!");
                              hd += LOGOUT;
                        	hd += "<li style=\"float:right\"><a href=\"#\">Hi ";
                        	hd += users.getUserId();
                              hd +="</a>";
                        	if (users.getLevel() > 1){
                        		hd += PRODUCT;
                                    hd += INVENTORY;
                                    hd += "<li class=\"\"><a href=\"./SalesReport\">SalesReport</a></li>";
                        	}
                        	if (users.getLevel() == 1){
                        		hd += "<li style=\"float:right\"><a href=\"./Registration.html\">creat account</a></li>";
                                    hd += "<li style=\"float:right\"><a href=\"./ManagerOrder\">Manager Orders</a></li>";

                        	}

                        } else {
                     //    	//add signup and login tag
	                        hd += NOTSIGNUP;
	                        hd += NOTLOGIN;
                    	}
                        hd +=("<li class=\"\" style=\"float: right\" class=\"iu\"><a href=\"./OrdersServlet\">Orders");
                        // if (users != null && users.getOrdersMap() != null ) {
                        //       hd += "( " + users.getOrdersMap().size() + " )";
                        // }
                        if (users != null){
                              List<Order> orderList1 = MySqlDataStoreUtilities.getOrder(users.getUserId());
                              if (orderList1 != null ) {
                                    hd += "( " + orderList1.size() + " )";
                              }
                        }
                        hd +=("</a></li>");                        //Write the real content into content
                        // ct = ct.replace("##CONTENT##", CONTENT);
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
                        }                        
                        ct += CONTENT;
                        pw.print(hd);
                        pw.print(ct);
                        pw.print(sb);
                        pw.print(ft);
            }

}