package testCase;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.TestConfiguration;
import webservices.DriverService;
import wrappers.ProjectWrappers;

import java.util.HashMap;

public class ExternalUser extends ProjectWrappers {




    @BeforeClass
    public void beforeClass() {

        categrory = "Smoke";
        author = "Jani Basha Shaik";
    }

    @Test(groups = "Test")
    @TestConfiguration(excelsheetname = "result", excelfilename = "TestCaseFile")
    public void addExternalUser(HashMap<String, Object> map) {
        DriverService driver = new DriverService(test);
        System.out.println(map);
        driver.createDriver(map);
    }


    @Test(groups = "Test")
    @TestConfiguration(excelsheetname = "result", excelfilename = "TestCaseFile")
    public void addExternalUserMissingFieldValidations(HashMap<String, Object> map) {
        DriverService driver = new DriverService(test);
        System.out.println(map);
        driver.createDriver(map);
    }


    @Test(groups = "Test")
    @TestConfiguration(excelsheetname = "result", excelfilename = "TestCaseFile")
    public void addExternalUserEmptyFieldValidations(HashMap<String, Object> map) {
        DriverService driver = new DriverService(test);
        System.out.println(map);
        driver.createDriver(map);
    }

    @Test(groups = "Test")
    @TestConfiguration(excelsheetname = "result", excelfilename = "TestCaseFile")
    public void addExternalUserInvalidFieldValidations(HashMap<String, Object> map) {
        DriverService driver = new DriverService(test);
        System.out.println(map);
        driver.createDriver(map);
    }










}
