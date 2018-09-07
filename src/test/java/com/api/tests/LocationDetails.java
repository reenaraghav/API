package com.api.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.api.common.ProjectWrappers;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

import java.util.Map;

import io.restassured.parsing.Parser;
import io.restassured.response.Response;

public class LocationDetails extends ProjectWrappers{

	String basePath="api/Location/LocationDetails";
	@BeforeClass
	public void setUp(){
		try{
			RestAssured.baseURI=getPropertyValue("baseURI");
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void getLocations(){
		Response response=doGetRequest(basePath);
		Assert.assertEquals(response.getStatusCode(), 200);
		//for individual node??
		Map<String,Object> msg=response.jsonPath().getMap("Msg");
		Assert.assertEquals(msg.get("Message"), "Success Location Details");
		
	}
}
