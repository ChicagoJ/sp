import java.sql.*;
import java.lang.*;
import java.util.*;

public class MySqlDataStoreUtilities {

    public static Connection getDafaultConnection() {
        try {

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection conn = null;

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "nyit");
            System.out.println("\n Driver is connected Successfully");

            return conn;
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
//
    public static void insertUser(String username, String password, String usertype){
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SP",
                    "root", "nyit");


        String insertIntoCustomerRegisterQuery = "INSERT INTO Registration("
                                                + "username, password,usertype)"
                                                + "VALUES(?,?,?);";
        PreparedStatement pst = conn.prepareStatement(insertIntoCustomerRegisterQuery);
        pst.setString(1,username);
        pst.setString(2,password);
        pst.setString(3,usertype);
        pst.execute();
        System.out.println("User inserted");
        }
        catch(Exception e){
            System.out.println("user not inserted");
        }
    }

    public static void insertOrder(String uId, String oId, String itemId, int number, String cId, String oTime, double totalsales){
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SP",
                    "root", "nyit");


            String insertIntoOrdersQuery = "INSERT INTO Orders("
                    + "uId, oId, itemName, itemNumber, cId, orderTime, totalSales)"
                    + "VALUES(?,?,?,?,?,?,?);";
            PreparedStatement pst = conn.prepareStatement(insertIntoOrdersQuery);
            pst.setString(1,uId);
            pst.setString(2,oId);
            pst.setString(3,itemId);
            pst.setInt(4,number);
            pst.setString(5,cId);
            pst.setString(6,oTime);
            pst.setDouble(7,totalsales);
            pst.execute();
            System.out.println("Orders inserted");
        }
        catch(Exception e){
            System.out.println("orders not inserted");
        }
    }
    public static boolean addOrder(String uId, String oId, String itemId, int number, String cId){
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SP",
                    "root", "nyit");


        String insertIntoOrdersQuery = "INSERT INTO Orders("
                                                + "uId, oId, itemName, itemNumber, cId)"
                                                + "VALUES(?,?,?,?,?);";
        PreparedStatement pst = conn.prepareStatement(insertIntoOrdersQuery);
        pst.setString(1,uId);
        pst.setString(2,oId);
        pst.setString(3,itemId);
        pst.setInt(4,number);
        pst.setString(5,cId);
        pst.execute();
        System.out.println("Orders inserted");
        }
        catch(Exception e){
            System.out.println("orders not inserted");
        }
        return true;    
    }
    public static boolean addItem(String itemID, String itemName, double price, double discount, double rebates, int stocks, String accessories){
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SP",
                    "root", "nyit");


        String addItemQuery = "INSERT INTO Products("
                                                + "itemID, itemName, price, discount, rebates, stocks, accessories)"
                                                + "VALUES(?,?,?,?,?,?,?);";
        PreparedStatement pst = conn.prepareStatement(addItemQuery);
        pst.setString(1,itemID);
        pst.setString(2,itemName);
        pst.setDouble(3,price);
        pst.setDouble(4,discount);
        pst.setDouble(5,rebates);
        pst.setInt(6, stocks);
        pst.setString(7, accessories);
        pst.execute();
        System.out.println("Item inserted to DB");
        }
        catch(Exception e){
            System.out.println("Items not inserted to DB");
        }
        return true;      
    }
    public static boolean updateItem(String itemID, String itemName, double price, double discount, double rebates, int stocks, String accessories){
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SP",
                    "root", "nyit");


        String updateItemQuery = "update Products set itemName = \"" + itemName + "\","
                                                       + "price = \"" + price + "\","
                                                       + "discount = \"" + discount + "\","
                                                       + "rebates = \"" + rebates + "\","
                                                       + "stocks = \"" + stocks + "\","
                                                       + "accessories = \"" + accessories + "\" "
                                                       + "where itemID = \"" + itemID + "\";";
        PreparedStatement pst = conn.prepareStatement(updateItemQuery);
        System.out.println(updateItemQuery);
        pst.execute();
        System.out.println("Item updated to DB");
        }
        catch(Exception e){
            System.out.println("Items updated to DB");
        }
        return true;     
    }
    
    public static void updateItem(String itemName, int stocks){
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SP",
                    "root", "nyit");


        String updateItemQuery = "update Products set stocks = \"" + stocks + "\""
                                                       + "where itemName = \"" + itemName + "\";";
        PreparedStatement pst = conn.prepareStatement(updateItemQuery);
        System.out.println(updateItemQuery);
        pst.execute();
        System.out.println("Item updated to DB");
        }
        catch(Exception e){
            System.out.println("Items updated to DB");
        }     
    }    
    
    public static void updateItemById(String itemId, int stocks){
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SP",
                    "root", "nyit");


        String updateItemQuery = "update Products set stocks = \"" + stocks + "\""
                                                       + "where itemId = \"" + itemId + "\";";
        PreparedStatement pst = conn.prepareStatement(updateItemQuery);
        System.out.println(updateItemQuery);
        pst.execute();
        System.out.println("Item updated to DB");
        }
        catch(Exception e){
            System.out.println("Items updated to DB");
        }     
    }

    public static boolean updateOrder(String uId, String oId, String itemName, int itemNumber, String cId){
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SP",
                    "root", "nyit");


        String updateOrderQuery = "update Orders set uId = \"" + uId + "\","
                                                       + "itemName = \"" + itemName + "\","
                                                       + "itemNumber = \"" + itemNumber + "\","
                                                       + "cId = \"" + cId + "\""
                                                       + "where oId = \"" + oId + "\";";
        PreparedStatement pst = conn.prepareStatement(updateOrderQuery);
        System.out.println(updateOrderQuery);
        pst.execute();
        System.out.println("Orders updated to DB");
        }
        catch(Exception e){
            System.out.println("Orders updated to DB");
        }
        return true;     
    }    

    public static boolean deleteItem(String itemID){
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SP",
                    "root", "nyit");


        String deleteItemQuery = "delete from Products where itemID = \""+ itemID +"\";";
        PreparedStatement pst = conn.prepareStatement(deleteItemQuery);
        System.out.println(deleteItemQuery);
        // pst.setString(1,itemID);
        pst.execute();
        System.out.println("Item deleted from DB");
        }
        catch(Exception e){
            System.out.println("Items not deleted from DB");
        }
        return true;      
    }

    public static void deleteOrder(String cId, String itemName){
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SP",
                    "root", "nyit");


        String deleteOrderQuery = "delete from Orders where itemName = \""+ itemName +"\" and cId = \""+ cId+"\";";
        System.out.println(deleteOrderQuery);
        PreparedStatement pst = conn.prepareStatement(deleteOrderQuery);
        pst.execute();
        System.out.println("Order deleted from DB");
        }
        catch(Exception e){
            System.out.println("Order not deleted from DB");
        }    
    }
   
    public static Map<String, User> selectUser(){
        Map<String, User> users = new HashMap<String, User>();
        try {
            Connection conn = null;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SP",
                    "root", "nyit");
            String selectUserQuery = "SELECT * FROM registration;";
//            System.out.println("1");
            PreparedStatement pst = conn.prepareStatement(selectUserQuery);
//            System.out.println("2");
            ResultSet rs = pst.executeQuery();
//            System.out.println("3");
            System.out.println(rs);
            while(rs.next()){
//                int lvl =  Integer.parseInt(rs.getString("usertype"));
                User user =
                        new User(rs.getString("username"), rs.getString("password"),
                                rs.getInt("usertype"));
                users.put(rs.getString(("username")),user);
            }
            System.out.println("the user are + " + users.values());
            System.out.println("user selected");
//            return users;
        }catch (Exception e){
            System.out.println("error");
        }
        return users;
    }

    public static List<String> getCId(String uId, String itemName){
        List<String> cIdList = new ArrayList<String>();
        try {
            Connection conn = null;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SP",
                    "root", "nyit");
            String getCIdQuery = "SELECT * FROM orders where uId = \"" + uId + "\" and itemName = \"" + itemName + "\";";
            System.out.println(getCIdQuery);
            PreparedStatement pst = conn.prepareStatement(getCIdQuery);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                System.out.println(rs.getString("cId"));
                cIdList.add(rs.getString("cId"));
            }
            System.out.println("the cIds are " + cIdList);
            System.out.println("cIds selected");
//            return users;
        }catch (Exception e){
            System.out.println("error");
        }
        return cIdList;
    }


    public static List<Order> getOrder(String uId){
        // Map<String, User> users = new HashMap<String, User>();
        List<Order> orders = new ArrayList<Order>();
        try {
            Connection conn = null;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SP",
                    "root", "nyit");
            String selectOrderQuery = "SELECT * FROM Orders where uId =\"" + uId +"\" ;";
            PreparedStatement pst = conn.prepareStatement(selectOrderQuery);
            System.out.println("pst ok");
            ResultSet rs = pst.executeQuery();
            System.out.println("rs ok");
            while(rs.next()){
                Order order = new Order(rs.getString("uId"), rs.getString("oId"),
                                        rs.getString("itemName"),rs.getInt("itemNumber"),
                                        rs.getString("cId"));
                // users.put(rs.getString(("username")),user);
                orders.add(order);
            }
            // System.out.println(users.values());
            System.out.println("order selected");
//            return users;
        }catch (Exception e){
            System.out.println("error");
        }
        return orders;    
    }

    public static List<Order> getOrder(){
        List<Order> orders = new ArrayList<Order>();
        try {
            Connection conn = null;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SP",
                    "root", "nyit");
            String selectOrderQuery = "SELECT * FROM Orders;";
            PreparedStatement pst = conn.prepareStatement(selectOrderQuery);
            // System.out.println("pst ok");
            ResultSet rs = pst.executeQuery();
            // System.out.println("rs ok");
            while(rs.next()){
                Order order = new Order(rs.getString("uId"), rs.getString("oId"),
                                        rs.getString("itemName"),rs.getInt("itemNumber"),
                                        rs.getString("cId"));
                // users.put(rs.getString(("username")),user);
                orders.add(order);
            }
            // System.out.println(users.values());
            System.out.println("all order selected");
//            return users;
        }catch (Exception e){
            System.out.println("error");
        }
        return orders;    
    }

    public static String getItemId(String itemName){
        String itemId = "";
        try {
            Connection conn = null;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SP",
                    "root", "nyit");
            String selectIdQuery = "SELECT * FROM Products where itemName = \"" + itemName + "\";";
            PreparedStatement pst = conn.prepareStatement(selectIdQuery);
            // System.out.println("pst ok");
            ResultSet rs = pst.executeQuery();
            // System.out.println("rs ok");
            while(rs.next()){
                itemId = rs.getString("itemID");
                // users.put(rs.getString(("username")),user);
                // orders.add(order);
            }
            // System.out.println(users.values());
            System.out.println("id selected");
//            return users;
        }catch (Exception e){
            System.out.println("error");
        }
        return itemId;    
    }
    public static List<Order> getTotalSold(){
        List<Order> orders = new ArrayList<Order>();
        try {
            Connection conn = null;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SP",
                    "root", "nyit");
            String selectOrderQuery = "select SUM(itemNumber) as totalsold, SUM(totalsales) as finalsales,itemName from Orders GROUP BY itemName ;";
            PreparedStatement pst = conn.prepareStatement(selectOrderQuery);
            // System.out.println("pst ok");
            ResultSet rs = pst.executeQuery();
            // System.out.println("rs ok");
            while(rs.next()){
                Order order = new Order(rs.getString("itemName"),rs.getInt("totalsold"),rs.getDouble("finalsales"));
                // users.put(rs.getString(("username")),user);
                orders.add(order);
            }
            // System.out.println(users.values());
            System.out.println("all total sold selected");
//            return users;
        }catch (Exception e){
            System.out.println("error");
        }
        return orders;    
    }
    
    public static List<Order> getDaliySales(){
        List<Order> orders = new ArrayList<Order>();
        try {
            Connection conn = null;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SP",
                    "root", "nyit");
            String selectDaliyQuery = "select SUM(totalsales) as finalsales,orderTime from Orders GROUP BY orderTime;";
            PreparedStatement pst = conn.prepareStatement(selectDaliyQuery);
            // System.out.println("pst ok");
            ResultSet rs = pst.executeQuery();
            // System.out.println("rs ok");
            while(rs.next()){
                Order order = new Order(rs.getString("orderTime"),rs.getDouble("finalsales"));
                // users.put(rs.getString(("username")),user);
                orders.add(order);
            }
            // System.out.println(users.values());
            System.out.println("daily sales selected");
//            return users;
        }catch (Exception e){
            System.out.println("error");
        }
        return orders;    
    }

    public static int getStock(String itemName){
        int stocks= 0;
        try {
            Connection conn = null;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SP",
                    "root", "nyit");
            String getStockQuery = "SELECT * FROM Products where itemName = \""+ itemName +"\";";
            PreparedStatement pst = conn.prepareStatement(getStockQuery);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                stocks = rs.getInt("stocks");
            }
            // System.out.println(users.values());
            System.out.println("itemNumber selected" + stocks);
//            return users;
        }catch (Exception e){
            System.out.println("error");
        }
        return stocks;    
    }

    public static int getStockById(String itemId){
        int stocks= 0;
        try {
            Connection conn = null;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SP",
                    "root", "nyit");
            String getStockQuery = "SELECT * FROM Products where itemId = \""+ itemId +"\";";
            PreparedStatement pst = conn.prepareStatement(getStockQuery);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                stocks = rs.getInt("stocks");
            }
            // System.out.println(users.values());
            System.out.println("itemNumber selected" + stocks);
//            return users;
        }catch (Exception e){
            System.out.println("error");
        }
        return stocks;    
    }


    public static Map<String, Item> getItems(){
        Map<String, Item> items = new HashMap<String, Item>();
        try{
            Connection conn = null;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SP",
                    "root", "nyit");
            String getItemsQuery = "SELECT * FROM PRODUCTS";
            PreparedStatement pst = conn.prepareStatement(getItemsQuery);
            ResultSet rs = pst.executeQuery();
            List<String> accessories = new ArrayList<String>();
            String s = "";
            while(rs.next()){
                s = rs.getString("accessories");
                accessories = new ArrayList<String>(Arrays.asList(s.split(",")));
                Item item = new Item(rs.getString("itemID"), rs.getString("itemName"), 
                    rs.getDouble("price"), rs.getDouble("discount"), rs.getDouble("rebates"), 
                    rs.getInt("stocks"), accessories);
                items.put(rs.getString("itemID"), item);
            }
            // System.out.println(items.values());
            System.out.println("item got!");

        }catch (Exception e){
            System.out.println("didnt get items");
        }
        return items;
    }

    

    public static void ReadDataToDB(){
        Map<String, Item> items = new HashMap<String, Item>();
        items = SaxPaserDataStore.getItems("./ProductCatalog.xml");
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SP",
                    "root", "nyit");
            String accessories = "";
            for (Item item: items.values()) {
                String insertIntoProductsQuery = "INSERT INTO Products("
                                                        + "itemID, itemName, price, discount, rebates, stocks, accessories)"
                                                        + "VALUES(?,?,?,?,?,?,?);";
                PreparedStatement pst = conn.prepareStatement(insertIntoProductsQuery);
                pst.setString(1,item.getItemId());
                pst.setString(2,item.getItemName());
                pst.setDouble(3,item.getPrice());
                pst.setDouble(4,item.getDiscount());
                pst.setDouble(5,item.getRebates());
                pst.setInt(6,item.getStock());
                accessories = String.join(",", item.getAccessories());
                pst.setString(7,accessories);
                pst.execute();            
            }            
                System.out.println("products inserted");
        }
        catch(Exception e){
            System.out.println("products not inserted");
        }
    }

    public static boolean updateItemNum(String itemName){
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SP",
                    "root", "nyit");

            int itemNumber = (int)(Math.random()*20 + 10);
            String updateOrderQuery = "update Products set stocks = " + itemNumber
                    + " where itemName =\""+ itemName + "\";";
            PreparedStatement pst = conn.prepareStatement(updateOrderQuery);
            System.out.println(updateOrderQuery);
            pst.execute();
            System.out.println("Numbers updated to DB");
        }
        catch(Exception e){
            System.out.println("Numbers not updated to DB");
        }
        return true;
    }

    public static void main(String args[]){
    // //     // String username = "test1";
    // //     // String password = "111";
    // //     // String usertype = "customer";
    // //     // insertUser(username,password,usertype);
        // ReadDataToDB();
        Map<String, Item> items = new HashMap<String, Item>();
        items = getItems();
    // //     System.out.println(items);
    //     String itemID = "k01";
    //     String itemName = "Kindle";
    //     double price = 99.00;
    //     double discount = 0.8;
    //     double rebates = 20;
    //     int stocks = 99;
    //     String accessories = "protected case 1, protected case 2, protected case 3, protected case 4";
    //     // addItem(itemID,itemName,price, discount,rebates, stocks, accessories);
    //     updateItem(itemID,itemName,price, discount,rebates, stocks, accessories);
    //     // deleteItem(itemID);
        // List<String> cid = getCId("manager","HeadPhone 1");
        // String s = "2045380992";
        // if (cid.contains(s)){
        // deleteOrder(s, "HeadPhone 1");}
        // List<Order> oList = getTotalSold();
        // System.out.println(oList);
        for(Item item : items.values()){
            updateItemNum(item.getItemName());
        }
    }
}

