package com.app.pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.app.utilities.Driver;

public class SuitCRMCreateContactsPage {

	public SuitCRMCreateContactsPage() {
		PageFactory.initElements(Driver.getDriver(), this);
	}

	@FindBy(id = "first_name")
	public WebElement firstName;

	@FindBy(id = "last_name")
	public WebElement lastName;

	@FindBy(id = "phone_work")
	public WebElement officePhoneNumber;

	@FindBy(id = "phone_mobile")
	public WebElement cellPhoneNumber;

	@FindBy(id = "department")
	public WebElement department;

	@FindBy(id = "Contacts0emailAddress0")
	public WebElement email;

	@FindBy(id = "SAVE")
	public WebElement saveBtn;

	@FindBy(xpath = "//input[@title='Save']")
	public WebElement saveConfirmationBtn;

	@FindBy(linkText = "Create Contact From vCard")
	public WebElement createContactVCardLink;

	@FindBy(id = "vcard_file")
	public WebElement vCardInput;

	@FindBy(id = "import_vcard_button")
	public WebElement importVcard;

	@FindBy(xpath = "//a[contains(text(),'ACTIONS')]")
	public WebElement actions;

	@FindBy(id = "merge_duplicate_button")
	public WebElement findDuplicatesLink;

	@FindBy(id = "next_step_button")
	public WebElement nextStepButton;

	@FindBy(css = "#massall_top")
	public WebElement selectAll;

	@FindBy(id = "perform_merge_button")
	public WebElement performMerge;

	@FindBy(id = "save_merged_record_button")
	public WebElement saveMerge;

	@FindBy(xpath = "//div[.='View Contacts']")
	public WebElement viewContacts;

	public int getNumberOfContacts(String contactName) {
		List<WebElement> contacts = Driver.getDriver().findElements(By.xpath("//a[contains(text(),'John Doe')]"));
		return contacts.size();
	}

	public void deleteDuplicates() {
		actions.click();
		findDuplicatesLink.click();
		nextStepButton.click();
		JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
		js.executeScript("arguments[0].click();", selectAll);
		performMerge.click();
		saveMerge.click();
		Alert alert = Driver.getDriver().switchTo().alert();
		alert.accept();
	}

	public void importVCard(String path) {
		createContactVCardLink.click();
		vCardInput.sendKeys(path);
		importVcard.click();
	}

	public String getFullName() {
		return firstName.getText() + " " + lastName.getText();
	}

	public void save() {
		saveBtn.click();
		try {
			Driver.getDriver().manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			saveConfirmationBtn.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Driver.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

	}

}
