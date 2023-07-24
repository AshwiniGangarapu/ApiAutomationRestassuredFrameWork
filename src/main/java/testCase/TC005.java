package testCase;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.ParameterHandler;
import utils.TestConfiguration;
import webservices.AddUserByAdminService;
import webservices.DriverService;
import wrappers.ProjectWrappers;

import java.util.HashMap;

public class TC005 extends ProjectWrappers {
    @BeforeClass
    public void beforeClass() {
        categrory = "Smoke";
        author = "Ashwini";
    }

    @Test(groups = "Test",priority=1)
    @TestConfiguration(excelsheetname = "Sheet1", excelfilename = "TestDataSheetLatest")
    public void addUsersByAdmin(HashMap<String, Object> map) {
        AddUserByAdminService driver = new AddUserByAdminService(test);
        System.out.println(map);
        driver.addUserByAdminHappyPath(map);
    }


   @Test(groups = "Test",priority=2)
    @TestConfiguration(excelsheetname = "Sheet1", excelfilename = "TestDataSheetLatest")
    public void addUsersByAdminMissingFieldValidations(HashMap<String, Object> map) {
        AddUserByAdminService obj = new AddUserByAdminService(test);
        obj.addUserByAdminMissingFieldValidations(map);
    }


   @Test(groups = "Test",priority=3)
   @TestConfiguration(excelsheetname = "Sheet1", excelfilename = "TestDataSheetLatest")
    public void addUsersByAdminEmptyFieldValidations(HashMap<String, Object> map) {
       AddUserByAdminService obj = new AddUserByAdminService(test);
       obj.addUsersByAdminEmptyFieldValidations(map);
    }

    @Test(groups = "Test",priority=4)
    @TestConfiguration(excelsheetname = "Sheet1", excelfilename = "TestDataSheetLatest")
    public void addUserByAdminDuplicateEntryValidations(HashMap<String, Object> map) {
        AddUserByAdminService obj = new AddUserByAdminService(test);
        obj.addUserByAdminDuplicateEntryValidations(map);
    }

    @Test(groups = "Test",priority=5)
    @TestConfiguration(excelsheetname = "Sheet1", excelfilename = "TestDataSheetLatest")
    public void getUserByUserId(HashMap<String, Object> map) {
        AddUserByAdminService obj = new AddUserByAdminService(test);
        obj.addUserByAdminHappyPath(map);
        obj.getUserByUserId(map);
    }

    @Test(groups = "Test",priority=6)
    @TestConfiguration(excelsheetname = "Sheet1", excelfilename = "TestDataSheetLatest")
    public void getUserByUserIdNegativeValidation(HashMap<String, Object> map) {
        AddUserByAdminService obj = new AddUserByAdminService(test);
        obj.getUserByUserIdNegativeValidation(map);
    }



    @Test(groups = "Test",priority=7)
    @TestConfiguration(excelsheetname = "Sheet1", excelfilename = "TestDataSheetLatest")
    public void addUsersByAdminInvalidFieldValidations(HashMap<String, Object> map) {
        AddUserByAdminService obj = new AddUserByAdminService(test);
        obj.addUsersByAdminInvalidFieldValidations(map);
    }


}
