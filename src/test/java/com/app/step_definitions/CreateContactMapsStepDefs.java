package com.app.step_definitions;

import java.util.Map;

import com.app.pages.SuitCRMCreateContactsPage;
import com.app.pages.SuitCRMDashboard;
import com.app.pages.SuiteCRMContactInformationPage;
import com.app.utilities.BrowserUtils;

import cucumber.api.java.en.When;

public class CreateContactMapsStepDefs {
	SuitCRMDashboard dashboardPage = new SuitCRMDashboard();
	SuitCRMCreateContactsPage createContactPage = new SuitCRMCreateContactsPage();
	SuiteCRMContactInformationPage contactInfoPage = new SuiteCRMContactInformationPage();

	@When("^I create a new contact:$")
	public void i_create_a_new_contact(Map<String, String> contact) {
		// open create contact dialog
		BrowserUtils.hover(dashboardPage.createMenu);
		dashboardPage.createContact();
		// enter information
		if (contact.get("first_name") != null) {
			createContactPage.firstName.sendKeys(contact.get("first_name"));
		}
		if (contact.get("last_name") != null) {
			createContactPage.lastName.sendKeys(contact.get("last_name"));
		}
		if (contact.get("office_phone") != null) {
			createContactPage.officePhoneNumber.sendKeys(contact.get("office_phone"));
		}
		if (contact.get("cell_phone") != null) {
			createContactPage.cellPhoneNumber.sendKeys(contact.get("cell_phone"));
		}

		// save
		createContactPage.save();

	}

}
