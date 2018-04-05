package com.app.step_definitions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.Keys;

import com.app.pages.SuitCRMDashboard;
import com.app.pages.SuitCRMSearchResultsPage;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SearchModul {
	SuitCRMDashboard dashboard = new SuitCRMDashboard();
	SuitCRMSearchResultsPage resultPage = new SuitCRMSearchResultsPage();

	@When("^I search for \"([^\"]*)\"$")
	public void i_search_for(String contactName) {
		// BrowserUtils.waitForVisibility(dashPage.searchButton, 30);
		dashboard.searchButton.click();
		dashboard.searchBox.sendKeys(contactName + Keys.ENTER);
	}

	@Then("^link for user \"([^\"]*)\" should be displayed$")
	public void link_for_user_should_be_displayed(String contactName) {
		assertTrue(resultPage.verifyContact(contactName));
	}

	@Then("^verify number of result (\\d+) for \"([^\"]*)\"$")
	public void verify_number_of_result_for(int numberOfResult, String contactName) {
		int actual = resultPage.numberOfResult(contactName).size();
		assertEquals(actual, numberOfResult);
	}
}
