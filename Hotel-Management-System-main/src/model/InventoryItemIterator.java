package model;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Iterator;

public class InventoryItemIterator implements InventoryIterator {
    private Iterator<Document> iterator;

    public InventoryItemIterator() {
        MongoDatabase database = MongoDBConnection.getInstance().getDatabase();
        MongoCollection<Document> collection = database.getCollection("inventory");
        FindIterable<Document> items = collection.find();
        this.iterator = items.iterator();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public Inventory next() {
        Document doc = iterator.next();
        String itemName = doc.getString("itemName");
        int quantity = doc.getInteger("quantity");
        return new Inventory(itemName, quantity);
    }
}
