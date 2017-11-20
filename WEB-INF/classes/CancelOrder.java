
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class CancelOrder extends HttpServlet {
    String CANCELED = "Order Canceled!";
    String NOTCANCELED = "ERROR:Order not Canceled, Please check your confirmation ID again";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        HttpSession session = request.getSession();
        User users = (User)session.getAttribute("users");
        String uId = request.getParameter("userId");
        String ItemNumber = request.getParameter("ItemNumber");
        int itemNumber = Integer.parseInt(ItemNumber);
        String itemName = request.getParameter("itemName");
        int stocks = MySqlDataStoreUtilities.getStock(itemName);
        stocks = stocks + itemNumber;
        String cId = request.getParameter("cId");
        List<String> realCIdList = MySqlDataStoreUtilities.getCId(uId,itemName);
        System.out.println(realCIdList + " and the cid is " + cId);
        if(!realCIdList.contains(cId)){
            showPage(response,NOTCANCELED);
        } else{
            // users.cancelOrder(orderId);
            MySqlDataStoreUtilities.deleteOrder(cId, itemName);
            MySqlDataStoreUtilities.updateItem(itemName,stocks);
            showPage(response, CANCELED);
        }

    } 
    

    protected void showPage(HttpServletResponse response, String message)
    throws ServletException, java.io.IOException {
        response.setContentType("text/html");
        java.io.PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Check out</title>");  
        out.println("</head>");
        out.println("<body>");
        out.println(message);
        out.println("<CENTER><a href=\"./Home\">Home</a><CENTER>");
        out.println("</body>");
        out.println("</html>");
        out.close();
 
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