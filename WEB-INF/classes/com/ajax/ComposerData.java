package com.ajax;


import java.sql.*;
import java.lang.*;
import java.util.*;

/**
 *
 * @author nbuser
 */
public class ComposerData {
    
    HashMap composers = new HashMap();
    

    public HashMap getComposers() {
        return composers;
    }
    
    public ComposerData() {
        

        try{
            Connection conn = null;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SP",
                    "root", "nyit");
            String getItemsQuery = "SELECT * FROM PRODUCTS";
            PreparedStatement pst = conn.prepareStatement(getItemsQuery);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                composers.put(rs.getString("itemID"), new Composer(rs.getString("itemID"), rs.getString("itemName"), rs.getDouble("price")));
            }
            // System.out.println(items.values());
            System.out.println("composers got!");


        }catch (Exception e){
            System.out.println("didnt get composers");
        }
        

        
    }

}
