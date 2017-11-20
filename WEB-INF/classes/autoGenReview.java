import java.io.*; 
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class autoGenReview {


      public static List<String> getNames() {


            Map<String, Item> items = MySqlDataStoreUtilities.getItems();

            List<String> name = new ArrayList<String>();                  
            for (Item item : items.values()) {
                  name.add(item.getItemName());
                  // pw.println("<td align=\"\">"+ item.getItemName() + "</td>");
             }
             return name;
      }

      public static List<String> getusers() {


        Map<String, User> users = MySqlDataStoreUtilities.selectUser();

            List<String> name = new ArrayList<String>();                  
            for (User user : users.values()) {
                  name.add(user.getUserId());
                  // pw.println("<td align=\"\">"+ item.getItemName() + "</td>");
             }
             return name;
      }




      public static void main(String args[]){
        // Name
        List<String> productName = getNames();
        // catogory
        List<String> productCategory = new ArrayList<String>();
        productCategory.add("Phone");
        productCategory.add("Laptop");
        productCategory.add("SmartWatch");
        productCategory.add("HeadPhone");
        productCategory.add("Speakers");
        productCategory.add("Storage");
        double productPrice = 1;
        // Zipcode
        List<String> retailerZip = new ArrayList<String>();
        retailerZip.add("60616");
        retailerZip.add("10000");
        retailerZip.add("60608");
        retailerZip.add("60618");
        retailerZip.add("60209");
        retailerZip.add("60220");
        retailerZip.add("60234");
        retailerZip.add("61209");
        retailerZip.add("23409");
        // retailer name
        String retailerName = "SmartPortables";
        // retailer city
        List<String> retailerCity = new ArrayList<String>();
        retailerCity.add("Chicago");
        retailerCity.add("NewYork");
        retailerCity.add("Seattle");
        retailerCity.add("Springfiled");
        // retailer state
        List<String> retailerState = new ArrayList<String>();
        retailerState.add("IL");
        retailerState.add("WA");
        retailerState.add("NY");

        String productOnSale = "yes";

        String manufacuterName = "Apple";

        String manufacuterRebate = "yes";

        List<String> userID = getusers();

        int userAge = 20;

        List<String> userGender = new ArrayList<String>();
        userGender.add("Male");
        userGender.add("Female");

        List<String> userOccupation = new ArrayList<String>();
        userOccupation.add("Student");
        userOccupation.add("Teacher");
        userOccupation.add("Salesman");

        int reviewRating = 5;

        String reviewDate = "10/20/2017";

        List<String> reviewText = new ArrayList<String>();
        reviewText.add("aaa");
        reviewText.add("kkk");
        reviewText.add("Good");
        reviewText.add("Great");
        reviewText.add("Not good..emm, just so so");

        for(int i = 1; i < 100; i++){
          MongoDBDataStoreUtilities.insertReview(productName.get((int) (Math.random() * 99)),
                                                  productCategory.get((int) (Math.random() * 6)),
                                                  (Double) (Math.random() * 1000),
                                                  retailerName,
                                                  retailerZip.get((int) (Math.random() * 9)),
                                                  retailerCity.get((int) (Math.random() * 4)),
                                                  retailerState.get((int) (Math.random() * 3)),
                                                  productOnSale,
                                                  manufacuterName,
                                                  manufacuterRebate,
                                                  userID.get((int) (Math.random() * 18)),
                                                  (int) (Math.random() * 70) + "",
                                                  userGender.get((int) (Math.random() * 2)),
                                                  userOccupation.get((int) (Math.random() * 3)),
                                                  ((int) (Math.random() * 5)) + 1,
                                                  reviewDate,
                                                  reviewText.get((int)(Math.random() * 5)) );
        }


      }

}