package utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;

import base.BaseTest;

public class TestUtil extends BaseTest {

	public static String fileName;

	public static void captureScreenshot() throws IOException {

		Date d = new Date();
		fileName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";

		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotFile, new File("./reports/" + fileName));

	}

	@DataProvider(name = "dp")
	public static Object[][] getData(Method m) {

		String sheetName = m.getName();

		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);
		/*
		 * System.out.println(rows); System.out.print(cols);
		 */
		Object[][] data = new Object[rows - 1][cols];

		for (int rowNum = 2; rowNum <= rows; rowNum++) {
		  for (int colNum = 0; colNum < cols; colNum++) {

				data[rowNum - 2][colNum] = excel.getCellData(sheetName, colNum, rowNum);

				/*
				 * data[0][0] = "java@way2Automation.com"; data[0][1] = "abababab";
				 * 
				 * data[1][0] = "trainer@way2Automation.com"; data[1][1] = "swsssssss";
				 * 
				 * data[2][0] = "corp@way2Automation.com"; data[2][1] = "sfsf43234fwrwfw";
				 * 
				 */
			}
		}
		return data;
	}

}
