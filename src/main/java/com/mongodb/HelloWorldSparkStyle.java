package com.mongodb;

import spark.Route;
import spark.Spark;

/**
 * Created by mikedanylov on 1/5/16.
 */
public class HelloWorldSparkStyle {
    public static void main(String[] args) {
        Spark.get("/", (req, res) -> "Hello World From Spark");
    }
}
