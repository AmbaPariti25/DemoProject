package DemoPublicAPI;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.RestAssured;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import io.restassured.http.ContentType; 
import static org.testng.Assert.assertEquals;
import java.io.File;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;


public class SampleE2E {
	
	Response responseForPost;
	String id; 
	String baseUrl = "https://rahulshettyacademy.com/maps/api/place";
/*@Test
	public void getdata() {
		String resourceParam = "/get/json";
RestAssured.baseURI = baseUrl;

ValidatableResponse getResponse =  given().log().all().param("place_id", "43d391c91b3d3c5f850d416e01b4fab8").param("key","qaclick123").
when().
get(resourceParam).then().log().all().assertThat().statusCode(200);
  
	}*/
	
	@Test
	
	public void postdata() {
	//	String resourceParam = "/maps/api/place/add/json"; 
		File File = new File ("C:\\NexientAutomation_Amba\\test-automation-framework\\demoproject\\src\\files\\payload.json");
		String resourceUrl = "/add/json?key=qaclick123";
		//RestAssured.baseURI = baseUrl + resourceUrl;
		ValidatableResponse getResponse = RestAssured.given().log().all().contentType(ContentType.JSON).body(File)
.post(baseUrl + resourceUrl).then().log().all().assertThat().statusCode(200);


/*.statusCode(200)
.extract()
.response();*/

		/*int stCode = getResponse.getStatusCode();
		System.out.println(stCode);
		Assert.assertEquals(stCode, 200);
		System.out.println(getResponse);*/
		
	}
	
	@Test
	public void putdata() {
		String resourceUrl = "/update/json?place_id=c02aa19c82a1d3fb954d1beeb134fc0f&key=qaclick123"; 
	File File = new File ("C:\\NexientAutomation_Amba\\test-automation-framework\\demoproject\\src\\files\\updateRequest.json");
		//RestAssured.baseURI = baseUrl + resourceUrl;	
		
		ValidatableResponse responseForPost = RestAssured.given().log().all().contentType(ContentType.JSON).body(File)
				.post(baseUrl + resourceUrl).then().log().all().assertThat().statusCode(200);
	
	}
	
	@Test
	public void deleteData() {
		String resourceUrl = "/delete/json?&key=qaclick123"; 
		//RestAssured.baseURI = baseUrl + resourceUrl;
		File File = new File ("C:\\NexientAutomation_Amba\\test-automation-framework\\demoproject\\src\\files\\delete.json");
		ValidatableResponse responseForPost = RestAssured.given().log().all().contentType(ContentType.JSON).body(File)
				.post(baseUrl + resourceUrl).then().log().all().assertThat().statusCode(200);
	}

}
