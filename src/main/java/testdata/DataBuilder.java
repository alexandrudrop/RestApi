package testdata;

import org.json.simple.JSONObject;

import com.github.javafaker.Faker;

import utils.PropertiesFileProcessor;

public class DataBuilder {

	
	@SuppressWarnings("unchecked")
	public static JSONObject buildUser() {
		Faker fake = new Faker();
		JSONObject bodyBuilder = new JSONObject();
		bodyBuilder.put("name", fake.name().firstName());
		bodyBuilder.put("email", fake.internet().emailAddress());
		bodyBuilder.put("age", fake.number().numberBetween(6, 100));
		bodyBuilder.put("gender", "m");
		
		return bodyBuilder;
	}
	
	@SuppressWarnings("unchecked")
	public static JSONObject buildToken() {
		
		String user = PropertiesFileProcessor.readPropertiesFile("user", "credentials.properties");
		String pass = PropertiesFileProcessor.readPropertiesFile("pass", "credentials.properties");
		
		JSONObject tokenBuilder = new JSONObject();
		tokenBuilder.put("user", user);
		tokenBuilder.put("pass", pass);
		
		return tokenBuilder;
	}
	
	
}