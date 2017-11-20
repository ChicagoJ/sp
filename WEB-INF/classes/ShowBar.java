
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.mongodb.*;


public class ShowBar extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        response.setContentType("text/html");
        java.io.PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        Map<String, Item> items = MySqlDataStoreUtilities.getItems();






        out.println("<html>");
        out.println("<head>");
        out.println("<title>DataTable</title>");  
        out.println("<script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>");
        // hd += "<script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>";
        out.println("<script type=\"text/javascript\">");
        // hd += "<script type=\"text/javascript\">";
        // hd += "google.charts.load('current', {'packages':['corechart']});";
        out.println("google.charts.load('current', {'packages':['corechart']});");
        // hd += "google.charts.setOnLoadCallback(drawBarChart);";
        out.println("google.charts.setOnLoadCallback(drawBarChart);");
        // hd += "function drawBarChart(){";
        out.println("function drawBarChart(){");
        // hd += "var data = new google.visualization.DataTable()";
        out.println("var data = new google.visualization.DataTable();");
        // hd += "data.addColumn('string', 'ProductName');";
        out.println("data.addColumn('string', 'ProductName');"
                    + "data.addColumn('number', 'Stocks');"
                    + "data.addRows([");
        // hd += "data.addColumn('number', 'Stocks');";
        // hd += "data.addRows([";

        for (Item item: items.values()){
            out.println("['" + item.getItemName() + "'," + item.getStock() + "],");
        }

        // hd += "]);";
        out.println("]);");
        // hd += "var chart = new google.visualization.BarChart(document.getElementById(\"barchart_values\"))";
        out.println("var options = {title:'the total number of items available for every product',"
                    + "width:800,"
                    + "height:800};");


        out.println("var chart = new google.visualization.BarChart(document.getElementById(\"barchart_values\"))");
        // hd += "chart.draw(data,null)}";
        out.println("chart.draw(data,options)}"
                    + "</script>");
        // hd += "</script>";
        // out.println(hd);
        out.println("</head>");
        out.println("<body>");
        // out.println(message);
        out.println("<h1>Bar<br><br><hr>");
        out.println("<div id=\"barchart_values\" ></div>");



        out.println( 
             "<FORM ACTION=\"./Home\">\n" +
             "<center><INPUT TYPE=\"SUBMIT\"\n" +
             "       VALUE=\"Home\">\n" +
             "</center></FORM>");
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