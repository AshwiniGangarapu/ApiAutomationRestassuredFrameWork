package wrappers;

import java.lang.reflect.Method;
import java.util.HashMap;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.BeforeMethod;

public class ProjectWrappers extends GenericWrappers {

	@BeforeSuite
	public void beforeSuite() {
		startReport();
	}

	@BeforeTest
	public void beforTest() {
		loadObjects();
	}

	@BeforeMethod
	public void beforeMethod(Method method, Object[] testArgs) {
		System.out.println("Before method");
		
		
		HashMap<Object, Object> map = (HashMap<Object, Object>) testArgs[0];
		System.out.println(map.get("Description").toString());
		 String desc = map.get("Description").toString().concat(" - ").concat(map.get("Validation Type").toString());
		startTest(method.getName(),desc);
	}

	@AfterMethod
	public void afterMethid() {

	}

	@AfterClass
	public void afterClass() {
		endTest();
	}

	@AfterTest
	public void afterTest() {
		unloadObjects();
	}

	@AfterSuite
	public void afterSuite() {
		endReport();
	}
}
