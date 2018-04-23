package com.app.step_definitions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.app.pages.SuitCRMCreateContactsPage;
import com.app.pages.SuitCRMDashboard;
import com.app.pages.SuitCRMLoginPage;
import com.app.utilities.ConfigurationReader;
import com.app.utilities.Driver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class UITestsStepDefs {
	SuitCRMLoginPage loginPage = new SuitCRMLoginPage();
	SuitCRMDashboard dashboard = new SuitCRMDashboard();
	SuitCRMCreateContactsPage contactsPage = new SuitCRMCreateContactsPage();

	@Given("^I logged in into suiteCRM$")
	public void i_logged_in_into_suiteCRM() {
		System.out.println("Logging to CRM");
		Driver.getDriver().get(ConfigurationReader.getProperty("url"));
		loginPage.login(ConfigurationReader.getProperty("username"), ConfigurationReader.getProperty("password"));
	}

	@Then("^CRM name should be SuiteCRM$")
	public void crm_name_should_be_SuiteCRM() {
		assertTrue(Driver.getDriver().getTitle().endsWith("SuiteCRM"));
	}

	@Then("^Moduls should be displayed$")
	public void moduls_should_be_displayed() {
		assertTrue(dashboard.verifyElementsDispayed(dashboard.moduls));
	}

	@Then("^I logout from app$")
	public void i_logout_from_app() {
		dashboard.logout();
	}

	@Then("^close browser$")
	public void close_browser() {
		Driver.quit();
	}

	@Given("^I open contact \"([^\"]*)\"$")
	public void i_open_contact(String contactName) {
		dashboard.createContact();
		dashboard.openContact(contactName);

	}

	@Then("^contact name should be \"([^\"]*)\"$")
	public void contact_name_should_be(String contactName) {
		assertEquals(contactsPage.getFullName(), contactName);
	}

	@Then("^contact email should be \"([^\"]*)\"$")
	public void contact_email_should_be(String email) {
		assertEquals(contactsPage.email.getText(), email);
	}
}
