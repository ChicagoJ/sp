
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LoginServlet extends HttpServlet {
   
    // private Map<String, User> users = Utilities.getAllUsers();
    // private Map<String, User> users = new HashMap<String, User>();
    Map<String, User> users = MySqlDataStoreUtilities.selectUser();
            // users = MySqlDataStoreUtilities.selectUser();
    // public void init() {
    //             // users = MySqlDataStoreUtilities.selectUser();
    //             User manager = new User("manager","111",2);
    //             users.put("manager", manager);
    //             User Joey = new User("Joey","111",0);
    //             users.put("Joey", Joey);
    //             User sales = new User("sales","111",1);
    //             users.put("sales",sales);   
    //         }


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        String userid = request.getParameter("userid");
        String password = request.getParameter("password");
        
        Map<String, User> users = MySqlDataStoreUtilities.selectUser();

        HttpSession session = request.getSession();
        System.out.println("the userid from database is " + users.get(userid) );
        // System.out.println("the user level is " + users.get(userid).getLevel());

        if(userid != null && userid.length() != 0) {
            userid = userid.trim();
        }
        if(password != null && password.length() != 0) {
            password = password.trim();
        }
        if(userid != null &&
            password != null) {
                if(users.get(userid)!=null){
                    String realpassword = (String)users.get(userid).getPassword();
                    System.out.println("the realpassword is " + realpassword + " the real userid is " + userid);

                    if(realpassword != null &&
                        realpassword.equals(password)) {
                        showPage(response, "Login Success!");
                        session.setAttribute("users",users.get(userid));
                    } else {
                        showPage(response, "Login Failure! Username or password is incorrect");
                    }
                }
                else {
                    showPage(response,"Login Failure! You must regist first!");
                }

        }  
        else {
            showPage(response, "Login Failure!  You must supply a username and password");
        }
    } 
    

    protected void showPage(HttpServletResponse response, String message)
    throws ServletException, java.io.IOException {
        response.setContentType("text/html");
        java.io.PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Result</title>");  
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>" + message + "</h2>");
        out.println("<li><a href=\"./Home\">Home</a></li>");
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
