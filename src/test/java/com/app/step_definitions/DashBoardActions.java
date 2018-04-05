package com.app.step_definitions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.app.pages.SuitCRMCreateContactsPage;
import com.app.pages.SuitCRMDashboard;
import com.app.pages.SuitCRMLoginPage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class DashBoardActions {
	SuitCRMLoginPage loginPage = new SuitCRMLoginPage();
	SuitCRMDashboard dashboard = new SuitCRMDashboard();
	SuitCRMCreateContactsPage contactPage = new SuitCRMCreateContactsPage();

	@When("^I post \"([^\"]*)\"$")
	public void i_post(String searchTerm) {
		dashboard.post(searchTerm);
	}

	@Then("^Post \"([^\"]*)\" should be displayed$")
	public void post_should_be_displayed(String searchTerm) {
		assertTrue(dashboard.verifyPostDisplayed(searchTerm));
	}

	@Given("^duplicated contact \"([^\"]*)\" exists$")
	public void duplicated_contact_exists(String searchTerm) {
		dashboard.createContact();
		String pathOfVcard = "/Users/MarifatAzamatuulu/Downloads/john-doe.vcf";
		contactPage.importVCard(pathOfVcard);

	}

	@When("^remove duplicates for this contact$")
	public void remove_duplicates_for_this_contact() {
		contactPage.deleteDuplicates();
	}

	@Then("^there should be only (\\d+) \"([^\"]*)\" in the contacts page$")
	public void there_should_be_only_in_the_contacts_page(int amount, String contactName) {
		contactPage.viewContacts.click();
		assertEquals(contactPage.getNumberOfContacts(contactName), amount);
	}

}
