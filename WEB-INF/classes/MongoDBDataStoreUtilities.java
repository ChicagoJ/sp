import com.mongodb.*;


public class MongoDBDataStoreUtilities {
	public static void getConnection(){
		DBCollection myReviews;
        MongoClient mongo;
        mongo = new MongoClient("localhost", 27017);
        DB db = mongo.getDB("test");
        myReviews = db.getCollection("test");	
    }

    public static void insertReview(String productName, String productCategory, double productPrice, String retailerName,
                                    String retailerZip, String retailerCity, String retailerState, String productOnSale,
                                    String manufacturerName, String manufacturerRebate, String userID, String userAge,
                                    String userGender, String userOccupation, int reviewRating, String reviewDate,
                                    String reviewText ){
        DBCollection myReviews;
        MongoClient mongo;
        mongo = new MongoClient("localhost", 27017);
        DB db = mongo.getDB("sp");
        myReviews = db.getCollection("myReviews");
        BasicDBObject doc = new BasicDBObject("title", "myReviews").
                append("productName", productName).
                append("productCategory", productCategory).
                append("productPrice", productPrice).
                append("retailerName", retailerName).
                append("retailerZip", retailerZip).
                append("retailerCity", retailerCity).
                append("retailerState", retailerState).
                append("productOnSale", productOnSale).
                append("manufacturerName", manufacturerName).
                append("manufacturerRebate", manufacturerRebate).
                append("userID", userID).
                append("userAge", userAge).
                append("userGender", userGender).
                append("userOccupation", userOccupation).
                append("reviewRating", reviewRating).
                append("reviewDate", reviewDate).
                append("reviewText", reviewText);
        myReviews.insert(doc);
        System.out.println("document inserted");

    }

    
}
