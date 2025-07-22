package model;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import org.bson.Document;

public class Bill {
    private String customerName;
    private double roomAmount;
    private double foodOrdersAmount;
    private double totalAmount;

    private static final String COLLECTION_NAME = "bills";

    public Bill(String customerName, double roomAmount, double foodOrdersAmount, double totalAmount) {
        this.customerName = customerName;
        this.roomAmount = roomAmount;
        this.foodOrdersAmount = foodOrdersAmount;
        this.totalAmount = totalAmount;
    }

    public void saveToMongoDB() {
        try {
            MongoDatabase database = MongoDBConnection.getInstance().getDatabase();
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);
            Document query = new Document("customerName", this.customerName);
            Document updateDoc = new Document("$set", new Document("roomAmount", this.roomAmount)
                    .append("foodOrdersAmount", this.foodOrdersAmount)
                    .append("totalAmount", this.totalAmount));

            // Use updateOne with upsert option set to true
            UpdateOptions options = new UpdateOptions().upsert(true);
            collection.updateOne(query, updateDoc, options);

            System.out.println("Bill saved to MongoDB successfully.");
        } catch (Exception e) {
            System.err.println("Error saving bill to MongoDB: " + e.getMessage());
        }
    }

    public static void deleteBillByCustomerName(String customerName) {
        try {
            MongoDatabase database = MongoDBConnection.getInstance().getDatabase();
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);
            Document query = new Document("customerName", customerName);

            collection.deleteOne(query);

            System.out.println("Bill deleted from MongoDB for customer: " + customerName);
        } catch (Exception e) {
            System.err.println("Error deleting bill from MongoDB: " + e.getMessage());
        }
    }
    
    public double getTotalAmount() {
        return this.totalAmount;
    }

    public String getCustomerName() {
        return this.customerName;
    }

}
