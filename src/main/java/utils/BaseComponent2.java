package utils;

import org.testng.annotations.BeforeClass;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import testdata.DataBuilder;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
public class BaseComponent2 {

	public static RequestSpecification requestSpec;
	public static ResponseSpecification responseSpec;
	String token;
	
	@BeforeClass
	public void setup() {
		
		Response response = given().
				contentType(ContentType.JSON).
				body(DataBuilder.buildToken().toJSONString()).
				post("https://dev-todo-b369f85c9f07.herokuapp.com/api/login").
				then().extract().response();
		
		token = response.jsonPath().getString("token");
		
		
		requestSpec = new RequestSpecBuilder().
				setBaseUri("https://dev-todo-b369f85c9f07.herokuapp.com/").
				setBasePath("api/").
				setContentType(ContentType.JSON). 
				addHeader("Authorization", "Bearer " + token).
				addHeader("accept", "application/json").build();
				
		responseSpec = new ResponseSpecBuilder(). 
				expectStatusCode(either(is(200)).or(is(201)).or(is(204))).build();
		
	}
	
	
	public static Response doPostRequest(String body, String path) {
		
		Response resp = 
				given()
					.spec(requestSpec)
					.body(body)
				.when()
					.post(path)
				.then() 
				   .spec(responseSpec)
				   .extract().response();		
		return resp;	
	}
	public static Response doGetRequest(String param) {
		
		Response resp = 
				given()
				.spec(requestSpec)
				.when()
					.get(param)
				.then() 
				   .spec(responseSpec)
				   .extract().response();		
		return resp;
		
	}
	
	public static Response doPutRequest(String body, String param) {
		
		Response resp = 
				given()
					.spec(requestSpec)
					.body(body)
				.when()
					.put(param)
				.then() 
				   .spec(responseSpec)
				   .extract().response();		
		return resp;
		
	}
	public static Response doDeleteRequest(String param) {
		
		Response resp = 
				given()
					.spec(requestSpec)
				.when()
					.delete(param)
				.then() 
				   .spec(responseSpec)
				   .extract().response();		
		return resp;
		
	}
	
}