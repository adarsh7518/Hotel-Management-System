package model;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class Manager {
    private static final String COLLECTION_NAME = "managers";

    private static Manager instance;

    private MongoCollection<Document> managerCollection;

    // Private constructor to prevent instantiation outside this class
    private Manager() {
        MongoDatabase database = MongoDBConnection.getInstance().getDatabase();
        this.managerCollection = database.getCollection(COLLECTION_NAME);
    }

    // Method to provide access to the Manager instance
    public static synchronized Manager getInstance() {
        if (instance == null) {
            instance = new Manager();
        }
        return instance;
    }

    public void recordComplaint(String receptionistName, String complaint) {
        Document complaintDoc = new Document("receptionistName", receptionistName)
                .append("complaint", complaint);
        managerCollection.insertOne(complaintDoc);
    }

    public List<Document> viewComplaints() {
        List<Document> complaints = new ArrayList<>();
        for (Document doc : managerCollection.find()) {
            complaints.add(doc);
        }
        return complaints;
    }
}
