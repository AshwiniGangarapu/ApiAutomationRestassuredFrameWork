package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Reports {

	public static ExtentReports extents;
	public ExtentTest test;
	public String testCaseName, testCaseDescription, author, categrory;

	public void startReport() {

		String date = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

		extents = new ExtentReports("./Reports/" + date + "/Result.html", false);
	}

	public void startTest(String testName, String description) {
		
		test = extents.startTest(testName, description);
		test.assignAuthor(author);
		test.assignCategory(categrory);
	}

	public void logTestSteps(String status, String description) {
		if (status.equalsIgnoreCase("PASS")) {
			test.log(LogStatus.PASS, description);
		} else if (status.equalsIgnoreCase("FAIL")) {
			test.log(LogStatus.FAIL, description);
		} else if (status.equalsIgnoreCase("ERROR")) {
			test.log(LogStatus.ERROR, description);
		}
	}

	public void endTest() {
		extents.endTest(test);
	}

	public void endReport() {
		extents.flush();
	}
}
