package model;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private int roomNumber;
    private String roomType;
    private int price;
    private boolean isBooked;
    public Room(int roomNumber, String roomType, int price, boolean isBooked) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.price = price;
        this.isBooked = isBooked;
    }

    public static Room getRoomByNumber(int roomNumber) {
        MongoDatabase database = MongoDBConnection.getInstance().getDatabase();
        MongoCollection<Document> collection = database.getCollection("rooms");

        Document query = new Document("roomNumber", roomNumber);
        Document roomDoc = collection.find(query).first();

        if (roomDoc != null) {
            String roomType = roomDoc.getString("roomType");
            int price = roomDoc.getInteger("price");
            boolean isBooked = roomDoc.getBoolean("isBooked");
            return new Room(roomNumber, roomType, price, isBooked);
        } else {
            return null; // Room not found
        }
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBookedStatus(boolean booked) {
        this.isBooked = booked;
    }

    public void updateRoomStatusInDatabase() {
        MongoDatabase database = MongoDBConnection.getInstance().getDatabase();
        MongoCollection<Document> collection = database.getCollection("rooms");

        Document query = new Document("roomNumber", roomNumber);
        Document update = new Document("$set", new Document("isBooked", isBooked));
        collection.updateOne(query, update);
    }

    public static List<Room> getAllAvailableRooms() {
        MongoDatabase database = MongoDBConnection.getInstance().getDatabase();
        MongoCollection<Document> collection = database.getCollection("rooms");

        List<Room> availableRooms = new ArrayList<>();
        for (Document doc : collection.find(new Document("isBooked", false))) {
            int roomNumber = doc.getInteger("roomNumber");
            String roomType = doc.getString("roomType");
            int price = doc.getInteger("price");
            boolean isBooked = doc.getBoolean("isBooked");
            availableRooms.add(new Room(roomNumber, roomType, price, isBooked));
        }
        return availableRooms;
    }

    // Other getters and setters

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getPrice() {
        System.out.println("Price:" + this.price);
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }
}
