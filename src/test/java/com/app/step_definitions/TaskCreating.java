package com.app.step_definitions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import com.app.pages.SuitCRMDashboard;
import com.app.pages.SuitCRMTasks;
import com.app.pages.TaskOverView;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TaskCreating {
	SuitCRMDashboard dashPage = new SuitCRMDashboard();
	SuitCRMTasks taskPage = new SuitCRMTasks();
	TaskOverView taskView = new TaskOverView();
	Map<String, String> createTaskMap = new HashMap<>();
	Map<String, String> overViewTaskMap = new HashMap<>();

	@When("^I clicked on create taks$")
	public void i_clicked_on_create_taks() {
		dashPage.clickCreateTask();
	}

	@And("^Set subject as \"([^\"]*)\"$")
	public void set_subject_as(String subject) {
		taskPage.subjectBox.sendKeys(subject);
		createTaskMap.put("subject", subject);
	}

	@And("^Set status as \"([^\"]*)\"$")
	public void set_status_as(String status) {
		taskPage.setStatusByVisibleText(status);
		createTaskMap.put("status", status);
	}

	@And("^Start date is todays date$")
	public void start_date_is_todays_date() {
		String StartDate = taskPage.setStartDateToday();
		createTaskMap.put("startDate", StartDate);
	}

	@And("^Due date is (\\d+) days after todays date$")
	public void due_date_is_days_after_todays_date(int days) {
		String EndDate = taskPage.setEndDateFiveDaysAfterStartingDate(days);
		createTaskMap.put("endDate", EndDate);
	}

	@And("^Set priority \"([^\"]*)\" priority$")
	public void set_priority_priority(String priority) {
		taskPage.setPriorityByVisibleText(priority);
		createTaskMap.put("priority", priority);
	}

	@And("^Set description as \"([^\"]*)\"$")
	public void set_description_as(String description) {
		taskPage.descriptionBox.sendKeys(description);
		createTaskMap.put("description", description);
	}

	@And("^Save the task$")
	public void save_the_task() {
		taskPage.saveButton.click();
	}

	@Then("^Task details page should be displayed$")
	public void task_details_page_should_be_displayed() {
		assertTrue(taskView.subjectView.isDisplayed());
		overViewTaskMap.put("subject", taskView.subjectView.getText());

		assertTrue(taskView.startDateView.isDisplayed());
		overViewTaskMap.put("startDate", taskView.getOnlyDate(taskView.startDateView));

		assertTrue(taskView.endDateView.isDisplayed());
		overViewTaskMap.put("endDate", taskView.getOnlyDate(taskView.endDateView));

		assertTrue(taskView.priorityView.isDisplayed());
		overViewTaskMap.put("priority", taskView.priorityView.getText());

		assertTrue(taskView.statusView.isDisplayed());
		overViewTaskMap.put("status", taskView.statusView.getText());

		assertTrue(taskView.descriptionView.isDisplayed());
		overViewTaskMap.put("description", taskView.descriptionView.getText());

	}

	@And("^Data should match with created task data$")
	public void data_should_match_with_created_task_data() {
		System.out.println(createTaskMap);
		System.out.println(overViewTaskMap);
		assertEquals(createTaskMap, overViewTaskMap);
	}
}
