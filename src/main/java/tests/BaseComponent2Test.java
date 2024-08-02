package tests;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import utils.BaseComponent2;

public class BaseComponent2Test extends BaseComponent2 {
	
	@Test
	public void postTodoWithAuth() {
		
		Response response = doPostRequest("{\r\n"
				+ "    \"title\":\"Microsoft\",\r\n"
				+ "    \"body\": \"testCeva\"\r\n"
				+ "}",
				"save");
		
		System.out.println(response.asPrettyString());
		
	}

}
