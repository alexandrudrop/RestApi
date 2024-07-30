package tests;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertTrue;

import java.util.Map;

public class RestAssuredAuthExample {
	
	//@Test
	public void basicAuth() {
		
		Response response = given().
								contentType(ContentType.JSON).
								auth().preemptive().basic("admin", "password123").
							when().
								delete("https://restful-booker.herokuapp.com/booking/1274").
							then().
								extract().response();
		
		System.out.println(response.asPrettyString());
		
	}
	
	//@Test
	public void formAuth() {
		
		Response response = 
			given().
				formParam("username", "customer").
				formParam("password", "customer@123").
				formParam("woocommerce-login-nonce", "3b378396cb").
				formParam("_wp_http_referer", "/my-account-2/").
				formParam("login", "Log+in").
			when().
				redirects().follow(true).redirects().max(100).
				post("https://keyfood.ro/my-account-2/").
			then().
				extract().response();
		
		Map<String, String> cookies = response.cookies();

		System.out.println(cookies);
		System.out.println(cookies.size());
		
		Response response2 = given().
						cookies(cookies).
						get("https://keyfood.ro/my-account-2/orders").
						then().extract().response();
		
		System.out.println(response2.asString());
		assertTrue(response2.asString().contains("#2857"));
		
	}
	
	@Test
	public void bearerToken() {
		
		Response response = given().
				contentType(ContentType.JSON).
				body("{\"user\" : \"admin\",\"pass\" : \"admin@123\"}").
				post("https://dev-todo-b369f85c9f07.herokuapp.com/api/login").
				then().extract().response();

		System.out.println(response.asPrettyString());
		String token = response.jsonPath().getString("token");
		
		Response response2 = given().
				contentType(ContentType.JSON).
				//auth().oauth2(token).
				header("Authorization", "Bearer " + token).
				body("{\"title\" : \"admin\",\"body\" : \"admin@123\"}").
				post("https://dev-todo-b369f85c9f07.herokuapp.com/api/save").
				then().extract().response();

		System.out.println(response2.asPrettyString());
		
	}

}
