
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class AddOrderServlet extends HttpServlet {
   
 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        String uId = request.getParameter("uId");
        String ItemName = request.getParameter("itemName");
        String oId = request.getParameter("oId");
        String itemNumberstr = request.getParameter("itemNumber");
        String rebatestr = request.getParameter("rebate");
        String cId = request.getParameter("cId");
        // String origItemNumberstr = request.getParameter("origItemNumber");

        int itemNumber = Integer.parseInt(itemNumberstr);
        // int origItemNumber = Integer.parseInt(origItemNumberstr);

        if(MySqlDataStoreUtilities.addOrder(uId, oId, ItemName, itemNumber, cId)){
            
            int stocks = MySqlDataStoreUtilities.getStock(ItemName);
            stocks = stocks - itemNumber;
            MySqlDataStoreUtilities.updateItem(ItemName, stocks);
            response.sendRedirect(request.getContextPath()+"/ManagerOrder");
        }
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
