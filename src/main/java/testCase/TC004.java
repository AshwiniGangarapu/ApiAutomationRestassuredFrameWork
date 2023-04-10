package testCase;

import java.util.HashMap;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utils.ExcelDataProvider;
import webservices.TeacherService;
import wrappers.ProjectWrappers;

public class TC004 extends ProjectWrappers {
	
	@BeforeClass 
	public void beforeClass() {
		  testCaseName="Creat Teacher";
		  testCaseDescription="To verify whether the user is able to create a Student record with valid details";
		  categrory="Smoke";
		  author="Jani Basha Shaik";
	}
	 
	@Test(dataProvider = "getExcelDataByKeys", dataProviderClass = ExcelDataProvider.class)
	public void createTeacher(HashMap<String, String> map) {
		TeacherService teacher = new TeacherService(test);
		teacher.createTeacher(map); 
	}
	
	@Test(dataProvider = "getExcelDataByKeys", dataProviderClass = ExcelDataProvider.class)	
	public void createTeacherMissingFiledsValidation(HashMap<String, String> map) {
		TeacherService teacher = new TeacherService(test);
		teacher.createTeacher(map); 
	}
	
	@Test(dataProvider = "getExcelDataByKeys", dataProviderClass = ExcelDataProvider.class)	
	public void createTeacherInvalidFiledsValidation(HashMap<String, String> map) {
		TeacherService teacher = new TeacherService(test);
		teacher.createTeacher(map); 
	}

}
