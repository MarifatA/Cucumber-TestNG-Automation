package com.app.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.app.utilities.DBUtility;
import com.app.utilities.DbType;

public class EmployeesDBTest {

	@BeforeClass
	public void setUp() throws SQLException {
		DBUtility.establishConnection(DbType.ORACLE);

	}

	@AfterClass
	public void tearDown() {
		DBUtility.closeConnections();

	}

	@Test
	public void countTest() throws SQLException {
		// Connect to oracle database
		// run following sql query : select * from employees where job_id='IT_PROG'
		// more than 0 records should be return
		int rowsCount = DBUtility.getRowsCount("select * from employees where job_id='IT_PROG'");
		assertTrue(rowsCount > 0);

	}

	@Test
	public void nameTestByID() throws SQLException {
		// Connect to oracle database
		// Employees firstName and lastName with employee_id=105 should be David Austin
		List<Map<String, Object>> empData = DBUtility
				.runSQLQuery("SELECT first_name,last_name FROM employees WHERE employee_id=105");
		assertEquals(empData.get(0).get("FIRST_NAME"), "David");
		assertEquals(empData.get(0).get("LAST_NAME"), "Austin");

	}
}
