package com.user.api;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateUserTestWithLambok {
	
	public static String getRandomEmailID() {
		return "apiautomation"+System.currentTimeMillis()+"@mail.com";
	}
	
	@Test
	public void createUserTest() {
		RestAssured.baseURI="https://gorest.co.in";
		
		User user=new User("Sakshi",getRandomEmailID(),"female","active");
		
	Response response=	RestAssured.given()
		  .contentType(ContentType.JSON)
		   .header("Authorization", "Bearer 811ac3ddea385fb639a89fb166731ada7825060d9d91e6b5072cf3d4dcee35da")
		       .body(user)
		         .when()
		           .post("/public/v2/users/");
	
	 Integer userID= response.jsonPath().get("id");
	 System.out.println(userID);
	 
	 //get the same user and verify with GET
	 
	 Response getResponse= given().log().all()
	  .header("Authorization", "Bearer 811ac3ddea385fb639a89fb166731ada7825060d9d91e6b5072cf3d4dcee35da")
	     .when().log().all()
	        .get("/public/v2/users/"+userID);
	 
	 //deserialization
	 ObjectMapper mapper=new ObjectMapper();
	  try {
		User userResponse= mapper.readValue(getResponse.getBody().asString(), User.class);
		System.out.println(userResponse.getId()+":" + userResponse.getEmail() + ":" + userResponse.getGender()
		+":"+userResponse.getName()+":"+userResponse.getStatus());
		
		Assert.assertEquals(userID, userResponse.getId());
		Assert.assertEquals(user.getEmail(), userResponse.getEmail());
		Assert.assertEquals(user.getName(), userResponse.getName());
		Assert.assertEquals(user.getGender(), userResponse.getGender());
		Assert.assertEquals(user.getStatus(), userResponse.getStatus());

	} catch (JsonMappingException e) {
		e.printStackTrace();
	} catch (JsonProcessingException e) {
		e.printStackTrace();
	}
	 
		             
	}
	
	
	@Test
	public void createUser_withBuilder_Test() {
		RestAssured.baseURI="https://gorest.co.in";
		
	User user=	new User.UserBuilder()
		  .name("SakshiThakur")
		    .email(getRandomEmailID())
		      .status("active")
		        .gender("female")
		          .build();
		
	Response response=	RestAssured.given()
		  .contentType(ContentType.JSON)
		   .header("Authorization", "Bearer 811ac3ddea385fb639a89fb166731ada7825060d9d91e6b5072cf3d4dcee35da")
		       .body(user)
		         .when()
		           .post("/public/v2/users/");
	
	 Integer userID= response.jsonPath().get("id");
	 System.out.println(userID);
	 
	 //get the same user and verify with GET
	 
	 Response getResponse= given().log().all()
	  .header("Authorization", "Bearer 811ac3ddea385fb639a89fb166731ada7825060d9d91e6b5072cf3d4dcee35da")
	     .when().log().all()
	        .get("/public/v2/users/"+userID);
	 
	 //deserialization
	 ObjectMapper mapper=new ObjectMapper();
	  try {
		User userResponse= mapper.readValue(getResponse.getBody().asString(), User.class);
		System.out.println(userResponse.getId()+":" + userResponse.getEmail() + ":" + userResponse.getGender()
		+":"+userResponse.getName()+":"+userResponse.getStatus());
		
		Assert.assertEquals(userID, userResponse.getId());
		Assert.assertEquals(user.getEmail(), userResponse.getEmail());
		Assert.assertEquals(user.getName(), userResponse.getName());
		Assert.assertEquals(user.getGender(), userResponse.getGender());
		Assert.assertEquals(user.getStatus(), userResponse.getStatus());

	} catch (JsonMappingException e) {
		e.printStackTrace();
	} catch (JsonProcessingException e) {
		e.printStackTrace();
	}
	 
		             
	}


}
