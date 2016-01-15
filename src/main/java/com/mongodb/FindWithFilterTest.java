package com.mongodb;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.mongodb.Helpers;
import org.bson.conversions.Bson;

import static com.mongodb.client.model.Filters.*;


/**
 * Created by mikedanylov on 1/15/16.
 */
public class FindWithFilterTest {
    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("course");
        MongoCollection<Document> collection = db.getCollection("findWithFilterTest");

        collection.drop();

        // insert 10 documents with two random integers
        for (int i = 0; i < 10; i++) {
            collection.insertOne(new Document()
                                .append("x", new Random().nextInt(2))
                                .append("y", new Random().nextInt(100)));
        }

//        Bson filter = new Document("x", 0)
//                .append("y", new Document("$gt", 10).append("$lt", 90));

        Bson filter = and(eq("x", 0), gt("y", 10), lt("y", 90));

        List<Document> all = collection.find(filter).into(new ArrayList<Document>());

        for (Document cur : all) {
            com.mongodb.Helpers.printJson(cur);
        }

        long count = collection.count(filter);
        System.out.println();
        System.out.println(count);
    }
}
