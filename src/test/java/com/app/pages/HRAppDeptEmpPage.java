package com.app.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.app.utilities.BrowserUtils;
import com.app.utilities.Driver;

public class HRAppDeptEmpPage {
	public HRAppDeptEmpPage() {
		PageFactory.initElements(Driver.getDriver(), this);
	}

	@FindBy(id = "pt1:ot1")
	public WebElement department_id;

	@FindBy(id = "pt1:ot2")
	public WebElement department_name;
	@FindBy(id = "pt1:ot3")

	public WebElement manager_id;

	@FindBy(id = "pt1:ot4")
	public WebElement location_id;

	@FindBy(id = "pt1:cb3")
	public WebElement nextBtn;

	@FindBy(id = "pt1:r1:0:it1::content")
	public WebElement emailField;

	@FindBy(id = "pt1:r1:0:cb1")
	public WebElement findDeatilsBtn;

	@FindBy(id = "pt1:r1:0:ot1")
	public WebElement firstName;

	@FindBy(id = "pt1:r1:0:ot2")
	public WebElement lastName;

	@FindBy(xpath = "//div[@id='pt1:pc1:t1::db']//tr")
	public List<WebElement> employeesCount;

	@FindBy(id = "pt1:pc1:_dchTbr")
	public WebElement detach;

	public void searchForDepartmentID(int deptID) {
		int currentDept_id = Integer.parseInt(department_id.getText());
		while (currentDept_id != deptID) {
			nextBtn.click();
			BrowserUtils.waitFor(2);
			currentDept_id = Integer.parseInt(department_id.getText());
		}
	}

}
