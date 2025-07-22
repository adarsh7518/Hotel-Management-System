package DAO;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import model.MongoDBConnection;
import org.bson.Document;

public class FoodItemDAO {

    private MongoCollection<Document> foodItemCollection;

    public FoodItemDAO() {
        MongoDatabase database = MongoDBConnection.getInstance().getDatabase();
        this.foodItemCollection = database.getCollection("foodItems");
    }

    public void insertFoodItemData() {
        // Data from JSON
        Document[] foodItemData = {
                new Document("itemName", "Burger").append("price", 10.0),
                new Document("itemName", "Pizza").append("price", 15.0),
                new Document("itemName", "Salad").append("price", 8.0)
        };

        // Insert data into foodItems collection
        for (Document foodItem : foodItemData) {
            foodItemCollection.insertOne(foodItem);
        }
    }

    public static void main(String[] args) {
        // Instantiate the FoodItemDAO
        FoodItemDAO foodItemDAO = new FoodItemDAO();

        // Insert food item data into the database
        foodItemDAO.insertFoodItemData();

        // Display a message indicating successful insertion
        System.out.println("Food item data inserted successfully!");
    }
}

