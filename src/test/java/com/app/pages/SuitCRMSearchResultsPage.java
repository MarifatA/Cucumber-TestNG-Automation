package com.app.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.app.utilities.Driver;

public class SuitCRMSearchResultsPage {

	public SuitCRMSearchResultsPage() {
		PageFactory.initElements(Driver.getDriver(), this);
	}

	public boolean verifyContact(String name) {
		try {
			WebElement el = Driver.getDriver().findElement(By.xpath("//a[.='" + name + "']"));
			if (!el.getAttribute("href").equals(null) && el.getText().equals(name)) {
				return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	public List<WebElement> numberOfResult(String contactName) {
		return Driver.getDriver().findElements(By.xpath("//a[.='" + contactName + "']"));
	}
}
