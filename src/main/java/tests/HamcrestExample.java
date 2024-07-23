package tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import io.restassured.response.Response;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.given;


public class HamcrestExample {
	 
	@Test
	public void hamcrestTest() {
		
		Response response = given().get("https://swapi.dev/api/planets/1/")
				.then().extract().response();
	
		System.out.println(response.asPrettyString());
		
		JsonPath json = response.jsonPath();
		
		String name = json.getString("name");
		System.out.println(name);
		
		//TestNg
		assertEquals(name, "Tatooine");
		//hamcrest assert
		//assertThat(name, "Tatooine");
		assertThat(name, is("Tatooine"));
		assertThat(name, equalTo("Tatooine"));
		assertThat(name, is(equalTo("Tatooine")));
		
		//TestNg
		assertNotEquals(name, "Mars");
		//hamcrest
		assertThat(name, is(not("Mars")));
		assertThat(name, is(not(equalTo("Mars"))));
		
		assertThat(name, is(not(instanceOf(Integer.class))));
		assertThat(name, is(instanceOf(String.class)));
		
		//startsWith endsWith
		assertThat(name, startsWith("Tat"));
		assertThat(name, endsWith("oine"));
		System.out.println(response.asString());
		assertThat(response.asString(), startsWithIgnoringCase("{\"NAME\":\"TaToOiNe\""));
		assertThat(response.asString(), endsWithIgnoringCase("PLANETS/1/\"}"));
		
		String name2 = " Tatooine ";
		assertThat(name, equalToCompressingWhiteSpace(name2));
		//assertThat(name, equalToIgnoringWhiteSpace(name2));
		
		//pattern
		assertThat(name, matchesPattern("[a-zA-Z]+"));
		String gravity = json.getString("gravity");
		assertThat(gravity, matchesPattern("[a-zA-Z 0-9!$&=]+"));
		String diameter = json.getString("diameter");
		assertThat(diameter, matchesPattern("[0-9]+"));
		
		//and
		assertThat(response.asString(), both(containsString("orbital_period")).and(containsString("10465")));
		
		//or
		assertThat(name, either(is("Tatooine")).or(is("Mars")).or(is("Terra")));
		assertThat(response.asString(), either(containsString("ABC")).or(containsString("population")));
		
		assertThat(response.asString(), containsStringIgnoringCase("ArID"));
		
		assertThat(response.asString(), stringContainsInOrder("rotation_period", "orbital_period"));
		
	}
}
