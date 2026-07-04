package GETAPIs;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import java.util.List;

public class GETAPIRequestWithBDD {
	
	@Test
	public void getProductTest() {
		
		given().log().all()
		   .when().log().all()
		      .get("https://fakestoreapi.com/products")
		         .then().log().all()
		            .assertThat()
		               .statusCode(200)
		                 .and()
		                   .contentType(ContentType.JSON)
		                     .and()
		                       .body("$.size()", equalTo(20))
		                         .and()
		                           .body("id", is(notNullValue()));
		
	}
	
	@Test
	public void getUserAPITest() {
		RestAssured.baseURI = "https://gorest.co.in";
		given().log().all()
		  .header("Authorization", "Bearer 811ac3ddea385fb639a89fb166731ada7825060d9d91e6b5072cf3d4dcee35da")
		     .when().log().all()
		        .get("/public/v2/users/")
		           .then().log().all()
		              .assertThat()
		                 .statusCode(200)
		                   .and()
		                     .contentType(ContentType.JSON);
		      
	}
	
	@Test
	public void getProductDataAPIWithQueryparam() {
		RestAssured.baseURI = "https://fakestoreapi.com";
		
		given().log().all()
		  .queryParam("limit", 5)
		     .when().log().all()
		        .get("/products")
		            .then().log().all()
		                 .assertThat()
                            .statusCode(200)
                                .and()
                                   .contentType(ContentType.JSON);
			
	}
	
	@Test
	public void getProductDataAPIWithExtractBody() {
		RestAssured.baseURI = "https://fakestoreapi.com";
		
		 Response response= given().log().all()
		                        .queryParam("limit", 5)
		                            .when().log().all()
		                               .get("/products");
		        
		        JsonPath js=response.jsonPath();
		 
		   int firstProductId=js.getInt("[0].id");
		   System.out.println("first product id is:"+firstProductId);
		   
		   String firstProductTitle=js.getString("[0].title");
		   System.out.println("first product title is"+firstProductTitle);
		
		   Float price=  js.getFloat("[0].price");
		   System.out.println("price is:"+ price);
		   
		   int ratingCount= js.getInt("[0].rating.count");
		   System.out.println("rating count is:"+ ratingCount);
	}
	
	@Test
	public void getProductDataAPIWithExtractBodyWithArray() {
		RestAssured.baseURI = "https://fakestoreapi.com";
		
		 Response response= given().log().all()
		                        .queryParam("limit", 10)
		                            .when().log().all()
		                               .get("/products");
		        
		        JsonPath js=response.jsonPath();
		 
		   List<Integer> idList=js.getList("id");
		   System.out.println(idList.size());
		   
		   List<String>  idTitle= js.getList("title");
		   System.out.println(idTitle);
		   
		   List<Float>   ratingList= js.getList("rating.rate",Float.class);
		   System.out.println(ratingList);
		   
		   for(int i=0;i<idList.size();i++) {
			   int id=idList.get(i);
			   String title=idTitle.get(i);
			   Float Rating=ratingList.get(i);
			   
			   System.out.println("ID:"+ id+ "title is: " + title + "Rating is:"+ Rating);
					 
		   }
	}
	
	@Test
	public void getUserAPIWith_Extract_Body_With_OnlyJson() {
		RestAssured.baseURI = "https://gorest.co.in";

		Response response = given().log().all()
				               .queryParam("limit", 5)
				                     .when().log().all()
				                           .get("/public/v2/users/6940961");
		
		   JsonPath js=  response.jsonPath();
		   
		  System.out.println(js.getInt("id"));
		  System.out.println(js.getString("title"));
	}
	
	@Test
	public void getUserAPIWith_Extract_Body_With_Json_Extract() {
		RestAssured.baseURI = "https://gorest.co.in";

		int userId = given().log().all()
				               .queryParam("limit", 5)
				                     .when().log().all()
				                           .get("/public/v2/users/6940961")
				                              .then()
				                                 .extract()
		                                             .path("id");
		   

		System.out.println(userId);
		
		//2nd way
		Response response=  given().log().all()
	                           .queryParam("limit", 5)
                                      .when().log().all()
                                          .get("/public/v2/users/6940961")
                                              .then()
                                                .extract()
                                                    .response();
		
		int userId1=response.path("id");
		String email=response.path("email");
		
		System.out.println(userId1);
		System.out.println(email);
		 
		

	}


}
