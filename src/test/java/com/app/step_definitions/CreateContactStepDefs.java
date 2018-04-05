package com.app.step_definitions;

import static org.testng.Assert.assertEquals;

import com.app.pages.SuitCRMCreateContactsPage;
import com.app.pages.SuitCRMDashboard;
import com.app.pages.SuiteCRMContactInformationPage;
import com.app.utilities.BrowserUtils;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CreateContactStepDefs {
	SuitCRMDashboard dashboard = new SuitCRMDashboard();
	SuitCRMCreateContactsPage createContactPage = new SuitCRMCreateContactsPage();
	SuiteCRMContactInformationPage contactInfo = new SuiteCRMContactInformationPage();

	@Given("^I open the create contact page$")
	public void i_open_the_create_contact_page() {
		BrowserUtils.hover(dashboard.createMenu);
		dashboard.createContact();
	}

	@Given("^I enter first name \"([^\"]*)\" and last name \"([^\"]*)\"$")
	public void i_enter_first_name_and_last_name(String fistName, String lastName) {
		createContactPage.firstName.sendKeys(fistName);
		createContactPage.lastName.sendKeys(lastName);
	}

	@Given("^I enter the phone number \"([^\"]*)\"$")
	public void i_enter_the_phone_number(String officePhoneNumber) {
		createContactPage.officePhoneNumber.sendKeys(officePhoneNumber);
	}

	@Given("^I enter the department \"([^\"]*)\"$")
	public void i_enter_the_department(String department) {
		createContactPage.department.sendKeys(department);
	}

	@When("^I click save button$")
	public void i_click_save_button() {
		createContactPage.save();
		
	}

	@Then("^I should see contact information for \"([^\"]*)\"$")
	public void i_should_see_contact_information_for(String fullname) {
		assertEquals(contactInfo.firstName.getText(), fullname.split(" ")[0]);
		assertEquals(contactInfo.lastName.getText(), fullname.split(" ")[1]);
		

	}
}
