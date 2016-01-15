package com.mongodb;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.*;

/**
 * Created by mikedanylov on 1/15/16.
 */
public class UpdateTest {
    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("course");
        MongoCollection<Document> collection = db.getCollection("findWithFilterTest");

        collection.drop();

        // insert 10 documents with two random integers
        for (int i = 0; i < 8; i++) {
            collection.insertOne(new Document()
                    .append("_id", i)
                    .append("x", i));
        }

//        collection.replaceOne(eq("x", 5), new Document("_id", 5).append("x", 20)
//                                                                .append("update", true));

//        collection.updateOne(eq("_id", 9), new Document("$set", new Document("x", 20)),
//                new UpdateOptions().upsert(true));

        collection.updateMany(gte("_id", 5), new Document("$inc", new Document("x", 10)));

        for (Document cur : collection.find().into(new ArrayList<Document>())) {
            Helpers.printJson(cur);
        }
    }
}
