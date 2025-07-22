package model;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoDBConnection {
    private static final String DATABASE_NAME = "HotelDB";
    private static final String CONNECTION_STRING = "mongodb://localhost:27017/" + DATABASE_NAME;
    private static MongoDBConnection instance;
    private MongoClient mongoClient;
    private MongoDatabase database;

    // Private constructor to prevent instantiation outside this class
    private MongoDBConnection() {
        // Create a MongoClient instance using the connection string
        this.mongoClient = MongoClients.create(CONNECTION_STRING);
        // Get the MongoDatabase instance for the specified database name
        this.database = mongoClient.getDatabase(DATABASE_NAME);
    }

    // Method to provide access to the MongoDBConnection instance
    public static synchronized MongoDBConnection getInstance() {
        if (instance == null) {
            instance = new MongoDBConnection();
        }
        return instance;
    }

    // Method to get the MongoDatabase instance
    public MongoDatabase getDatabase() {
        return this.database;
    }

    // Method to close the MongoClient connection
    public void closeConnection() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }
}
