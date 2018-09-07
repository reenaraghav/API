package com.api.common;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;

public class GenericWrappers {

	/*
	 * Method to read property file and returns the value
	 * 
	 * @param key- key from property file
	 */
	public String getPropertyValue(String key) throws IOException{
		Properties prop=new Properties();
		String value="";
		try{
			
			prop.load(new FileInputStream(new File("./src/main/resources/config/project.properties")));
			value= prop.getProperty(key);
			
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
		return value;
	}
	
	public static Response doGetRequest(String basePath ) {
        RestAssured.defaultParser = Parser.JSON;
        return
                given().headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON).
                        when().get(basePath).
                        then().log().everything().contentType(ContentType.JSON).extract().response();
    }
}
