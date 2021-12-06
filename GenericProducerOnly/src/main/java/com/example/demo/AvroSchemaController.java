package com.example.demo;


import java.io.FileWriter;
import java.io.IOException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.MySchema;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class AvroSchemaController{
	
    @PostMapping("/getavroschema")
    public String  getAvroSchema(@RequestBody Object user) throws IOException{
    	System.out.println("getAvroSchema method is working");
    	String avroschema =GenerateSchema.generateSchema(user);
//    	System.out.println("AVRO SCHEMA IN CONTROLLER:\n"+avroschema);

    	try {
			FileWriter writer=new FileWriter("src//main//resources//avro//MySchema.avsc"); //over writing is taking place
			ObjectMapper mapper=new ObjectMapper();
			//json to java conversion
			Object javaObject=mapper.readValue(avroschema,Object.class);
			//java to json conversion
			String formattedSchema=mapper.writerWithDefaultPrettyPrinter().writeValueAsString(javaObject);
			writer.write(formattedSchema);
			writer.flush();
			writer.close();
			System.out.println("writing success");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return "request call success avro schema generated successfully";	
    	
    	
    	
    	
    }
    @PostMapping("/sendjsondata")
    public String  publishJsonData(@RequestBody MySchema jsonData) throws IOException{
//    	System.out.println("publishJsonData method is working");
    	System.out.println("JSON DATA RECEIVED:"+jsonData);
    	KafkaAvroJavaProducerV1Demo.producer(jsonData);
    	
    	
    	return "producer called json data published";

}
}

