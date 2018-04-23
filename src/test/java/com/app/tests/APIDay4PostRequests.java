package com.app.tests;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.testng.annotations.Test;

import com.app.beans.Country;
import com.app.beans.CountryResponse;
import com.app.beans.Region;
import com.app.beans.RegionResponse;
import com.app.utilities.ConfigurationReader;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class APIDay4PostRequests {

	String url = ConfigurationReader.getProperty("hrapp.baseresturl") + "/regions/";
	// String requestJson = "{\"region_id\" : 77777, \"region_name\" : \"TALAS
	// region\"}";

	@Test
	public void postNewRegion() {
		Map requestMap = new HashMap<>();
		requestMap.put("region_id", 7777);
		requestMap.put("region_name", "Talas ");

		Response respone = given().accept(ContentType.JSON).and().contentType(ContentType.JSON).and().body(requestMap)
				.when().post(url);
		System.out.println(respone.statusLine());
		respone.prettyPrint();
		assertEquals(respone.statusCode(), 201);

		Map responseMap = respone.body().as(Map.class);
		// assertEquals(requestMap, responseMap);
		assertEquals(responseMap.get("region_id"), requestMap.get("region_id"));
		assertEquals(responseMap.get("region_name"), requestMap.get("region_name"));

	}

	@Test

	public void postUsingPOJO() {
		String url = ConfigurationReader.getProperty("hrapp.baseresturl") + "/regions/";
		Region region = new Region();
		region.setRegion_id(new Random().nextInt());
		region.setRegion_name("Talas region");

		Response response = given().log().all().accept(ContentType.JSON).and().contentType(ContentType.JSON).and()
				.body(region).when().post(url);

		assertEquals(response.statusCode(), 201);
		RegionResponse responseRegion = response.body().as(RegionResponse.class);
		assertEquals(responseRegion.getRegion_id(), region.getRegion_id());
		assertEquals(responseRegion.getRegion_name(), region.getRegion_name());

	}

	@Test
	public void postCountryUsingPOJO() {
		String url = ConfigurationReader.getProperty("hrapp.baseresturl") + "/countries/";
		Country reqCountry = new Country();
		reqCountry.setCountry_id("WI");
		reqCountry.setCountry_name("WISConsin");
		reqCountry.setRegion_id(4);

		Response response = given().log().all().accept(ContentType.JSON).and().contentType(ContentType.JSON).and()
				.body(reqCountry).when().post(url);

		assertEquals(response.statusCode(), 201);
		CountryResponse respCountry = response.body().as(CountryResponse.class);
		assertEquals(respCountry.getCountry_id(), reqCountry.getCountry_id());
		assertEquals(respCountry.getCountry_name(), reqCountry.getCountry_name());
		assertEquals(respCountry.getRegion_id(), reqCountry.getRegion_id());

	}

}
