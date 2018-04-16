package com.app.step_definitions;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.WebElement;

import com.app.pages.SuitCRMDashboard;
import com.app.utilities.BrowserUtils;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MenuOptionsStepDefs {
	SuitCRMDashboard dashboard = new SuitCRMDashboard();

	@When("^I hover over the (Collaboration|Sales|Marketing|Support|Activities|All) menu$")
	public void i_hover_over_the_Collaboration_menu(String menu) {
		switch (menu) {
		case "Collaboration":
			BrowserUtils.hover(dashboard.collaboration);
			break;
		case "Sales":
			BrowserUtils.hover(dashboard.sales);
			break;
		case "Marketing":
			BrowserUtils.hover(dashboard.marketing);
			break;
		case "Support":
			BrowserUtils.hover(dashboard.support);
			break;
		case "Activities":
			BrowserUtils.hover(dashboard.activities);
			break;
		case "All":
			BrowserUtils.hover(dashboard.all);
			break;
		}
	}

	@Then("^following menu options should be visible for (Collaboration|Sales|Marketing|Support|All):$")
	public void following_menu_options_should_be_visible_for_Collaboration(String menu, List<String> options) {
		// capture list of webelements
		List<WebElement> topMenuOptions = dashboard.topMenuOptions(menu);
		// get their text in a list
		List<String> topMenuOptionsString = BrowserUtils.getElementsText(topMenuOptions);
		// compare the list with options
		System.out.println(topMenuOptionsString.size() + "Adas");
		System.out.println(options.size() + "adfa");
		assertEquals(topMenuOptionsString.size(), options.size(), "Number of expected menu options did not match");
		for (int i = 0; i < options.size(); i++) {
			assertEquals(topMenuOptionsString.get(i), options.get(i));
		}

	}

}
