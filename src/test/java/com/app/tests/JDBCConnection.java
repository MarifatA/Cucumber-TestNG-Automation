package com.app.tests;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

public class JDBCConnection {

	String oracleDBUrl = "jdbc:oracle:thin:@ec2-184-72-113-162.compute-1.amazonaws.com:1521:xe";
	String oracleDBPassword = "hr";
	String oracleDBUsername = "hr";

	// Connection -> Statement -> ResultSet
	@Test(enabled = false)
	public void oracleJDBC() throws SQLException {
		Connection connection = DriverManager.getConnection(oracleDBUrl, oracleDBUsername, oracleDBPassword);
		// Statement statement = connection.createStatement();
		Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

		ResultSet resultSet = statement.executeQuery("select * from countries");

		// Find out how many records in the resultSet
		resultSet.last();
		int rowsCount = resultSet.getRow();
		System.out.println("Number of rows :" + rowsCount);

		resultSet.beforeFirst();

		while (resultSet.next()) {

			System.out.println(resultSet.getString(1) + "-" + resultSet.getString("country_name") + "-"
					+ resultSet.getInt("region_id"));
		}

		// resultSet.previous();
		// resultSet.first();
		// resultSet.beforeFirst();
		// resultSet.afterLast();

		resultSet.close();
		statement.close();
		connection.close();
	}

	@Test
	public void jdbcMetaData() throws Exception {

		Connection connection = DriverManager.getConnection(oracleDBUrl, oracleDBUsername, oracleDBPassword);
		// Statement statement = connection.createStatement();
		Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		String sql = "select employee_id,last_name,job_id,salary from employees";
		// String sql = "select * from employees";
		ResultSet resultSet = statement.executeQuery(sql);
		// DataBase MetaData
		DatabaseMetaData dbMetaData = connection.getMetaData();
		System.out.println("User: " + dbMetaData.getUserName());
		System.out.println("Database Type: " + dbMetaData.getDatabaseProductName()+dbMetaData.getSchemaTerm());

		// resultSet MetaData
		ResultSetMetaData rsMetadata = resultSet.getMetaData();
		System.out.println("columns count: " + rsMetadata.getColumnCount());
		System.out.println("first column is: " + rsMetadata.getColumnName(1));

		// print all columns name using a loop
		for (int i = 1; i <= rsMetadata.getColumnCount(); i++) {
			System.out.println(i + "->" + rsMetadata.getColumnName(i));
		}

		// Throw resultSet into a List of Maps
		// create a List of Maps
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		ResultSetMetaData rsMdata = resultSet.getMetaData();
		int colCount = rsMdata.getColumnCount();

		while (resultSet.next()) {
			Map<String, Object> rowMap = new HashMap<String, Object>();
			for (int col = 1; col <= colCount; col++) {
				rowMap.put(rsMdata.getColumnName(col), resultSet.getObject(col));
			}
			list.add(rowMap);
		}
		
		// print all employee_id from list of maps
		for (Map<String, Object> map : list) {
			System.out.println(map.get("EMPLOYEE_ID"));
		}
		
		
		
		resultSet.close();
		statement.close();
		connection.close();
	}
	
	

}
