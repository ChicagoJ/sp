
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class SignupServlet extends HttpServlet {
   
    // private Map<String, User> users = Utilities.getAllUsers();
    private Map<String, User> users = new HashMap<String, User>();
 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        // MySqlDataStoreUtilities.selectUser(users);
        users = MySqlDataStoreUtilities.selectUser();
        String userid = request.getParameter("userid");
        String password = request.getParameter("password");
        String repassword = request.getParameter("repassword");

        HttpSession session = request.getSession();

        if(userid != null && userid.length() != 0) {
            userid = userid.trim();
        }
        if(password != null && password.length() != 0) {
            password = password.trim();
        }
        if(repassword != null && repassword.length() != 0) {
            repassword = repassword.trim();
        }        


        if(userid != null &&
            password != null && repassword != null) {
            if(!users.containsKey(userid)){
                if (!password.equals(repassword)){
                    showPage(response,"Sign up Failure! Twice passwords are not the same!");
                }
                else {
                    showPage(response,"Sign up Successed! Enjoy Your time at Smart Portables!");
                    User newUser = new User(userid, password, 0);
                    users.put(userid,newUser);
                    MySqlDataStoreUtilities.insertUser(userid,password,"0");
                }                
            } 
            else {
                showPage(response, "Sign up Failure! User already exits");
            }
        } 
        else {
            showPage(response,"Sign up Failure! You must supply a username and password");
        }

    } 
    

    protected void showPage(HttpServletResponse response, String message)
    throws ServletException, java.io.IOException {
        response.setContentType("text/html");
        java.io.PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Login Servlet Result</title>");  
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
