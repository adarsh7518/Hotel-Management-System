package service;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import model.Customer;
import model.FoodOrder;
import model.MongoDBConnection;
import org.bson.Document;

import java.util.List;

public class CustomerService {

    private static final String COLLECTION_NAME = "customers";

    public void createCustomer(String name, String id, String phoneNumber, String address, int roomNumber) {
        Customer customer = new Customer(name, id, phoneNumber, address, roomNumber);
        saveCustomerToDatabase(customer);
    }

    public Customer getCustomerByRoomNumber(int roomNumber) {
        MongoDatabase database = MongoDBConnection.getInstance().getDatabase();
        MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

        Document query = new Document("roomNumber", roomNumber);
        Document customerDoc = collection.find(query).first();

        if (customerDoc != null) {
            String name = customerDoc.getString("name");
            String id = customerDoc.getString("id");
            String phoneNumber = customerDoc.getString("phoneNumber");
            String address = customerDoc.getString("address");
            return new Customer(name, id, phoneNumber, address, roomNumber);
        } else {
            return null; // Customer not found
        }
    }

    public void deleteCustomerByRoomNumber(int roomNumber) {
        MongoDatabase database = MongoDBConnection.getInstance().getDatabase();
        MongoCollection<Document> customerCollection = database.getCollection(COLLECTION_NAME);
//        MongoCollection<Document> foodOrderCollection = database.getCollection("foodOrders");

        // Delete associated food orders by customerName
        Customer customer = getCustomerByRoomNumber(roomNumber);
        if (customer != null) {
            String customerName = customer.getName();
            List<FoodOrder> orders = FoodOrder.getOrdersByCustomer(customerName);
            for (FoodOrder order : orders) {
                System.out.println(order);
                order.deleteFromDatabase();
            }
        }

        Document customerQuery = new Document("roomNumber", roomNumber);
        customerCollection.deleteOne(customerQuery);
    }

    private void saveCustomerToDatabase(Customer customer) {
        MongoDatabase database = MongoDBConnection.getInstance().getDatabase();
        MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

        Document customerDoc = new Document("name", customer.getName())
                .append("id", customer.getId())
                .append("phoneNumber", customer.getPhoneNumber())
                .append("address", customer.getAddress())
                .append("roomNumber", customer.getRoomNumber());

        collection.insertOne(customerDoc);
    }
}
