import java.io.*; 
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class Startup extends HttpServlet{
	private static Map<String, Item> items;

	public void init(HttpServletRequest request, HttpServletResponse response) throws ServletException{
              // response.setContentType("text/html");
              // PrintWriter pw = response.getWriter();
              HttpSession session = request.getSession();



              //set product infor
              // items = SaxPaserDataStore.getItems(request.getServletContext().getRealPath("/") + "ProductCatalog.xml");
              // items = MySqlDataStoreUtilities.getItems();
              items = MySqlDataStoreUtilities.getItems();
              session.setAttribute("items",items);
              System.out.println("Products loading...");  
            }

	public void init(){
            System.out.println("Products loading...");

	}

  // protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  //             response.setContentType("text/html");
  //             PrintWriter pw = response.getWriter();
  //             HttpSession session = request.getSession();



  //             //set product infor
  //             items = SaxPaserDataStore.getItems(request.getServletContext().getRealPath("/") + "ProductCatalog.xml");
  //             // items = MySqlDataStoreUtilities.getItems();
  //             items = MySqlDataStoreUtilities.getItems();
  //             session.setAttribute("items",items);
  //             System.out.println("Products loading...");

  // }

	
}