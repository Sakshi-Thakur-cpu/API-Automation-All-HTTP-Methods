package DeleteAPIs;

import org.testng.annotations.Test;

import com.user.api.User;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;

public class DeleteUser {
	
	public static String getRandomEmailID() {
		return "apiautomation"+System.currentTimeMillis()+"@mail.com";
	}
	
	@Test
	public void deleteUserTest() {
		// Post call-201
		// Delete call-204
		// get call-404

		RestAssured.baseURI = "https://gorest.co.in";

		User user = new User("Sakshi", getRandomEmailID(), "female", "active");

		Response response = RestAssured.given().contentType(ContentType.JSON)
				.header("Authorization", "Bearer 811ac3ddea385fb639a89fb166731ada7825060d9d91e6b5072cf3d4dcee35da")
				.body(user).when().post("/public/v2/users/");

		Integer userID = response.jsonPath().get("id");
		System.out.println(userID);

		System.out.println("-------------");

		// 2.Delete API
		RestAssured.given().contentType(ContentType.JSON)
				.header("Authorization", "Bearer 811ac3ddea385fb639a89fb166731ada7825060d9d91e6b5072cf3d4dcee35da")
				.when().log().all().delete("/public/v2/users/" + userID).then().log().all()
				.assertThat().statusCode(204);

		// 3.Get user

		RestAssured.given().contentType(ContentType.JSON)
				.header("Authorization", "Bearer 811ac3ddea385fb639a89fb166731ada7825060d9d91e6b5072cf3d4dcee35da")
				.when().log().all().get("/public/v2/users/" + userID).then().log().all()
				.assertThat().statusCode(404)
				 .and()
				   .assertThat()
				     .body("message", equalTo("Resource not found"));

	}

}
