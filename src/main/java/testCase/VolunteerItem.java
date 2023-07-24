package testCase;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.TestConfiguration;
import webservices.AddUserByAdminService;
import webservices.DriverService;
import webservices.VolunteerItemService;
import wrappers.ProjectWrappers;

import java.util.HashMap;

public class VolunteerItem extends ProjectWrappers {

    @BeforeClass
    public void beforeClass() {

        categrory = "Smoke";
        author = "Ashwini";
    }

    @Test(groups = "Test")
    @TestConfiguration(excelsheetname = "Sheet2", excelfilename = "TestDataSheetLatest")
    public void PostVolunteerItem(HashMap<String, Object> map) {
        VolunteerItemService driver = new VolunteerItemService(test);
        System.out.println(map);
        driver.VolunteerItemHappyPath(map);
    }


    @Test(groups = "Test")
    @TestConfiguration(excelsheetname = "Sheet2", excelfilename = "TestDataSheetLatest")
    public void PostVolunteerItemMissingFieldValidations(HashMap<String, Object> map) {
        VolunteerItemService driver = new VolunteerItemService(test);
        driver.PostVolunteerItemMissingFieldValidations(map);
      }

    @Test(groups = "Test")
    @TestConfiguration(excelsheetname = "Sheet2", excelfilename = "TestDataSheetLatest")
    public void PostVolunteerItemEmptyFieldValidations(HashMap<String, Object> map) {
        VolunteerItemService driver = new VolunteerItemService(test);
        driver.PostVolunteerItemEmptyFieldValidations(map);
    }

    @Test(groups = "Test")
    @TestConfiguration(excelsheetname = "Sheet2", excelfilename = "TestDataSheetLatest")
    public void PostVolunteerItemDuplicateValidations(HashMap<String, Object> map) {
        VolunteerItemService driver = new VolunteerItemService(test);
        driver.PostVolunteerItemDuplicateValidations(map);
    }

    /*@Test(groups = "Test")
      @TestConfiguration(excelsheetname = "Sheet2", excelfilename = "TestDataSheetLatest")
    public void PostVolunteerItemInvalidFieldValidations(HashMap<String, Object> map) {
        VolunteerItemService driver = new VolunteerItemService(test);
        driver.PostVolunteerItemInvalidFieldValidations(map);
    }*/





}
