package model;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class Inventory {
    private String itemName;
    private int quantity;

    public Inventory(String itemName, int quantity) {
        this.itemName = itemName;
        this.quantity = quantity;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public static boolean addItem(String itemName, int quantity) {
        MongoDatabase database = MongoDBConnection.getInstance().getDatabase();
        MongoCollection<Document> collection = database.getCollection("inventory");

        Document itemDoc = new Document("itemName", itemName)
                .append("quantity", quantity);

        collection.insertOne(itemDoc);
        return true;
    }

    public static void deleteItem(String itemName) {
        MongoDatabase database = MongoDBConnection.getInstance().getDatabase();
        MongoCollection<Document> collection = database.getCollection("inventory");

        Document query = new Document("itemName", itemName);
        collection.deleteOne(query);
    }

    public static String getAllItems() {
        StringBuilder items = new StringBuilder();
        InventoryIterator iterator = new InventoryItemIterator();

        while (iterator.hasNext()) {
            Inventory item = iterator.next();
            items.append(item.getItemName()).append(" - Quantity: ").append(item.getQuantity()).append("\n");
        }

        return items.toString();
    }
}
