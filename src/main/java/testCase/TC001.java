package testCase;

import java.util.HashMap;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utils.TestConfiguration;
import webservices.DriverService;
import wrappers.ProjectWrappers;

public class TC001 extends ProjectWrappers {

	@BeforeClass
	public void beforeClass() {
	
		categrory = "Smoke";
		author = "Jani Basha Shaik";
	}

	@Test(groups = "Test")
	@TestConfiguration(excelsheetname = "result", excelfilename = "TestCaseFile")
	public void createDriver(HashMap<String, Object> map) {
		DriverService driver = new DriverService(test);
		System.out.println(map);
		driver.createDriver(map);
	}

	
	  @TestConfiguration(excelsheetname = "result", excelfilename = "TestCaseFile")
	  public void createDriverMissingFiledsValidation(HashMap<String, String> map)
	  { DriverService driver = new DriverService(test); driver.createDriver(map); }
	  
	  @TestConfiguration(excelsheetname = "result", excelfilename = "TestCaseFile")
	  public void createDriverInvalidFiledsValidation(HashMap<String, String> map)
	  { DriverService driver = new DriverService(test); driver.createDriver(map); }
	 
}
