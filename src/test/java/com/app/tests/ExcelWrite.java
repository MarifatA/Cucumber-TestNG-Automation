package com.app.tests;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/*
 * Workbook < Worksheet < Row < Cell --> (Workbook > Sheet > Row > Cell)
 */
public class ExcelWrite {
	public static void main(String[] args) throws Exception {

		String testDataPath = "./src/test/resources/testdata/Emplyees.xlsx";

		// Create FileInputStream
		FileInputStream inStream = new FileInputStream(testDataPath);

		// creata Workbook
		Workbook workbook = WorkbookFactory.create(inStream);

		// Create Sheet
		Sheet workSheet = workbook.getSheet("Apache POI");// my sheet name should be same

		Cell job = workSheet.getRow(6).getCell(2);
		job.setCellValue("Automation Engineer In Testing");// to change value inside the cell we use setCellValue();

		// to Save we need to create FileOutputStream
		FileOutputStream outStream = new FileOutputStream(testDataPath);
		workbook.write(outStream);// to save changes we need use write() method

		// at the end we need to close all
		outStream.close();
		workbook.close();
		inStream.close();

	}
}
