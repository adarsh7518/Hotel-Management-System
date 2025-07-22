package model;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class FoodItem {
    private String foodName;
    private double price;

    private static final String COLLECTION_NAME = "foodItems";

    public FoodItem(String foodName, double price) {
        this.foodName = foodName;
        this.price = price;
    }

    public void saveToDatabase() {
        try {
            MongoDatabase database = MongoDBConnection.getInstance().getDatabase();
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            Document foodItemDoc = new Document("foodName", foodName)
                    .append("price", price);

            collection.insertOne(foodItemDoc);
        } catch (Exception e) {
            System.err.println("Error saving food item to MongoDB: " + e.getMessage());
        }
    }

    public static void deleteItem(String foodName) {
        try {
            MongoDatabase database = MongoDBConnection.getInstance().getDatabase();
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            Document query = new Document("foodName", foodName);
            collection.deleteOne(query);
        } catch (Exception e) {
            System.err.println("Error deleting food item from MongoDB: " + e.getMessage());
        }
    }

    public static String getAllItems() {
        StringBuilder items = new StringBuilder();
        FoodItemIterator iterator = new FoodItemIterator();

        while (iterator.hasNext()) {
            FoodItem foodItem = iterator.next();
            if (foodItem != null) {
                items.append(foodItem.getFoodName()).append(" - Price: $").append(foodItem.getPrice()).append("\n");
            }
        }

        return items.toString();
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
