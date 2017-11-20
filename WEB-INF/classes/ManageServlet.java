
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ManageServlet extends HttpServlet {
   
 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        String ItemId = request.getParameter("ItemId");
        String ItemName = request.getParameter("ItemName");
        String pricestr = request.getParameter("price");
        String discountstr = request.getParameter("discount");
        String rebatestr = request.getParameter("rebate");
        String stockstr = request.getParameter("stock");
        String accessories = request.getParameter("accessories");
        
        double price = Double.parseDouble(pricestr);
        double discount = Double.parseDouble(discountstr);
        double rebate = Double.parseDouble(rebatestr);
        int stock = Integer.parseInt(stockstr);

        if(MySqlDataStoreUtilities.addItem(ItemId, ItemName, price, discount, rebate, stock, accessories)){
            response.sendRedirect(request.getContextPath()+"/ProductServlet");
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
