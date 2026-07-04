package POSTAPIs;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class OAuth2Test {
	
//	@Test
//	public void getFlightInfoTest() {
//	//1. POST - get the access token
//	RestAssured.baseURI = "https://test.api.amadeus.com";
//
//	String accessToken = given()
//	.header("Content-Type", "application/x-www-form-urlencoded")
//	.formParam("grant_type", "client_credentials")
//	.formParam("client_id", "TAnRnsU5lASXZ8mPGdwRQZMoQzhu6Gwv")
//	.formParam("client_secret", "VjjgfcJilNAzcSJw")
//	.when()
//	.post("/v1/security/oauth2/token")
//	.then()
//	.assertThat()
//	.statusCode(200)
//	.extract().path("access_token");
//
//	System.out.println(accessToken);
	
	
	@Test
	public void getFlightInfoTest() {
		
		RestAssured.baseURI="https://test.api.amadeus.com";
		
		String accessToken=given()
		 .header("Content-Type", "application/x-www-form-urlencoded")
		 .formParam("grant_type", "client_credentials")
			.formParam("client_id", "TAnRnsU5lASXZ8mPGdwRQZMoQzhu6Gwv")
        		.formParam("client_secret", "VjjgfcJilNAzcSJw")
        		   .when()
        		     .post("/v1/security/oauth2/token")
        		       .then()
        		         .assertThat()
        		           .statusCode(200)
        		             .extract().path("access_token");
		
		System.out.println(accessToken);
		
		
	}

	

}
