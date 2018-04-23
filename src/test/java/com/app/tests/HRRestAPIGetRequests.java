package com.app.tests;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.RestAssured.with;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.net.URI;
import java.net.URISyntaxException;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class HRRestAPIGetRequests {
	@Test
	public void simpleGet() {
		/*
		 * When I send a GET request to REST URL
		 * http://34.223.219.142:1212/ords/hr/employees/ 
		 * Then response status should be 200
		 */
		when().get("http://34.223.219.142:1212/ords/hr/employees").then().statusCode(200);
	}

	/*
	 * When I send a GET request to REST URL
	 * http://34.223.219.142:1212/ords/hr/countries/
	 * Then response status should be 200 
	 * Then I should see Json Response
	 * 
	 */
	@Test
	public void printResponse() {
		when().get("http://34.223.219.142:1212/ords/hr/countries/").body().prettyPrint();
	}

	/*
	 * When I send a GET request to REST Url
	 * http://34.223.219.142:1212/ords/hr/countries/US 
	 * And Accept type is "application/json" 
	 * Then response status code should be 200
	 */
	@Test
	public void getWithHeaders() {
		with().accept(ContentType.JSON).when().get("http://34.223.219.142:1212/ords/hr/countries/US").then()
				.statusCode(200);
	}

	/*
	 * 	When I send a GET request to REST URL:
	 * 	http://34.223.219.142:1212/ords/hr/employees/1234
	 *  Then response status code is 404 
	 *  And Response body error message is "Not Found"
	 */
	@Test
	public void negativeGet() {
		// when().get("http://34.223.219.142:1212/ords/hr/employees/1234").then().statusCode(404);
		Response response = when().get("http://34.223.219.142:1212/ords/hr/employees/1234");
		assertEquals(response.statusCode(), 404);
		assertTrue(response.asString().contains("Not Found"));
		response.prettyPrint();
	}

	/*
	 * When I send a GET request to REST URL:
	 * http://34.223.219.142:1212/ords/hr/employees/100
	 * And Accept Type is Json 
	 * Then response status code is 200 
	 * And Response content should be Json
	 */
	@Test
	public void verifyContentTypeWithAssertThat() {
		given().accept(ContentType.JSON).when().get("http://34.223.219.142:1212/ords/hr/employees/100").then()
				.assertThat().statusCode(200).and().contentType(ContentType.JSON);
	}

	/*
	 * Given Accept type is Json 
	 * When I send a GET request to REST URL:
	 * http://34.223.219.142:1212/ords/hr/employees/100 
	 * Then status code is 200 
	 * And Response content should be Json 
	 * And firstname should be "Steven" 
	 * And employee_id 100
	 */
	@Test
	public void verifyFirstName() throws URISyntaxException {
		URI uri = new URI("http://34.223.219.142:1212/ords/hr/employees/100");
		given().accept(ContentType.JSON).when().get(uri).then().assertThat().statusCode(200).and()
				.contentType(ContentType.JSON).and().assertThat().body("first_name", equalTo("Steven")).and()
				.assertThat().body("employee_id", equalTo(100));
	}

}
