package com.app.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.app.utilities.BrowserUtils;
import com.app.utilities.Driver;

public class SuitCRMDashboard {

	public SuitCRMDashboard() {
		PageFactory.initElements(Driver.getDriver(), this);
	}

	@FindBy(css = ".topnav>span>a")
	public List<WebElement> moduls;

	@FindBy(id = "text")
	public WebElement textBox;

	@FindBy(xpath = "(//input[@value='Post'])[1]")
	public WebElement postButton;

	@FindBy(id = "with-label")
	public WebElement profileMenu;

	@FindBy(xpath = "(//a[@id='logout_link'])[3]")
	public WebElement logoutlink;

	@FindBy(xpath = "(//button[@id ='searchbutton'])[3]")
	public WebElement searchButton;

	@FindBy(xpath = "(//input[@id='query_string'])[5]")
	public WebElement searchBox;

	@FindBy(id = "grouptab_1")
	public WebElement marketingMenu;

	@FindBy(linkText = "Create Contact")
	public WebElement createContactLink;

	@FindBy(linkText = "CREATE")
	public WebElement createMenu;

	@FindBy(linkText = "Create Task")
	public WebElement createTaskLink;

	@FindBy(xpath = "//*[@id=\"grouptab_4\"]")
	public WebElement collaboration;

	@FindBy(xpath = "//*[@id=\"grouptab_2\"]")
	public WebElement suppoert;

	@FindBy(xpath = "//*[@id=\"grouptab_0\"]")
	public WebElement sales;

	@FindBy(xpath = "//*[@id=\"grouptab_5\"]")
	public WebElement all;

	@FindBy(xpath = "//*[@id=\"grouptab_3\"]")
	public WebElement activities;

	@FindBy(xpath = "//*[@id=\"grouptab_1\"]")
	public WebElement marketing;

	public void clickCreateTask() {
		createMenu.click();
		createTaskLink.click();
	}

	public void openContact(String contactName) {
		Driver.getDriver().findElement(By.xpath("(//a[contains(text(),'" + contactName + "')])[1]")).click();
	}

	public void createContact() {
		Actions action = new Actions(Driver.getDriver());
		action.moveToElement(createMenu).perform();
		BrowserUtils.waitForVisibility(createContactLink, 3);
		createContactLink.click();

	}

	public boolean verifyPostDisplayed(String value) {
		return Driver.getDriver().findElement(By.xpath("//div[contains(text(),'" + value + "')]")).isDisplayed();
	}

	public void post(String value) {
		textBox.sendKeys(value);
		postButton.click();
	}

	public boolean verifyElementsDispayed(List<WebElement> listOfElements) {
		for (WebElement e : listOfElements) {
			if (!e.isDisplayed()) {
				System.out.println(e.getText() + " does dispayed");
				return false;
			}
		}
		return true;
	}

	public void logout() {
		Actions action = new Actions(Driver.getDriver());
		action.moveToElement(profileMenu).perform();
		BrowserUtils.waitForVisibility(logoutlink, 5);
		logoutlink.click();
	}

	public List<WebElement> topMenuOptions(String name) {
		// sales marketing support activities
		String xpath = "//a[.='" + name + "']/..//li/a";
		return Driver.getDriver().findElements(By.xpath(xpath));

	}

}
