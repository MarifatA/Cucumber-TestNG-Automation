package com.app.tests;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.app.pages.GasMileageCalculatorPage;
import com.app.utilities.ConfigurationReader;
import com.app.utilities.Driver;

/*
 * Data Driven Test
 * Test Parametrization
 * Some modules in application needs to be tested with multiple inputs.
 * Whenever we encounter this kind of scenario we do data drivern test.
 * Data can come form different external sources.
 * Excel file,Feature files,Databases, csv files,API calls,JSon
 */
public class BasicDataDrivenTest {
	WebDriver driver;
	Workbook workbook;
	Sheet worksheet;
	FileInputStream inStream;
	FileOutputStream outStream;
	GasMileageCalculatorPage gasPage;

	public static final int CURRENTOD_COLNUM = 1;
	public static final int PREVIOUSOD_COLNUM = 2;
	public static final int GAS_COLNUM = 3;

	@BeforeClass
	public void setUp() throws Exception {

		inStream = new FileInputStream(ConfigurationReader.getProperty("gasmileage.testdata.path"));
		workbook = WorkbookFactory.create(inStream);
		worksheet = workbook.getSheetAt(0);
		driver = Driver.getDriver();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.get("http://www.calculator.net/gas-mileage-calculator.html");

	}

	@Test
	public void dataDrivenMileageCalculatorTest() {

		for (int rowNum = 1; rowNum < worksheet.getPhysicalNumberOfRows(); rowNum++) {

			Row currentRow = worksheet.getRow(rowNum);

			// CHECK THE CONTROL COLUMN.IF IT DOESN'T SAY 'Y' THEN SKIP THIS ROW
			if (!currentRow.getCell(0).toString().equalsIgnoreCase("Y")) {
				if (currentRow.getCell(6) == null) {
					currentRow.createCell(6);
				}
				currentRow.getCell(6).setCellValue("Skip Requested");
				continue;
			}

			double currentOR = currentRow.getCell(CURRENTOD_COLNUM).getNumericCellValue();
			double previousOR = currentRow.getCell(PREVIOUSOD_COLNUM).getNumericCellValue();
			double gasI = currentRow.getCell(GAS_COLNUM).getNumericCellValue();

			gasPage = new GasMileageCalculatorPage();
			gasPage.currentOdometer.clear();
			gasPage.currentOdometer.sendKeys(String.valueOf(currentOR)); // to convert int to String
			
			gasPage.previousOdometer.clear();
			gasPage.previousOdometer.sendKeys(String.valueOf(previousOR));
			
			gasPage.gas.clear();
			gasPage.gas.sendKeys(String.valueOf(gasI));
			gasPage.calculateBtn.click();
			

			// WRITE RESULT TO ACTUAL COLUMN
			String[] result = gasPage.text.getText().split(" ");
			System.out.println(result[0]);

			String actualResult = result[0].replace(",", "");
			if (currentRow.getCell(5) == null) {
				currentRow.createCell(5);
			}
			currentRow.getCell(5).setCellValue(actualResult);

			double calculationResult = (currentOR - previousOR) / gasI;
			DecimalFormat format = new DecimalFormat("#0.00");
			System.out.println(format.format(calculationResult));

			// WRITE RESULT TO EXPECTED COLUMN
			if (currentRow.getCell(4) == null) {
				currentRow.createCell(4);
			}
			currentRow.getCell(4).setCellValue(format.format(calculationResult));

			// WRITE PASS OR FAIL TO STATUS COLUMN
			if (currentRow.getCell(6) == null) {
				currentRow.createCell(6);
			}
			if (actualResult.equals(format.format(calculationResult))) {
				currentRow.getCell(6).setCellValue("PASS");
				System.out.println("Pass");
			} else {
				System.out.println("Fail");
				currentRow.getCell(6).setCellValue("FAIL");
			}

			// WRITE CURRENT TIME 
			if (currentRow.getCell(7) == null) {
				currentRow.createCell(7);
			}
			currentRow.getCell(7).setCellValue(LocalDateTime.now().toString());
		}
	}

	@AfterClass
	public void tearDown() throws IOException {
		outStream = new FileOutputStream(ConfigurationReader.getProperty("gasmileage.testdata.path"));
		workbook.write(outStream);
		outStream.close();
		inStream.close();
		workbook.close();

		Driver.getDriver().close();
	}
}
