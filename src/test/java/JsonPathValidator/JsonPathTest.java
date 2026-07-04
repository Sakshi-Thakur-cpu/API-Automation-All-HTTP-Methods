package JsonPathValidator;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

public class JsonPathTest {
	
	@Test
	public void getCircuitDataAPIWith_YearTest() {
	RestAssured.baseURI = "http://ergast.com";

	Response response = given(). log().all()
	.when (). log().all()
	.get("/api/f1/2017/circuits.json");
	
	String jsonResponse=response.asString();
	System.out.println(jsonResponse);
	
	int totalCircuitLength= com.jayway.jsonpath.JsonPath.read(jsonResponse, "$.MRData.CircuitTable.Circuits. length( )");
	System.out.println(totalCircuitLength);
	
	
//	int totalCircwits = JsonPath. read[jsonResponse, "$.MRData.CircuitTable.Circuits. length( )"];
//	System.oot.println(totalCircuits);
//
//	List<String countryList = JsomPath. read(jsonResponse, "$ .. Circwits. .country");
//	System.out.printin(countryList.size()];
//	System.out.primtin(coumtryList);
	
	
//	7 1. $[*].id
//	8 2. $[*].title
//	9 3. $[*].rating. rate
//	0 4. $[*] .. rate
//	1 5. $[*] .. rate, count
//	2 6. rates which less than 3:
//	$[?(@.rating. rate < 3)]. rating. rate
//
//	7. $[*].title
//	8. fetch the product price where id is 3
//	$[?(@.id == 3)].price
//
//	9 9. fetch titles and prices of the product where category = jewelery
//	$[?ategory == 'jewelery' ) ]. [title, price]
	//List<Map<String,Object>>
//
//	10. Tetch title of the products with rating rate >=4.5
//	$[?(@. rating. rate>=4.5)]. [title]
//
//	11. fetch title of the producs where price is less than 30$
//	$[?(@.price<30)].[title]
//
//	12. fetch title and jewellery category of the products where rate is between 2 and 4:
//	$[?(@. category == 'jewelery' && @. rating. rate >=2 && @. rating. rate <= 4)]. [title, category]
//
//	1 13. fetch title and women's clothing category where rating count >=100 and price <10$
//	$[?(@.category == 'women\'s clothing' && @. rating. count >=100 && @.price < 10)]. [tile, category]


}}
