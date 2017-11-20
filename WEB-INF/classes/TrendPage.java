
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.mongodb.*;


public class TrendPage extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        response.setContentType("text/html");
        java.io.PrintWriter out = response.getWriter();
        DBCollection myReviews;
        MongoClient mongo;
        mongo = new MongoClient("localhost", 27017);
        DB db = mongo.getDB("sp");
        myReviews = db.getCollection("myReviews");
        AggregationOutput poutput = myReviews.aggregate(Arrays.asList(
                                    new BasicDBObject("$group",new BasicDBObject("_id","$productName").append(
                                            "count",new BasicDBObject("$sum",1)))));

        Iterator<DBObject> pdbCursor = poutput.results().iterator();
        Map<String, Integer> pmap = new HashMap<String, Integer>();
        while(pdbCursor.hasNext()){
            BasicDBObject bobj = (BasicDBObject) pdbCursor.next();
            pmap.put(bobj.getString("_id"),bobj.getInt("count"));
        }

        Object [] a = pmap.entrySet().toArray();
        Arrays.sort(a, new Comparator() {
            public int compare(Object o1, Object o2){
                return ((Map.Entry<String, Integer>) o2).getValue().compareTo(((Map.Entry<String, Integer>) o1).getValue());
            }
        });

        AggregationOutput zoutput = myReviews.aggregate(Arrays.asList(
                                    new BasicDBObject("$group",new BasicDBObject("_id","$retailerZip").append(
                                            "count",new BasicDBObject("$sum",1)))));

        Iterator<DBObject> zdbCursor = zoutput.results().iterator();
        Map<String, Integer> zmap = new HashMap<String, Integer>();
        while(zdbCursor.hasNext()){
            BasicDBObject bobj1 = (BasicDBObject) zdbCursor.next();
            zmap.put(bobj1.getString("_id"),bobj1.getInt("count"));
        }

        Object [] z = zmap.entrySet().toArray();
        Arrays.sort(z, new Comparator() {
            public int compare(Object o1, Object o2){
                return ((Map.Entry<String, Integer>) o2).getValue().compareTo(((Map.Entry<String, Integer>) o1).getValue());
            }
        });
        
        AggregationOutput routput = myReviews.aggregate(Arrays.asList(
                new BasicDBObject("$group",new BasicDBObject("_id","$productName").append(
                        "average",new BasicDBObject("$avg","$reviewRating")))
        ));


        Iterator<DBObject> rdbCursor = routput.results().iterator();
        Map<String, Integer> map = new HashMap<String, Integer>();
        while(rdbCursor.hasNext()){
            BasicDBObject rbobj = (BasicDBObject) rdbCursor.next();
              map.put(rbobj.getString("_id"),rbobj.getInt("average"));

        }

        Object [] r = map.entrySet().toArray();
        Arrays.sort(r, new Comparator() {
            public int compare(Object o1, Object o2){
                return ((Map.Entry<String, Integer>) o2).getValue().compareTo(((Map.Entry<String, Integer>) o1).getValue());
            }
        });






        out.println("<html>");
        out.println("<head>");
        out.println("<title>Trend</title>");  
        out.println("</head>");
        out.println("<body>");
        // out.println(message);
        out.println("<h1>Trending<br><br><hr>");
        

       // Top five most liked products
        out.println("<h3>Top five most liked products<hr>");
        int rcount = 0;
        for (Object e : r){
            rcount ++;
            out.println("<table>");
            out.println("<tr><td>Rank#" + rcount + "</td><tr>");
            out.println("<tr><td>productName: " + ((Map.Entry<String, Integer>) e).getKey() + " </td><tr>");
            out.println("<tr><td>Average reviewRating: " + ((Map.Entry<String, Integer>) e).getValue() + " </td><tr>");
            out.println("<tr>");
            out.println("<td>--------------<td>");
            out.println("</tr>");
            out.println("</table>");
            if (rcount == 5){
                break;
            }

        }



        // Top five zip-codes where maximum number of products sold
        out.println("<h3>Top five zip-codes where maximum number of products sold<hr>");
        int zcount = 0;
        for (Object e : z){
            zcount ++;
            out.println("<table>");
            out.println("<tr><td>Rank#" + zcount + "</td><tr>");
            out.println("<tr><td>retailer zip-code: " + ((Map.Entry<String, Integer>) e).getKey() + " </td><tr>");
            out.println("<tr><td>Sold #: " + ((Map.Entry<String, Integer>) e).getValue() + " </td><tr>");
            out.println("<tr>");
            out.println("<td>--------------<td>");
            out.println("</tr>");
            out.println("</table>");
            if (zcount == 5){
                break;
            }

        }



        // Top five most sold products regardless of the rating
        out.println("<h3>Top five most sold products regardless of the rating<hr>");
        int pcount = 0;
        for (Object e : a){
            pcount ++;
            out.println("<table>");
            out.println("<tr><td>Rank#" + pcount + "</td><tr>");
            out.println("<tr><td>productName: " + ((Map.Entry<String, Integer>) e).getKey() + " </td><tr>");
            out.println("<tr><td>Sold #: " + ((Map.Entry<String, Integer>) e).getValue() + " </td><tr>");
            out.println("<tr>");
            out.println("<td>--------------<td>");
            out.println("</tr>");
            out.println("</table>");
            if (pcount == 5){
                break;
            }

        }


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