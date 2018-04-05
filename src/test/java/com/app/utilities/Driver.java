package com.app.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.PhantomJsDriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Driver {

	private static WebDriver driver;

	public static WebDriver getDriver() {
		if (driver == null) {
			switch (ConfigurationReader.getProperty("browser")) {

			case "chrome":
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				break;

			case "firefox":
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				break;

			case "safari":
				driver = new SafariDriver();
				break;

			case "opera":
				WebDriverManager.operadriver().setup();
				driver = new OperaDriver();
				break;

			case "ie":
				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();
				break;
			case "phantomjs":
				WebDriverManager.phantomjs().setup();
				driver = (WebDriver) new PhantomJsDriverManager();
				break;
			}
		}
		return driver;

	}

	public static void quit() {
		if (driver != null) {
			driver.quit();
			driver = null;

		}
	}

}
