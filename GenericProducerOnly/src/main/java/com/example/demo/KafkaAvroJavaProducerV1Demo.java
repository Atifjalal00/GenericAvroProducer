package com.example.demo;


import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import com.example.MySchema;

import io.confluent.kafka.serializers.KafkaAvroSerializer;

public class KafkaAvroJavaProducerV1Demo {

    public static void producer(MySchema jsonData) {
    	System.out.println("PRODUCER STARTED:\n");
        Properties properties = new Properties();
        // normal producer
        properties.setProperty("bootstrap.servers", "127.0.0.1:9092");
        properties.setProperty("acks", "all");
        properties.setProperty("retries", "10");
        // avro part
        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer", KafkaAvroSerializer.class.getName());
        properties.setProperty("schema.registry.url", "http://127.0.0.1:8081");

        KafkaProducer<String, MySchema> producer = new KafkaProducer<String, MySchema>(properties);

        String topic = "avrotest";

        // copied from avro examples
       /* MySchemaV1  jsonData = MySchemaV1.newBuilder()
                .setAge(34)
                .setAutomatedEmail(false)
                .setFirstName("basker")
                .setLastName("naveen")
                .setHeight(160f)
                .setWeight(75f)
                .build();*/
        
        
        //easy way to create hard corded objects
      /* List<String> list=new ArrayList<>();
       list.add("red");
       list.add("green");
        MySchemaV1 jsonData=new MySchemaV1("bharath kumar",34,60000.9,true,list);*/
        
        
        //best and dynamic way to create object
        MySchema jsonDataToPublish=jsonData;
        

        ProducerRecord<String, MySchema> producerRecord = new ProducerRecord<String, MySchema>(
                topic, jsonDataToPublish
        );

//        System.out.println(jsonData);
        producer.send(producerRecord, new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                if (exception == null) {
                    System.out.println("META DATA AFTER SUCCESSFULL PUBLICATION : "+metadata);
                } else {
                    exception.printStackTrace();
                }
            }
        });

        producer.flush();
        producer.close();

    }
}
