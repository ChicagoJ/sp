import java.io.*; 
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class testBar extends HttpServlet {

	private String TAGLOGIN = "<li class=\"right\"><a href=\"./LoginPage\">Login</a></li>";
      private String TAGSIGNUP = "<li class=\"right\"><a href=\"./SignupPage\">Signup</a></li>";
      private String SignupForm = Utilities.PrintSignupForm();
      String PRODUCT = "<li class=\"\"><a href=\"./ProductServlet\">Products</a></li>";
      String NOTLOGIN = "<li class=\"right\"><a href=\"./Login.html\">Login</a></li>";
      String NOTSIGNUP = "<li class=\"right\"><a href=\"./Registration.html\">Signup</a></li>";
      String LOGOUT = "<li class=\"right\"><a href=\"./LogoutServlet\">Logout</a></li>";



            protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                        response.setContentType("text/html");
                        PrintWriter pw = response.getWriter();
                        HttpSession session = request.getSession();
                        Map<String, Item> items = MySqlDataStoreUtilities.getItems();
                        String tableType = request.getParameter("tableType");
                        // Map<String, Item> items = (Map)session.getAttribute("items");
                        User users = (User)session.getAttribute("users");

                        //get HTML content
                        String hd = "";
                        String ct = Utilities.PrintContent();
                        String sb = Utilities.PrintSidebar();
                        String ft = Utilities.PrintFooter();
                        
                        hd += "<!doctype html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />";
                        hd += "<title>Chart</title>";
                        hd += "<link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" />";
                        hd += "<meta name=\"viewport\" content=\"width=device-width, minimum-scale=1.0, maximum-scale=1.0\" />";
                        hd += "<script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>";
                        hd += "<script type=\"text/javascript\">";
                        hd += "google.charts.load('current', {'packages':['corechart']});";
                        hd += "google.charts.setOnLoadCallback(drawBarChart);";
                        hd += "function drawBarChart(){";
                        hd += "var data = new google.visualization.DataTable();";
                        
                        if(tableType.equals("table4")){

                              hd += "data.addColumn('string', 'ProductName');";
                              hd += "data.addColumn('number', 'Stocks');";
                              hd += "data.addRows([";
                              for (Item item: items.values()){
                                    hd += "['" + item.getItemName() + "'," + item.getStock() + "],";
                              }
                              hd += "]);";



                              hd += "var options = {title:'the total number of items available for every product',";
                              hd += "width:800,";
                              // hd += "height:800};";
                              

                              hd += "height:800,";
                              hd += "vAxis:{format:'decimal'}};";

                              hd += "var chart = new google.visualization.BarChart(document.getElementById(\"barchart_values\"));";
                              hd += "chart.draw(data,options);}";
                              hd += "</script></head>";
                        }else if(tableType.equals("table6")){
                              hd += "data.addColumn('string', 'ProductName');";
                              hd += "data.addColumn('number', 'Price');";
                              hd += "data.addRows([";
                              List<Order> oList = MySqlDataStoreUtilities.getTotalSold();
                              for (Order order : oList) {                                
                                  hd += "['" + order.getItemName() + "'," + order.getTotalSales() + "],";                     
                              }                              
                              hd += "]);";



                              hd += "var options = {title:'the total sales for every product',";
                              hd += "width:800,";
                              // hd += "height:800};";
                              hd += "height:800,";
                              hd += "vAxis:{format:'currency'}};";

                              hd += "var chart = new google.visualization.BarChart(document.getElementById(\"barchart_values\"));";
                              hd += "chart.draw(data,options);}";
                              hd += "</script></head>";                        
                        }
                        




                        hd += "<body><div id=\"container\">";
                        hd += "<header><div class=\"width\"><h1><a href=\"./Home\">Smart<span>Portables</span></a></h1></div></header>";
                        hd += "<nav><div class=\"width\"><ul><li class=\"start selected\"><a href=\"./Home\">Home</a></li>";
                        hd += "<li class=\"\"><a href=\"#\">Categories</a></li>";                        //login or notlogin in
                        if (users!= null){
                              // System.out.println("Products Page");
                              // System.out.println(items);
                              hd += "<li style=\"float:right\"><a href=\"#\">Hi ";
                              hd += users.getUserId();
                              if (users.getLevel() > 1){
                                    hd += PRODUCT;
                                    hd += "<li class=\"\"><a href=\"./Inventory\">Inventory</a></li>";
                                    hd += "<li class=\"\"><a href=\"./SalesReport\">SalesReport</a></li>";

                              }
                              if (users.getLevel() == 1){
                                    hd += "<li class=\"right\"><a href=\"./Registration.html\">creat account</a></li>";
                              }
                              hd += LOGOUT;

                        } else {
                     //       //add signup and login tag
                              hd += NOTSIGNUP;
                              hd += NOTLOGIN;
                        }





                        pw.print(hd);
                        ct += "<article><h1>Product Report</h2></article>";
                        // ct += "<li class=\"\"><a href=\"#product infor\">product infor</a></li>";
                        pw.println(ct);
                        pw.println("<div id=\"barchart_values\" ></div>");
                        pw.println(sb);
                        pw.println(ft);
            }

}