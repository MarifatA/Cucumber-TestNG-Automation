package com.app.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.app.utilities.Driver;

public class TaskOverView {
	private WebDriver driver;

	public TaskOverView() {
		driver = Driver.getDriver();
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "name")
	public WebElement subjectView;

	@FindBy(id = "date_start")
	public WebElement startDateView;

	@FindBy(id = "date_due")
	public WebElement endDateView;

	@FindBy(css = "div[field='priority']")
	public WebElement priorityView;

	@FindBy(css = "div[field='status']")
	public WebElement statusView;

	@FindBy(id = "description")
	public WebElement descriptionView;

	public String getOnlyDate(WebElement el) {
		String[] arr = el.getText().split(" ");
		return arr[0];
	}
}
