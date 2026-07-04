package com.pet.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.text.html.parser.TagElement;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pet.api.Petlambok.Category;
import com.pet.api.Petlambok.Tag;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class CreatepetTest {
	
	@Test
	public void CreatePetTest() {
		RestAssured.baseURI = "https://petstore.swagger.io";

		Category category = new Category(1, "dog");
		List<String> photoUrls = Arrays.asList("https://dog.com", "https://doge.com");
		Tag tag = new Tag(8, "red");
		List<Tag> tags = Arrays.asList(tag);
		Petlambok petlambok = new Petlambok(300, category, "roney", photoUrls, tags, "available");
		
		Response response=  RestAssured.given()
		    .contentType(ContentType.JSON)
		     .body(petlambok)
		     .when()
		       .post("/v2/pet");
		
		System.out.println(response.statusCode());
		response.prettyPrint();
		
		//De-serialization
		ObjectMapper mapper=new ObjectMapper();
		try {
			Petlambok petresponse= mapper.readValue(response.getBody().asString(), Petlambok.class);
			
			System.out.println(petresponse.getId());
			System.out.println(petresponse.getName());
			System.out.println(petresponse.getStatus());
			
			System.out.println(petresponse.getCategory().getId());
			System.out.println(petresponse.getCategory().getName());
			
			System.out.println(petresponse.getPhotoUrls());
			
			System.out.println(petresponse.getTags().get(0).getId());
			System.out.println(petresponse.getTags().get(0).getName());
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void CreatePet_WithBuilder_Pattern_Test() {
		RestAssured.baseURI = "https://petstore.swagger.io";
		
		Category category=new Category.CategoryBuilder()
		  .id(400)
		    .name("ron")
		      .build();
		
		Tag tag= new Tag.TagBuilder()
		  .id(70)
		    .name("tagname")
		      .build();
		
		Petlambok pet=  new Petlambok.PetlambokBuilder()
		  .id(3000)
		    .category(category)
		      .name("ronlik")
		        .photoUrls(Arrays.asList("httpp:jkl.com","htpp:hhhj.com"))
		         .tags(Arrays.asList(tag))
		           .status("available")
		             .build();
		        

//		Category category = new Category(1, "dog");
//		List<String> photoUrls = Arrays.asList("https://dog.com", "https://doge.com");
//		Tag tag = new Tag(8, "red");
//		List<Tag> tags = Arrays.asList(tag);
//		Petlambok petlambok = new Petlambok(300, category, "roney", photoUrls, tags, "available");
		
		Response response=  RestAssured.given()
		    .contentType(ContentType.JSON)
		     .body(pet)
		     .when()
		       .post("/v2/pet");
		
		System.out.println(response.statusCode());
		response.prettyPrint();
		
		//De-serialization
		ObjectMapper mapper=new ObjectMapper();
		try {
			Petlambok petresponse= mapper.readValue(response.getBody().asString(), Petlambok.class);
			
			System.out.println(petresponse.getId());
			System.out.println(petresponse.getName());
			System.out.println(petresponse.getStatus());
			
			System.out.println(petresponse.getCategory().getId());
			System.out.println(petresponse.getCategory().getName());
			
			System.out.println(petresponse.getPhotoUrls());
			
			System.out.println(petresponse.getTags().get(0).getId());
			System.out.println(petresponse.getTags().get(0).getName());
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
