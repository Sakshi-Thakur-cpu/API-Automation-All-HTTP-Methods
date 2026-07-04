package ProductAPI;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.http.ContentType;

import io.restassured.response.Response;

//GET ---- Request(X) --- > Response(JSON) -- > POJO
//- NO POJO (request)
//- P0JO (response)
//
//POST ---- Request(JSON) ---- > Response(JSON)
//- POJO -- >JSON => Serialization/Marshelling (non static)
//- JSON -- >POJO > De-Serialization/UnMarshelling
//Jackon-databind lib

public class ProductAPITest {
	
	@Test
	public void getProductTestPojo() {
		
	Response response=	given().log().all()
		   .when().log().all()
		      .get("https://fakestoreapi.com/products");
	
	//json to POJO mapping-deserialization
	
	ObjectMapper mapper=new ObjectMapper();
	
	 try {
		Product product[]= mapper.readValue(response.getBody().asString(), Product[].class);
		
		for(Product p: product) {
			System.out.println("ID:"+p.getId());
			System.out.println("title:"+p.getTitle());
			System.out.println("price:"+p.getPrice());
			System.out.println("description:"+p.getDescription());
			System.out.println("category:"+p.getCategory());
			System.out.println("image:"+p.getimage());
			System.out.println("rate:"+p.getRating().getRate());
			System.out.println("rate:"+p.getRating().getCount());
			System.out.println("----------------");
		}
	} catch (JsonMappingException e) {
		e.printStackTrace();
	} catch (JsonProcessingException e) {
		e.printStackTrace();
	}
		         
		
	}
	
	
	@Test
	public void getProductTestPojoLambok() {
		
	Response response=	given().log().all()
		   .when().log().all()
		      .get("https://fakestoreapi.com/products");
	
	//json to POJO mapping-deserialization
	
	ObjectMapper mapper=new ObjectMapper();
	
	 try {
		 ProductLambok product[]= mapper.readValue(response.getBody().asString(), ProductLambok[].class);
		
		for(ProductLambok p: product) {
			System.out.println("ID:"+p.getId());
			System.out.println("title:"+p.getTitle());
			System.out.println("price:"+p.getPrice());
			System.out.println("description:"+p.getDescription());
			System.out.println("category:"+p.getCategory());
			System.out.println("image:"+p.getImage());
			System.out.println("rate:"+p.getRating().getRate());
			System.out.println("rate:"+p.getRating().getCount());
			System.out.println("----------------");
		}
	} catch (JsonMappingException e) {
		e.printStackTrace();
	} catch (JsonProcessingException e) {
		e.printStackTrace();
	}
		         
		
	}

}
