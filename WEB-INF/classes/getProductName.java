import java.io.*; 
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class getProductName extends HttpServlet {


      public static List<String> getNames() {


            // Map<String, Item> items = MySqlDataStoreUtilities.getItems();

            // List<String> name = new ArrayList<String>();                  
            // for (Item item : items.values()) {
            //       name.add(item.getItemName());
            //       // pw.println("<td align=\"\">"+ item.getItemName() + "</td>");
            //  }
            //  return name;
        Map<String, User> users = MySqlDataStoreUtilities.selectUser();

            List<String> name = new ArrayList<String>();                  
            for (User user : users.values()) {
                  name.add(user.getUserId());
                  // pw.println("<td align=\"\">"+ item.getItemName() + "</td>");
             }
             return name;      
       }

      public static void main(String args[]){
            System.out.println(getNames());
      }

}