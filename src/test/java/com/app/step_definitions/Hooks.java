package com.app.step_definitions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.app.utilities.Driver;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {

	@Before
	public void setUp(Scenario scenario) {
		Driver.getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Driver.getDriver().manage().window().fullscreen();

	}

	@After
	public void teardown(Scenario scenario) {
		if (scenario.isFailed()) {
			// taking a screenshot
			final byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
			// adding the screenshot to the report
			scenario.embed(screenshot, "image/png");
		}
		Driver.quit();
	}
}
