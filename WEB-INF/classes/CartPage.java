

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;


public class CartPage extends HttpServlet {
  
  // ArrayList idList = new ArrayList();
  // Map<String, Integer> idNum = new HashMap<String, Integer>();

  public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
    HttpSession session = request.getSession();
    User users = (User)session.getAttribute("users");
    ShoppingCart cart;
    // Map<String, Item> items = (Map)session.getAttribute("items");
    Map<String, Item> items = MySqlDataStoreUtilities.getItems();
    ArrayList idList = new ArrayList();
    Map<String, Integer> idNum = new HashMap<String, Integer>();

    
    synchronized(session) {
      cart = (ShoppingCart)session.getAttribute("shoppingCart");
      System.out.println("the cart is " + cart);
      // New visitors get a fresh shopping cart.
      // Previous visitors keep using their existing cart.
      if (cart == null) {
        cart = new ShoppingCart();
        session.setAttribute("shoppingCart", cart);
      }
      String itemID = request.getParameter("ItemId");
      System.out.println("Name is " + request.getParameter("ItemName") + "\nid is " + itemID);

      if (itemID != null) {
        String numItemsString =
          request.getParameter("numItems");
          System.out.println("There are " + numItemsString + "in the stock");
        if (numItemsString == null) {
          // If request specified an ID but no number,
          // then customers came here via an "Add Item to Cart"
          // button on a catalog page.
          cart.addItem(itemID);
        } else {
          // If request specified an ID and number, then
          // customers came here via an "Update Order" button
          // after changing the number of items in order.
          // Note that specifying a number of 0 results
          // in item being deleted from cart.1
          int numItems;
          try {
            numItems = Integer.parseInt(numItemsString);
          } catch(NumberFormatException nfe) {
            numItems = 1;
          }
          System.out.println("the real # is " + numItems);
          cart.setNumOrdered(itemID, numItems);
          System.out.println("there are " + cart.getItemsOrdered().size() + " items in the cart");
        }
      }
      // ArrayList nameList = new ArrayList();
      // if (!nameList.contains(request.getParameter("ItemName"))){
      //   nameList.add(request.getParameter("ItemName"));
      // }
      // System.out.println("the namelist is " + nameList);    
    }

    // Whether or not the customer changed the order, show
    // order status.
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    String title = "Status of Your Order";
    String docType =
      "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
      "Transitional//EN\">\n";
    out.println(docType +
                "<HTML>\n" +
                "<HEAD><TITLE>" + title + "</TITLE>");
    out.println("<meta charset=\"utf-8\">"
            + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">"
            + "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">"
            + "<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>"
            + "<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>" 
            + "<style>.carousel-inner > .item > img,.carousel-inner > .item > a > img { width: 70%;margin: auto;}</style>");
    out.println("</HEAD>\n" +
                "<BODY BGCOLOR=\"#FDF5E6\">\n" +
                "<H1 ALIGN=\"CENTER\">" + title + "</H1>");
    synchronized(session) {
      if(users != null){
        List itemsOrdered = cart.getItemsOrdered();
        if (itemsOrdered.size() == 0) {
          out.println("<H2><I>No items in your cart...</I></H2>");
        } else {
          // If there is at least one item in cart, show table
          // of items ordered.
          out.println
            ("<CENTER><H2>Please update all your item before checkout</H2></CENTER><TABLE BORDER=1 ALIGN=\"CENTER\">\n" +
             "<TR BGCOLOR=\"#FFAD00\">\n" +
             "  <TH>Name<TH>Discount\n" +
             "  <TH>Unit Cost<TH>Number<TH>Total Cost");
          ItemOrder order;
          // Rounds to two decimal places, inserts dollar
          // sign (or other currency symbol), etc., as
          // appropriate in current Locale.
          NumberFormat formatter =
            NumberFormat.getCurrencyInstance();
          // For each entry in shopping cart, make
          // table row showing ID, description, per-item
          // cost, number ordered, and total cost.
          // Put number ordered in textfield that user
          // can change, with "Update Order" button next
          // to it, which resubmits to this same page
          // but specifying a different number of items.
          int cnt = 0;
          double totalcost = 0;
          for(int i=0; i<itemsOrdered.size(); i++) {
            order = (ItemOrder)itemsOrdered.get(i);
            // System.out.println("the item is " + order.getItem());
            cnt += (int)order.getNumItems();
            totalcost += order.getTotalCost();
            // System.out.println("total cost is :" + totalcost);
            // session.setAttribute("TotalPrice",totalcost);

            if (!idList.contains(order.getItemName())){
              
              idList.add(order.getItemName());
              idNum.put(order.getItemName(),order.getNumItems());
            } else{
              idNum.put(order.getItemName(),order.getNumItems());
            }



            System.out.println(idNum);



            // System.out.println("the namelist is " + nameList);

            out.println
              ("<TR>\n" +
               "  <TD>" + order.getItemName() + "\n" +
               "  <TD>" + order.getDiscount() + "\n" +
               "  <TD>" +
               formatter.format(order.getCost()) + "\n" +
               "  <TD>" +
               "<FORM >\n" +  // Submit to current URL
               "<INPUT TYPE=\"HIDDEN\" NAME=\"ItemId\"\n" +
               "       VALUE=\"" + order.getItemId() + "\">\n" +
               "<INPUT TYPE=\"TEXT\" NAME=\"numItems\"\n" +
               "       SIZE=3 VALUE=\"" + 
               order.getNumItems() + "\">\n" +
               "<SMALL>\n" +
               "<INPUT TYPE=\"SUBMIT\"\n "+
               "       VALUE=\"Update Order\">\n" +
               "</SMALL>\n" +
               "</FORM>\n" +
               "  <TD>" +
              formatter.format(order.getTotalCost()) );
              System.out.println(order.getNumItems());
              System.out.println(order.getTotalCost());
          }
          session.setAttribute("idNum", idNum);
          session.setAttribute("TotalPrice",totalcost);
          System.out.println("total cost is :" + totalcost);
          // System.out.println("the total count is " + cnt);
          // session.setAttribute("itemcnt",cnt);
          out.println("</TABLE><br><br><article class=\"expanded\"><CENTER><h2>The total item cost is " + formatter.format(totalcost) + "</h2><CENTER></article><br><br>");
          String checkoutURL =
            response.encodeURL("./CheckoutPage");
          // "Proceed to Checkout" button below table
          out.println
            ( 
             "<FORM ACTION=\"" + checkoutURL + "\">\n" +
             "<BIG><CENTER>\n" +
             "<INPUT TYPE=\"SUBMIT\"\n" +
             "       VALUE=\"Proceed to Checkout\">\n" +
             "</CENTER></BIG>"+
             "</FORM>");

        }
        out.println( 
             "<FORM ACTION=\"./Home\">\n" +
             "<BIG><CENTER>\n" +
             "<INPUT TYPE=\"SUBMIT\"\n" +
             "       VALUE=\"Continute to shopping\">\n" +
             "</CENTER></BIG>"+
             "</FORM>");
        String itemID = request.getParameter("ItemId");

        if (itemID != null){
          out.println("<div id=\"myCarousel\" class=\"carousel slide\" data-ride=\"carousel\">"
              + "<ol class=\"carousel-indicators\">"
              + "<li data-target=\"#myCarousel\" data-slide-to=\"0\" class=\"active\"></li>"
              + "<li data-target=\"#myCarousel\" data-slide-to=\"1\"></li>" 
              + "<li data-target=\"#myCarousel\" data-slide-to=\"2\"></li>" 
              + "<li data-target=\"#myCarousel\" data-slide-to=\"4\"></li>" 
              + "<li data-target=\"#myCarousel\" data-slide-to=\"5\"></li>" 
              + "<li data-target=\"#myCarousel\" data-slide-to=\"6\"></li></ol>" 
              + "<div class=\"carousel-inner\" role=\"listbox\">");         
      }
        int cCarousel = 0;
        String carousel = "";
        String accessoryName = "";
        String accessoryId;

        for (Item item: items.values()) {
          if (itemID != null){
            if(item.getItemId().charAt(0) == itemID.charAt(0) && item.getItemId().charAt(1) == 'a'){
              if(cCarousel == 0){
                carousel += "<div class=\"item active\">";
              } else{
                carousel +="<div class=\"item\">";
              }
              cCarousel++;
              carousel += "<img src=\"./images/image.jpg\" alt=\"Chania\" width=\"460\" height=\"345\">";
              carousel += "<div class=\"carousel-caption\"><h3>";
              //get accessory name and id
              carousel += item.getItemName();
              carousel += "</h3>";
              carousel += "<form action=\"./CartPage\">"; 
              carousel += "<input type=\"hidden\" name=\"method\" value=\"addToCart\">";
              carousel += "<input type=\"hidden\" name=\"ItemId\" value=\""+ item.getItemId() + "\">";
              carousel += "<input type=\"hidden\" name=\"ItemName\" value=\""+ item.getItemName() + "\">";
              carousel += "<input type=\"hidden\" name=\"Stocks\" value=\""+ item.getStock() + "\">";
              carousel += "<input class=\"submit-button\" type=\"submit\" value=\"Add to cart\" style=\"background-color:red\">";
              carousel += "</form>";
              carousel +="</div></div>";
            }
          }
        }
          // out.println(carousel);

          // out.println("<a class=\"left carousel-control\" href=\"#myCarousel\" role=\"button\" data-slide=\"prev\">" 
          // + "<span class=\"glyphicon glyphicon-chevron-left\" aria-hidden=\"true\"></span>"
          // + "<span class=\"sr-only\">Previous</span></a>" 
          // + "<a class=\"right carousel-control\" href=\"#myCarousel\" role=\"button\" data-slide=\"next\">"
          // + "<span class=\"glyphicon glyphicon-chevron-right\" aria-hidden=\"true\"></span>" 
          // + "<span class=\"sr-only\">Next</span></a></div></div>");
        if (itemID != null){
          out.println(carousel);

          out.println("<a class=\"left carousel-control\" href=\"#myCarousel\" role=\"button\" data-slide=\"prev\">" 
          + "<span class=\"glyphicon glyphicon-chevron-left\" aria-hidden=\"true\"></span>"
          + "<span class=\"sr-only\">Previous</span></a>" 
          + "<a class=\"right carousel-control\" href=\"#myCarousel\" role=\"button\" data-slide=\"next\">"
          + "<span class=\"glyphicon glyphicon-chevron-right\" aria-hidden=\"true\"></span>" 
          + "<span class=\"sr-only\">Next</span></a></div></div>");        }


        out.println("</BODY></HTML>");
      } else{


        out.println("<h2>Login first</h2>");
        out.println("<li><a href=\"./Home\">Home</a></li>");
        out.println("</body>");
        out.println("</html>");
        out.close();      
      }
    }
  }
}
