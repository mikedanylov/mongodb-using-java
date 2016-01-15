package com.mongodb;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;


/**
 * Created by mikedanylov on 1/15/16.
 */
public class FindWithProjectionTest {
    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("course");
        MongoCollection<Document> collection = db.getCollection("findWithFilterTest");

        collection.drop();

        // insert 10 documents with two random integers
        for (int i = 0; i < 10; i++) {
            collection.insertOne(new Document()
                                .append("x", new Random().nextInt(2))
                                .append("y", new Random().nextInt(100))
                                .append("i", i));
        }

//        Bson filter = new Document("x", 0)
//                .append("y", new Document("$gt", 10).append("$lt", 90));

        Bson filter = and(eq("x", 0), gt("y", 10), lt("y", 90));

        Bson projection = fields(include("y", "i"), exclude("_id"));

        List<Document> all = collection.find(filter)
                                    .projection(projection)
                                    .into(new ArrayList<Document>());

        for (Document cur : all) {
            Helpers.printJson(cur);
        }

    }
}
