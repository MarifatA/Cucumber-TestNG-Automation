package com.app.tests;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.app.utilities.ConfigurationReader;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class JsonValidationWarmUpTest {

	@Test
	public void testRegions1() {
		Map<String, Integer> requestParamMap = new HashMap<>();
		requestParamMap.put("limit", 10);

		Response response = given().accept(ContentType.JSON).and().params(requestParamMap).and().when()
				.get(ConfigurationReader.getProperty("hrapp.baseresturl") + "/regions");

		assertEquals(response.statusCode(), 200, "Doesn't match status code");

		JsonPath json = response.jsonPath();
		// Version-1
		assertEquals(json.getInt("items[0].region_id"), 1);
		assertEquals(json.getString("items[0].region_name"), "Europe");
		assertEquals(json.getInt("items[1].region_id"), 2);
		assertEquals(json.getString("items[1].region_name"), "Americas");
		assertEquals(json.getInt("items[2].region_id"), 3);
		assertEquals(json.getString("items[2].region_name"), "Asia");
		assertEquals(json.getInt("items[3].region_id"), 4);
		assertEquals(json.getString("items[3].region_name"), "Middle East and Africa");
	}

	@Test
	public void testRegions2() {
		Map<String, Integer> requestParamMap = new HashMap<>();
		requestParamMap.put("limit", 10);

		Response response = given().accept(ContentType.JSON).and().params(requestParamMap).and().when()
				.get(ConfigurationReader.getProperty("hrapp.baseresturl") + "/regions");
		assertEquals(response.statusCode(), 200, "Doesn't match status code");
		// Store into json path > List<Map>
		JsonPath json = response.jsonPath();

		// De-serialization JSon to Java Object
		List<Map> regions = json.getList("items", Map.class);

		Map<Integer, String> expectedRegions = new HashMap<>();
		expectedRegions.put(1, "Europe");
		expectedRegions.put(2, "Americas");
		expectedRegions.put(3, "Asia");
		expectedRegions.put(4, "Middle East and Africa");

		for (Integer regionID : expectedRegions.keySet()) {
			System.out.println("Looking for region: " + regionID);
			for (Map map : regions) {
				if (map.get("region_id") == regionID) {
					assertEquals(map.get("region_name"), expectedRegions.get(regionID));
				}
			}

		}

	}

	@Test
	public void testWarmUpVer2() {
		Response response = given().accept(ContentType.JSON).and().params("limit", 10).when()
				.get(ConfigurationReader.getProperty("hrapp.baseresturl") + "/regions");
		JsonPath json = response.jsonPath();
		Map<Integer, String> expectedValues = new HashMap<>();
		expectedValues.put(1, "Europe");
		expectedValues.put(2, "Americas");
		expectedValues.put(3, "Asia");
		expectedValues.put(4, "Middle East and Africa");
		assertEquals(response.statusCode(), 200);
		for (int i = 0; i < 4; i++) {
			assertEquals(json.getInt("items[" + i + "].region_id"), i + 1);
			assertEquals(json.getString("items[" + i + "].region_name"), expectedValues.get(i + 1));
		}
	}
}
