
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class RemoveItemServlet extends HttpServlet {
   
 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        String ItemId = request.getParameter("ItemId");



        if(MySqlDataStoreUtilities.deleteItem(ItemId)){
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
