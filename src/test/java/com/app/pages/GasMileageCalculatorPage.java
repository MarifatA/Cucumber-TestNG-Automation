package com.app.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.app.utilities.Driver;

public class GasMileageCalculatorPage {

	public GasMileageCalculatorPage() {
		PageFactory.initElements(Driver.getDriver(), this);
	}

	@FindBy(xpath = "//input[@id='uscodreading']")
	public WebElement currentOdometer;

	@FindBy(xpath = "//input[@id='uspodreading']")
	public WebElement previousOdometer;

	@FindBy(xpath = "//input[@id='usgasputin']")
	public WebElement gas;

	@FindBy(xpath = "//table[@id='standard']//input[@alt='Calculate']")
	public WebElement calculateBtn;

	@FindBy(xpath = "//b[contains(.,'miles per gallon')]")
	public WebElement text;

}
