package SpecificationConcept;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ResponseBuilderSpecTest {
	
	public static ResponseSpecification get_res_spec_200_ok() {
	  ResponseSpecification response_spec_200ok=	new ResponseSpecBuilder()
		  .expectContentType(ContentType.JSON)
		    .expectStatusCode(200)
		      .expectHeader("Server", "cloudflare")
		         .build();
		  
	  return response_spec_200ok;
	}
	
	public static ResponseSpecification get_res_spec_401_Auth_Fail() {
		  ResponseSpecification response_spec_401_authFail=	new ResponseSpecBuilder()
			  .expectContentType(ContentType.JSON)
			    .expectStatusCode(401)
			      .expectHeader("Server", "cloudflare")
			         .build();
			  
		  return response_spec_401_authFail;
		}
	
	public static ResponseSpecification get_res_spec_200_ok_With_body() {
		  ResponseSpecification response_spec_200ok=	new ResponseSpecBuilder()
			  .expectContentType(ContentType.JSON)
			    .expectStatusCode(200)
			      .expectHeader("Server", "cloudflare")
			        .expectBody("$.size()", equalTo(10))
			          .expectBody("id", hasSize(10))
			            .build();
			  
		  return response_spec_200ok;
		}
	
	@Test
	public void get_user_res_200_spec_test() {
		RestAssured.baseURI="https://gorest.co.in";
		 given()
		 .header("Authorization", "Bearer 811ac3ddea385fb639a89fb166731ada7825060d9d91e6b5072cf3d4dcee35da")
		 .when().log().all()
	        .get("/public/v2/users/")
	           .then().log().all()
	              .assertThat()
	                .spec(get_res_spec_200_ok_With_body());
		   
	}
	
	@Test
	public void get_user_res_401_Auth_fail_spec_test() {
		RestAssured.baseURI="https://gorest.co.in";
		 given()
		 .header("Authorization", "Bearer 811ac3ddea385fb639a89fb166731ada7825060d9d91e6b5072cf3d4dcee35da90")
		 .when().log().all()
	        .get("/public/v2/users/")
	           .then().log().all()
	              .assertThat()
	                .spec(get_res_spec_401_Auth_Fail());
		   
	}

}
