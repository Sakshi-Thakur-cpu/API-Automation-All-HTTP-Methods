package PUTAPIs;

import org.testng.annotations.Test;

import com.user.api.User;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;

public class UpdateUser {
	
	public static String getRandomEmailID() {
		return "apiautomation"+System.currentTimeMillis()+"@mail.com";
	}
	
	@Test
	public void updateUserTest() {
		RestAssured.baseURI = "https://gorest.co.in";

		User user = new User("Sakshi", getRandomEmailID(), "female", "active");

		//1.Post call to create user
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.header("Authorization", "Bearer 811ac3ddea385fb639a89fb166731ada7825060d9d91e6b5072cf3d4dcee35da")
				.body(user).when().post("/public/v2/users/");

		Integer userID = response.jsonPath().get("id");
		System.out.println(userID);
		
		System.out.println("------------------");
		
		//2.PUT call: to update user 
		
		user.setName("sdsfdsf");
		user.setStatus("inactive");
		
		RestAssured.given().contentType(ContentType.JSON)
		.header("Authorization", "Bearer 811ac3ddea385fb639a89fb166731ada7825060d9d91e6b5072cf3d4dcee35da")
		  .body(user)
		    .when()
		       .put("/public/v2/users/"+userID)
		          .then().log().all()
		             .assertThat()
		                .statusCode(200)
		                  .and()
		                     .body("id", equalTo(userID))
		                       .and()
		                         .body("name", equalTo(user.getName()))
		                           .and()
		                              .body("status", equalTo(user.getStatus()));

		

	}

}
