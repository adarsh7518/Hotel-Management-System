package model;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Iterator;

public class FoodItemIterator implements FoodIterator {
    private Iterator<Document> iterator;

    public FoodItemIterator() {
        try {
            MongoDatabase database = MongoDBConnection.getInstance().getDatabase();
            MongoCollection<Document> collection = database.getCollection("foodItems");

            FindIterable<Document> iterable = collection.find();
            this.iterator = iterable.iterator();
        } catch (Exception e) {
            System.err.println("Error initializing food item iterator: " + e.getMessage());
            this.iterator = null;
        }
    }

    @Override
    public boolean hasNext() {
        return iterator != null && iterator.hasNext();
    }

    @Override
    public FoodItem next() {
        if (iterator != null && iterator.hasNext()) {
            Document doc = iterator.next();
            String foodName = doc.getString("foodName");
            double price = doc.getDouble("price");
            return new FoodItem(foodName, price);
        }
        return null;
    }
}
