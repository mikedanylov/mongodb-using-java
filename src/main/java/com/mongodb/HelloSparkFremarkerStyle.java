package com.mongodb;

import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Spark;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mikedanylov on 1/5/16.
 */
public class HelloSparkFremarkerStyle {
    public static void main(String[] args) {
        final Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(HelloWorldFreemarkerStyle.class, "/");
        Spark.get("/", (req, res) -> {
            StringWriter writer = new StringWriter();
            try {
                Template helloTemplate = configuration.getTemplate("hello.ftl");
                Map<String, Object> helloMap = new HashMap<String, Object>();
                helloMap.put("name", "Freemarker");

                helloTemplate.process(helloMap, writer);

                System.out.println(writer);

            } catch (Exception e) {
                Spark.halt(500);
                e.printStackTrace();
            }
            return writer;
        });
    }
}
