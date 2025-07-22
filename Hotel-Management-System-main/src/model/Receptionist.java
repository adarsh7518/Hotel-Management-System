package model;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class Receptionist {
    private MongoCollection<Document> receptionistCollection;

    private String complaint;
    public Receptionist(String complaint) {
       this.complaint=complaint;
    }

    public void recordComplaint(String complaint) {
        try {
            Document complaintDoc = new Document("complaint", complaint);
            receptionistCollection.insertOne(complaintDoc);
        } catch (Exception e) {
            System.err.println("Error recording complaint: " + e.getMessage());
        }
    }
}
