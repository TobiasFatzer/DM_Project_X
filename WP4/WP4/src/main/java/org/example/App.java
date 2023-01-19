package org.example;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;

public class App {
    public static void main(String[] args) {

        MongoClient mongoClient = new MongoClient(
                new MongoClientURI(
                        "mongodb+srv://admin:Root1234!@cluster0.xbt48zk.mongodb.net/test"
                )
        );

        MongoDatabase database = mongoClient.getDatabase("Project_X");
        MongoCollection<Document> collection = database.getCollection("Assessment");

        ArrayList<Document> results = collection.aggregate(Arrays.asList(new Document("$lookup",
                        new Document("from", "Assessment")
                                .append("localField", "performance_id")
                                .append("foreignField", "performance_id")
                                .append("as", "b2_documents")),
                new Document("$unwind", "$b2_documents"),
                new Document("$project",
                        new Document("_id", 0L)
                                .append("team_1", "$team_id")
                                .append("team_2", "$b2_documents.team_id")
                                .append("cmp_value",
                                        new Document("$cmp", Arrays.asList("$team_id", "$b2_documents.team_id")))),
                new Document("$match",
                        new Document("cmp_value",
                                new Document("$lt", 0L))),
                new Document("$project",
                        new Document("team_1", "$team_1")
                                .append("team_2", "$team_2")),
                new Document("$sort",
                        new Document("team_1", 1L)))).into(new ArrayList<>());

        for (Document result : results) {
            System.out.println(result.get("team_1") + ";" + result.get("team_2"));
        }


    }

}
