package com.app.step_definitions;

import static org.testng.Assert.assertEquals;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.app.pages.HRAppDeptEmpPage;
import com.app.utilities.BrowserUtils;
import com.app.utilities.ConfigurationReader;
import com.app.utilities.DBUtility;
import com.app.utilities.DbType;
import com.app.utilities.Driver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class HRAppStepDefs {

	private HRAppDeptEmpPage deptEmpPage = new HRAppDeptEmpPage();

	private Map<String, Object> UIData;
	private Map<String, Object> DBData;

	@Given("^I am on DeptEmpPage$")
	public void i_am_on_DeptEmpPage() {
		Driver.getDriver().get(ConfigurationReader.getProperty("hrapp.url"));
	}

	@When("^I search for deaprtment id (\\d+)$")
	public void i_search_for_deaprtment_id(int deptID) {
		UIData = new HashMap<>();
		deptEmpPage.searchForDepartmentID(deptID);
		// add UI data to HashMap
		UIData.put("DEPARTMENT_NAME", deptEmpPage.department_name.getText());
		UIData.put("MANAGER_ID", deptEmpPage.manager_id.getText());
		UIData.put("LOCATION_ID", deptEmpPage.location_id.getText());
	}

	@When("^I query database with sql \"([^\"]*)\"$")
	public void i_query_database_with_sql(String sql) throws SQLException {

		DBUtility.establishConnection(DbType.ORACLE);
		List<Map<String, Object>> DBDataList = DBUtility.runSQLQuery(sql);
		DBData = DBDataList.get(0);

		DBUtility.closeConnections();

	}

	@When("^I search for email \"([^\"]*)\" to see firstname and lastname$")
	public void i_search_for_email_to_see_firstname_and_lastname(String email) {
		deptEmpPage.emailField.clear();
		deptEmpPage.emailField.sendKeys(email);
		deptEmpPage.findDeatilsBtn.click();
		BrowserUtils.waitForVisibility(deptEmpPage.firstName, 5);

		UIData = new HashMap<>();
		UIData.put("FIRST_NAME", deptEmpPage.firstName.getText());
		UIData.put("LAST_NAME", deptEmpPage.lastName.getText());

	}

	@When("^I search for deaprtment id (\\d+) and get number of employees$")
	public void i_search_for_deaprtment_id_and_get_number_of_employees(int deptID) {

		deptEmpPage.searchForDepartmentID(deptID);
		deptEmpPage.detach.click();
		BrowserUtils.waitFor(2);
		BrowserUtils.scrollDown();
		UIData = new HashMap<>();
		UIData.put("EMPLOYEES_COUNT", String.valueOf(deptEmpPage.employeesCount.size()));

	}

	@Then("^UI data and Database data must match$")
	public void ui_data_and_Database_data_must_match() {
		for (String key : DBData.keySet()) {
			assertEquals(UIData.get(key), String.valueOf(DBData.get(key)));
		}

		// assertEquals(UIDepartmentData.get("DEPARTMENT_NAME"),
		// DBDepartmentData.get("DEPARTMENT_NAME"));
		// assertEquals(UIDepartmentData.get("MANAGER_ID"),
		// String.valueOf(DBDepartmentData.get("MANAGER_ID")));
		// assertEquals(UIDepartmentData.get("LOCATION_ID"),
		// String.valueOf(DBDepartmentData.get("LOCATION_ID")));
	}

}
