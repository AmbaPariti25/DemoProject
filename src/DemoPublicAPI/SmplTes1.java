package DemoPublicAPI;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
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
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONObject;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class SmplTes1 {

	String id;
	String baseUrl = "https://rahulshettyacademy.com/maps/api/place";

	@Test

	public void postdata() {

		File File = new File(
				"C:\\NexientAutomation_Amba\\test-automation-framework\\demoproject\\src\\files\\payload.json");
		String resourceUrl = "/add/json?key=qaclick123";

		String postResponse = RestAssured.given().log().all().contentType(ContentType.JSON).body(File)
				.post(baseUrl + resourceUrl).then().statusCode(200).extract().response().asString();

		System.out.println(postResponse);
		String newAddress = "Summer Walk, Africa";

		JsonPath js = new JsonPath(postResponse);
		String placeId = js.getString("place_id");
		System.out.println(placeId);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("place_id", placeId);
		jsonObject.put("address", newAddress);
		jsonObject.put("key", "qaclick123");
		
		try {
			FileWriter file = new FileWriter(
					"C:\\NexientAutomation_Amba\\test-automation-framework\\demoproject\\src\\files\\update.json");
			file.write(jsonObject.toJSONString());
			file.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
		System.out.println("JSON file created: " + jsonObject);
	}

	@Test
	public void getdata() throws Exception {
		
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(
				"C:\\NexientAutomation_Amba\\test-automation-framework\\demoproject\\src\\files\\update.json"));
		String placeid = (String) jsonObject.get("place_id");
		String key = (String) jsonObject.get("key");
		System.out.println(placeid);
		String resourceParam = "/get/json";
		RestAssured.baseURI = baseUrl;

		String getResponse = given().log().all().param("place_id", placeid).param("key", key).when().get(resourceParam)
				.then().log().all().assertThat().statusCode(200).extract().response().asString();
		System.out.println(getResponse);

	}

	@Test
	public void putdata() throws Exception {

		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(
				"C:\\NexientAutomation_Amba\\test-automation-framework\\demoproject\\src\\files\\update.json"));
		String placeid = (String) jsonObject.get("place_id");
		String key = (String) jsonObject.get("key");

		File File = new File(
				"C:\\NexientAutomation_Amba\\test-automation-framework\\demoproject\\src\\files\\update.json");

		RestAssured.baseURI = baseUrl;
		String resourceParam = "/update/json";
		
		String responseForPost = given().log().all().param("place_id", placeid).param("key", key)
				.contentType(ContentType.JSON).body(File).put(resourceParam).then().log().all().assertThat()
				.statusCode(200).extract().response().asString();

		System.out.println(responseForPost);
	}

	@Test
	public void deleteData() throws Exception {

		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(
				"C:\\NexientAutomation_Amba\\test-automation-framework\\demoproject\\src\\files\\update.json"));
		String placeid = (String) jsonObject.get("place_id");

		System.out.println(placeid);

		File File = new File(
				"C:\\NexientAutomation_Amba\\test-automation-framework\\demoproject\\src\\files\\update.json");
		RestAssured.baseURI = baseUrl;
		String resourceParam = "/delete/json";
		
		String responseForPost = given().log().all().param("place_id", placeid).contentType(ContentType.JSON)
				.body("place_id").delete(resourceParam).then().log().all().assertThat().statusCode(200).extract()
				.response().asString();

		System.out.println(responseForPost);
	}
}
