package com.app.pages;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.app.utilities.Driver;

public class SuitCRMTasks {
	public SuitCRMTasks() {
		PageFactory.initElements(Driver.getDriver(), this);
	}

	LocalDate date = LocalDate.now();
	DateTimeFormatter custom = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	@FindBy(xpath="//input[@id='name']")
	public WebElement subjectBox;

	@FindBy(id = "status")
	public WebElement status;

	@FindBy(id = "date_start_date")
	public WebElement startDate;

	@FindBy(id = "date_due_date")
	public WebElement endDate;

	@FindBy(id = "priority")
	public WebElement priority;

	@FindBy(id = "description")
	public WebElement descriptionBox;

	@FindBy(id = "SAVE")
	public WebElement saveButton;

	public void setPriorityByVisibleText(String priority) {
		Select select = new Select(this.priority);
		select.selectByVisibleText(priority);
	}

	public void setStatusByVisibleText(String status) {
		Select select = new Select(this.status);
		select.selectByVisibleText(status);
	}

	public String setStartDateToday() {
		startDate.sendKeys(custom.format(date).toString());
		return custom.format(date).toString();
	}

	public String setEndDateFiveDaysAfterStartingDate(int days) {
		date = date.plusDays(days);
		endDate.sendKeys(custom.format(date).toString());
		return custom.format(date).toString();
	}

}
