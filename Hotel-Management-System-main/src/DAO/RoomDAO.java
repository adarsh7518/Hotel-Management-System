package DAO;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import model.MongoDBConnection;
import org.bson.Document;

public class RoomDAO {

    private MongoCollection<Document> roomCollection;

    public RoomDAO() {
        MongoDatabase database = MongoDBConnection.getInstance().getDatabase();
        this.roomCollection = database.getCollection("rooms");
    }

    public void insertRoomData() {
        // Data from JSON
        Document[] roomData = {
                new Document("roomNumber", 101).append("roomType", "Standard").append("isBooked", false).append("price", 1000),
                new Document("roomNumber", 102).append("roomType", "Deluxe").append("isBooked", false).append("price", 2000),
                new Document("roomNumber", 103).append("roomType", "Suite").append("isBooked", true).append("price", 2500),
                new Document("roomNumber", 104).append("roomType", "Suite").append("isBooked", false).append("price", 2500),
                new Document("roomNumber", 105).append("roomType", "Deluxe").append("isBooked", false).append("price", 2000),
                new Document("roomNumber", 201).append("roomType", "Standard").append("isBooked", false).append("price", 1000),
                new Document("roomNumber", 202).append("roomType", "Standard").append("isBooked", false).append("price", 1000),
                new Document("roomNumber", 203).append("roomType", "Deluxe").append("isBooked", false).append("price", 2000),
                new Document("roomNumber", 204).append("roomType", "Deluxe").append("isBooked", false).append("price", 2000),
                new Document("roomNumber", 205).append("roomType", "Suite").append("isBooked", false).append("price", 2500),
                new Document("roomNumber", 301).append("roomType", "Deluxe").append("isBooked", false).append("price", 2000),
                new Document("roomNumber", 302).append("roomType", "Suite").append("isBooked", false).append("price", 2500),
                new Document("roomNumber", 303).append("roomType", "Suite").append("isBooked", false).append("price", 2500)
        };


        // Insert data into rooms collection
        for (Document room : roomData) {
            roomCollection.insertOne(room);
        }
    }
    public static void main(String[] args) {
        // Instantiate the RoomDAO
        RoomDAO roomDAO = new RoomDAO();

        // Insert room data into the database
        roomDAO.insertRoomData();

        // Display a message indicating successful insertion
        System.out.println("Room data inserted successfully!");
    }
}

