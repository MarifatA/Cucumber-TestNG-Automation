package com.app.step_definitions;

import java.util.List;

import com.app.beans.ContactBean;
import com.app.pages.SuitCRMCreateContactsPage;
import com.app.pages.SuitCRMDashboard;
import com.app.pages.SuiteCRMContactInformationPage;
import com.app.utilities.BrowserUtils;

import cucumber.api.java.en.When;

public class CreateContactBeansStepDefs {
	SuitCRMDashboard dashboardPage = new SuitCRMDashboard();
	SuitCRMCreateContactsPage createContactPage = new SuitCRMCreateContactsPage();
	SuiteCRMContactInformationPage contactInfoPage = new SuiteCRMContactInformationPage();

	@When("^I save a new contact:$")
	public void i_save_a_new_contact(List<ContactBean> contacts) {
		System.out.println(contacts.size());
		ContactBean contactBean = contacts.get(0);
		// open the create contact page
		BrowserUtils.hover(dashboardPage.createMenu);
		dashboardPage.createContact();
		// enter data
		createContactPage.firstName.sendKeys(contactBean.getFirstName());
		createContactPage.lastName.sendKeys(contactBean.getLastName());
		createContactPage.officePhoneNumber.sendKeys(contactBean.getOfficePhone());
		createContactPage.cellPhoneNumber.sendKeys(contactBean.getCellPhone());
		createContactPage.department.sendKeys(contactBean.getDepartment());
		createContactPage.email.sendKeys(contactBean.getEmail());
		// save
		createContactPage.save();
	}

}
