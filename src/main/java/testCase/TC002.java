package testCase;

import java.util.HashMap;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utils.ExcelDataProvider;
import webservices.StudentService;
import wrappers.ProjectWrappers;

public class TC002 extends ProjectWrappers {
	
	@BeforeClass 
	public void beforeClass() {
		  testCaseName="Creat Student";
		  testCaseDescription="To verify whether the user is able to create a Student record with valid details";
		  categrory="Smoke";
		  author="Jani Basha Shaik";
	}
	
	@Test(dataProvider = "getExcelDataByKeys", dataProviderClass = ExcelDataProvider.class)
	public void createStudent(HashMap<String, String> map) {
		StudentService student = new StudentService(test);
		student.createStudent(map); 
	}
	
	@Test(dataProvider = "getExcelDataByKeys", dataProviderClass = ExcelDataProvider.class)	
	public void createStudentMissingFiledsValidation(HashMap<String, String> map) {
		StudentService student = new StudentService(test);
		student.createStudent(map);
	}
	
	@Test(dataProvider = "getExcelDataByKeys", dataProviderClass = ExcelDataProvider.class)	
	public void createStudentInvalidFiledsValidation(HashMap<String, String> map) {
		StudentService student = new StudentService(test);
		student.createStudent(map);
	}

}
