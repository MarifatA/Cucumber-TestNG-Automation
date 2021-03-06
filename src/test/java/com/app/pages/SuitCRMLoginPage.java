package com.app.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.app.utilities.Driver;

public class SuitCRMLoginPage {
   public SuitCRMLoginPage() {
	   PageFactory.initElements(Driver.getDriver(), this);
   }
   
   @FindBy(id = "user_name")
   public WebElement username;
   
   @FindBy(id = "username_password")
   public WebElement password;
   
   @FindBy(id = "bigbutton")
   public WebElement login;
   
   public void login(String username, String password) {
	   this.username.sendKeys(username);
	   this.password.sendKeys(password);
	   this.login.click();
   }
   
}
