package SpecificationConcept;

import org.apache.http.client.methods.RequestBuilder;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestBuilderSpecTest {

	public static RequestSpecification user_req_spec() {
		RequestSpecification reqspec = new RequestSpecBuilder()
				                           .setBaseUri("https://gorest.co.in")
				                               .setContentType(ContentType.JSON)
				                                       .addHeader("Authorization", 
				                                    		   "Bearer 811ac3ddea385fb639a89fb166731ada7825060d9d91e6b5072cf3d4dcee35da")
				                                                      .build();

		return reqspec;
	}

	@Test
	public void getUser_With_Request_Spec() {
		RestAssured.given().log().all()
		        .spec(user_req_spec())
		             .get("/public/v2/users/")
		                   .then()
		                       .statusCode(200);

	}

	@Test
	public void getUser_With_Queryparam_Request_Spec() {
		RestAssured.given().log().all()
		      .queryParam("name", "naveen")
		           .queryParam("status", "active")
				        .spec(user_req_spec())
				               .get("/public/v2/users/")
				                     .then()
				                         .statusCode(200);

	}

}
