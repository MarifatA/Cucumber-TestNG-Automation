package com.app.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.app.utilities.ConfigurationReader;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class APIDay3_JsonPath {

	/*
	 * Given Accept type is Json When I send a GET request to REST URL:
	 * http://34.223.219.142:1212/ords/hr/regions Then status code is 200 And
	 * Response content should be Json And 4 regions should be returned And Americas
	 * is one of the regions names
	 */
	// Validation of multiple values in response Json
	@Test
	public void testItemsCountResponseBody() {

		given().accept(ContentType.JSON).when().get(ConfigurationReader.getProperty("hrapp.baseresturl") + "/regions")
				.then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().assertThat()
				.body("items.region_id", hasSize(4)).and().assertThat().body("items.region_name", hasItem("Americas"))
				.body("items.region_name", hasItems("Americas", "Asia"));

	}

	/*
	 * Given Accept type is Json And param are limit 100 When I send get request to
	 * http://34.223.219.142:1212/ords/hr/employees Then status code is 200 And
	 * Response content should be Json And 100 employees data should be in Json
	 * response body
	 */

	@Test
	public void testWithQueryParameterAndList() {
		given().accept(ContentType.JSON).param("limit", 100).when()
				.get(ConfigurationReader.getProperty("hrapp.baseresturl") + "/employees").then().assertThat()
				.statusCode(200).and().contentType(ContentType.JSON).and().assertThat()
				.body("items.employee_id", hasSize(100));

	}

	/*
	 * Given Accept type is Json And param are limit 100 And path param is 110 When
	 * I send get request to http://34.223.219.142:1212/ords/hr/employees Then
	 * status code is 200 And Response content should be Json And 100 employees data
	 * should be in Json response body And following data should be returned:
	 * "employee_id": 110, "first_name": "John", "last_name": "Chen", "email":
	 * "JCHEN",
	 * 
	 */
	@Test
	public void testWithPathParameter() {

		given().accept(ContentType.JSON).and().params("limit", 100).and().pathParams("employee_id", 110).when()
				.get(ConfigurationReader.getProperty("hrapp.baseresturl") + "/employees/{employee_id}").then()
				.statusCode(200).and().contentType(ContentType.JSON).and().assertThat()
				.body("employee_id", equalTo(110), "first_name", equalTo("John"), "last_name", equalTo("Chen"));
	}

	/*
	 * Given Accept type is Json And param are limit 100 When I send get request to
	 * http://34.223.219.142:1212/ords/hr/employees Then status code is 200 And
	 * Response content should be Json And 100 employees data should be in Json
	 * response body And following data should be returned: All employee_id should
	 * be returned
	 * 
	 */

	@Test
	public void testWithJsonPath() {
		Map<String, Integer> requestParamMap = new HashMap<>();
		requestParamMap.put("limit", 100);

		Response response = given().accept(ContentType.JSON)// header
				.and().params(requestParamMap)// query param/request param
				.and().pathParam("employee_id", 177)// path param
				.when().get(ConfigurationReader.getProperty("hrapp.baseresturl") + "/employees/{employee_id}");
		JsonPath json = response.jsonPath();// get json body and assign to jsonpath Object

		System.out.println(json.getInt("employee_id"));
		System.out.println(json.getString("last_name"));
		System.out.println(json.getString("job_id"));
		System.out.println(json.getInt("salary"));
		System.out.println(json.getString("links[0].href"));// get specific element from array

		// asign all href into a list of strings
		List<String> hrefs = json.getList("links.href");
		System.out.println(hrefs);

	}

	/*
	 * Given Accept type is Json And param are limit 100 When I send get request to
	 * http://34.223.219.142:1212/ords/hr/employees Then status code is 200 And
	 * Response content should be Json And 100 employees data should be in Json
	 * response body And all employee data should be returned
	 */
	@Test
	public void testJsonPathWithLists() {
		Map<String, Integer> requestParamMap = new HashMap<>();
		requestParamMap.put("limit", 100);

		Response response = given().accept(ContentType.JSON)// header
				.and().params(requestParamMap)// query param/request param
				.when().get(ConfigurationReader.getProperty("hrapp.baseresturl") + "/employees");

		assertEquals(response.statusCode(), 200);// check status code
		JsonPath json = response.jsonPath();
		// JsonPath json=new JsonPath(FilePath(new File())
		// JsonPath json = new JsonPath(response.asString());

		// get all employee ids into araryLIst
		List<Integer> employeeIds = json.getList("items.employee_id");
		System.out.println(employeeIds);
		assertEquals(employeeIds.size(), 100, "Employee ids doesn't match");

		// get all employee emails
		List<String> empEmails = json.getList("items.email");
		System.out.println(empEmails);

		// get all employee ids that are greater than 150
		List<String> empIdList = json.getList("items.findAll{it.employee_id > 150}.employee_id");
		System.out.println(empIdList);

		// get all emplyee lastnames who's salary more than 7000
		List<Integer> empLastnames = json.getList("items.findAll{it.salary>7000}.last_name");
		System.out.println(empLastnames);
	}

}
