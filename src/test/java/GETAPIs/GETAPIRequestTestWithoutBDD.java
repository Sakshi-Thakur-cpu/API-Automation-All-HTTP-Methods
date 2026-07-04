package GETAPIs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GETAPIRequestTestWithoutBDD {
	RequestSpecification request;
	
	//Non BDD Approach
	
	@BeforeTest
	public void setup() {
		RestAssured.baseURI = "https://gorest.co.in";
		 request = RestAssured.given();// have to give request to this RequestSpecification
		// once request is set I have to send other params to this request
		request.header("Authorization", "Bearer 811ac3ddea385fb639a89fb166731ada7825060d9d91e6b5072cf3d4dcee35da");
		
	}

	@Test
	public void getUserAPITest() {
		
		// we have to send method type
		Response response = request.get("/public/v2/users/");

		// once we hit the request we will get the status code
		int statuscode = response.statusCode();
		System.out.println("Status code:" + statuscode);

		// we will write assertion
		Assert.assertEquals(statuscode, 200);

		// Status message
		String statusMesg = response.statusLine();
		System.out.println(statusMesg);

		// status body
		response.prettyPrint();

		// fetch headers
		String contentType = response.header("Content-Type");
		System.out.println(contentType);

		// fetch all headers size and all list
		List<Header> headersList = response.headers().asList();
		System.out.println(headersList.size());

		for (Header header : headersList) {
			System.out.println(header.getName() + " : " + header.getValue());
		}

	}

	@Test
	public void getUserWithQueryParamAPITest() {
		// add query param
		// you can add individual and with map as well
		request.queryParam("name", "naveen");
		request.queryParam("status", "active");
		// once request is set I have to send other params to this request
		// we have to send method type
		Response response = request.get("/public/v2/users/");

		// once we hit the request we will get the status code
		int statuscode = response.statusCode();
		System.out.println("Status code:" + statuscode);

		// we will write assertion
		Assert.assertEquals(statuscode, 200);

		// Status message
		String statusMesg = response.statusLine();
		System.out.println(statusMesg);

		// status body
		response.prettyPrint();

	}
	
	@Test
	public void getUserWithQueryParamWithHashMapAPITest() {
		// add query param
		// you can add individual and with map as well
		Map<String, String> queryParam=new HashMap<String, String>();
		queryParam.put("name", "naveen");
		queryParam.put("status", "active");
		
		request.queryParams(queryParam);
		
		// we have to send method type
		Response response = request.get("/public/v2/users/");

		// once we hit the request we will get the status code
		int statuscode = response.statusCode();
		System.out.println("Status code:" + statuscode);

		// we will write assertion
		Assert.assertEquals(statuscode, 200);

		// Status message
		String statusMesg = response.statusLine();
		System.out.println(statusMesg);

		// status body
		response.prettyPrint();

	}

}
