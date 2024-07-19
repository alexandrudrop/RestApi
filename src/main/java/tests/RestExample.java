package tests;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

public class RestExample {
	
	@Test
	public void restExampleTest() {
		
		Response response = 
				given().
					header("Content-Type", "application/json").
					body("{\"title\":\"dawdada\",\"body\":\"awdadada\"}").
				when().
					post("https://keytodorestapi.herokuapp.com/api/save").
				then().
					statusCode(200).
					body("info", equalTo("Todo saved! Nice job!")).
					extract().response();
		
		System.out.println(response);
		System.out.println(response.asString());
		System.out.println(response.asPrettyString());
		System.out.println(response.statusCode());
		System.out.println(response.getHeaders());
		
		assertEquals(response.statusCode(), 200);
		assertEquals(response.jsonPath().getString("info"), "Todo saved! Nice job!");
		assertThat(response.jsonPath().getString("info"), is(equalTo("Todo saved! Nice job!")));
		
		System.out.println(response.jsonPath().getString("id"));
	}
	

}
