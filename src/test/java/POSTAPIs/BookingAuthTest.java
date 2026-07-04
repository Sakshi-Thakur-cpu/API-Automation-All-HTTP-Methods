package POSTAPIs;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

import org.testng.annotations.Test;

public class BookingAuthTest {
	
	
	@Test
	public void getBookingAuthTokenTest_With_JSON_String() {
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		
		String tokenID=given()
		  .contentType(ContentType.JSON)
		    .body("{\r\n"
		    		+ "    \"username\": \"admin\",\r\n"
		    		+ "    \"password\": \"password123\"\r\n"
		    		+ "}")
		    .when()
		      .post("/auth")
		        .then()
		           .assertThat()
		              .statusCode(200)
		                 .extract()
		                    .path("token");
		
		System.out.println(tokenID);
		      
	}
	
	@Test
	public void getBookingAuthTokenTest_With_JSON_File() {
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		
		String tokenID=given()
		  .contentType(ContentType.JSON)
		    .body(new File("./src/test/resources/testData/basicauth.json"))
		    .when()
		      .post("/auth")
		        .then()
		           .assertThat()
		              .statusCode(200)
		                 .extract()
		                    .path("token");
		
		System.out.println(tokenID);
		      
	}
	
	@Test
	public void addUserTest() {
		RestAssured.baseURI = "https://gorest.co.in";
		
		int userId= given().log().all()
		  .contentType(ContentType.JSON)
		    .body(new File("./src/test/resources/testData/addUser.json"))
		    .header("Authorization", "Bearer 811ac3ddea385fb639a89fb166731ada7825060d9d91e6b5072cf3d4dcee35da")
		    .when().log().all()
		    .post("/public/v2/users/")
		    .then().log().all()
		    .assertThat()
		    .statusCode(201)
		    .and()
		    .body("name", equalTo("Sakshi"))
		    .extract()
		    .path("id");
		
		
		System.out.println("user id--->" +userId);
		
		//get the same user and verify it : GET
		given().log().all()
		  .header("Authorization", "Bearer 811ac3ddea385fb639a89fb166731ada7825060d9d91e6b5072cf3d4dcee35da")
		     .when().log().all()
		        .get("/public/v2/users/"+userId)
		           .then().log().all()
		              .assertThat()
		                 .statusCode(200)
		                    .body("id", equalTo(userId));
		
		
		    
		    
	}
}
