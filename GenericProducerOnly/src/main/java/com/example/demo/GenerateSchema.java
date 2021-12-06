package com.example.demo;

import org.kitesdk.data.spi.JsonUtil;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Service
public class GenerateSchema {
	public static String generateSchema(Object user) {
		Object user1=user;
		//faking namespace
//		MySchema fakingNamespace=new MySchema();
//		String SCHEMA_NAME=fakingNamespace.getClass().getName();
		String SCHEMA_NAME="MySchema";
		System.out.println("JAVA OBJECT:\n" + user1);
		
		//java to json 
		Gson gson=new GsonBuilder().setPrettyPrinting().create();
		String jsonString =gson.toJson(user1);
		System.out.println("JSON STRING :\n"+ jsonString);

		
		//using 3rd party tool kitesdk
	    String avroSchema = JsonUtil.inferSchema(JsonUtil.parse(jsonString),SCHEMA_NAME).toString();
	    System.out.println("AVRO SCHEMA IN STRING:\n"+ avroSchema);
	    
	
		
	    
	    return avroSchema;
			
	}

}
