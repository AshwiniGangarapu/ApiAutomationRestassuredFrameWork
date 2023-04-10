package testCase;

import java.util.HashMap;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utils.ExcelDataProvider;
import webservices.ParentService;
import wrappers.ProjectWrappers;

public class TC003 extends ProjectWrappers {
	
	@BeforeClass 
	public void beforeClass() {
		  testCaseName="Creat Parent";
		  testCaseDescription="To verify whether the user is able to create a Student record with valid details";
		  categrory="Smoke";
		  author="Jani Basha Shaik";  
	}
	
	@Test(dataProvider = "getExcelDataByKeys", dataProviderClass = ExcelDataProvider.class)	
	public void createParent(HashMap<String, String> map) {
		ParentService parent = new ParentService(test);
		parent.createParent(map);  
	}
	
	@Test(dataProvider = "getExcelDataByKeys", dataProviderClass = ExcelDataProvider.class)	
	public void createParentMissingFiledsValidation(HashMap<String, String> map) {
		ParentService parent = new ParentService(test);
		parent.createParent(map);
	}
	
	@Test(dataProvider = "getExcelDataByKeys", dataProviderClass = ExcelDataProvider.class)	
	public void createParentInvalidFiledsValidation(HashMap<String, String> map) {
		ParentService parent = new ParentService(test);
		parent.createParent(map);
	}
}
