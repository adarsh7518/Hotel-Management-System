package model;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class FoodOrder {
    private String foodName;
    private int quantity;
    private String customerName;
    private FoodItem foodItem; // Reference to the corresponding FoodItem

    public FoodOrder(String foodName, int quantity, String customerName) {
        this.foodName = foodName;
        this.quantity = quantity;
        this.customerName = customerName;
        this.foodItem = retrieveFoodItem(); // Retrieve the associated FoodItem
    }

    // Retrieve the FoodItem corresponding to this order
    private FoodItem retrieveFoodItem() {
        try {
            MongoDatabase database = MongoDBConnection.getInstance().getDatabase();
            MongoCollection<Document> collection = database.getCollection("foodItems");

            Document query = new Document("foodName", foodName);
            Document foodItemDoc = collection.find(query).first();

            if (foodItemDoc != null) {
                double price = foodItemDoc.getDouble("price");
                return new FoodItem(foodName, price); // Create FoodItem object
            }
        } catch (Exception e) {
            System.err.println("Error retrieving FoodItem from MongoDB: " + e.getMessage());
        }

        return null; // FoodItem not found or error occurred
    }

    public void saveToDatabase() {
        try {
            MongoDatabase database = MongoDBConnection.getInstance().getDatabase();
            MongoCollection<Document> collection = database.getCollection("foodOrders");

            Document orderDoc = new Document("foodName", foodName)
                    .append("quantity", quantity)
                    .append("customerName", customerName);

            collection.insertOne(orderDoc);
        } catch (Exception e) {
            System.err.println("Error saving FoodOrder to MongoDB: " + e.getMessage());
        }
    }

    public static List<FoodOrder> getOrdersByCustomer(String customerName) {
        try {
            MongoDatabase database = MongoDBConnection.getInstance().getDatabase();
            MongoCollection<Document> collection = database.getCollection("foodOrders");

            List<FoodOrder> orders = new ArrayList<>();
            for (Document doc : collection.find(new Document("customerName", customerName))) {
                String foodName = doc.getString("foodName");
                int quantity = doc.getInteger("quantity");
                orders.add(new FoodOrder(foodName, quantity, customerName));
            }
            return orders;
        } catch (Exception e) {
            System.err.println("Error retrieving FoodOrders from MongoDB: " + e.getMessage());
            return new ArrayList<>(); // Return an empty list in case of error
        }
    }

    public void deleteFromDatabase() {
        try {
            MongoDatabase database = MongoDBConnection.getInstance().getDatabase();
            MongoCollection<Document> collection = database.getCollection("foodOrders");

            Document query = new Document("foodName", foodName)
                    .append("customerName", customerName);
            collection.deleteOne(query);
        } catch (Exception e) {
            System.err.println("Error deleting FoodOrder from MongoDB: " + e.getMessage());
        }
    }

    // Calculate the total cost of this order using the associated FoodItem price
    public double getTotalCost() {
        if (foodItem != null) {
            return foodItem.getPrice() * quantity;
        }
        return 0.0; // Handle case where FoodItem is not found
    }

    // Other getters and setters
}
