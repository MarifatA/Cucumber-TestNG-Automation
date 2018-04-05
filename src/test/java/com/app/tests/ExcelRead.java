package com.app.tests;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelRead {
	public static void main(String[] args) throws Exception {
		String filePath = "/Users/MarifatAzamatuulu/Desktop/Emplyees.xlsx";

		// Open file and convert to a stream of data
		FileInputStream inStream = new FileInputStream(filePath);

		// take the stream of data and use it as WorkBook
		Workbook workbook = WorkbookFactory.create(inStream);

		// Get the first worksheet from the workbook
		Sheet worksheet = workbook.getSheetAt(0);

		// go to first row
		Row row = worksheet.getRow(0);
		Cell cell = row.getCell(0);
		System.out.println(cell.toString());

		// Find out how many rows in Excel sheet
		int rowsCount = worksheet.getPhysicalNumberOfRows();
		System.out.println("Number of rows: " + rowsCount);

		// print all firstnames
		for (int rowNum = 1; rowNum < rowsCount; rowNum++) {
			row = worksheet.getRow(rowNum);
			cell = row.getCell(0);
			System.out.println(rowNum + " - " + cell);

			// System.out.println(rowNum + " - " + worksheet.getRow(rowNum).getCell(0));}
			// Print JOB_ID of Nancy
			// Cell NancyJob = worksheet.getRow(5).getCell(2);
			// System.out.println(NancyJob);
		}
		for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
			Row myRow = worksheet.getRow(i);
			if (myRow.getCell(0).toString().equals("Nancy")) {

				// print job_ID from same row
				System.out.println("Nancy works as: " + myRow.getCell(2));
				break;

			}
		}

		workbook.close();
		inStream.close();

	}
}
