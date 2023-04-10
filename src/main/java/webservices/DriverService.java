package webservices;

import java.util.HashMap;

import org.json.simple.JSONObject;

import com.relevantcodes.extentreports.ExtentTest;

import io.restassured.response.Response;
import wrappers.GenericWrappers;

public class DriverService extends GenericWrappers {

	public DriverService(ExtentTest test) {
		this.test = test;
	}

	public void createDriver(HashMap<String, Object> map) {

		String endPoint = prop.getProperty("BaseURL").concat(prop.getProperty("DriverEndPoint"));

		JSONObject data = createBodyFromJsonFile(map, "ReadJsonCreateDriver");
		String body = data.toString();

		try {
			Response rep = sendCustomRequest(map.get("HTTPMethod").toString(), endPoint, body, map.get("ExpectedStatusCode").toString());
			logResponse(rep);
			if(rep.getStatusCode() == 201) {
			String sql = prop.getProperty("createDriverSQL");
			validateDBRecord(sql.replaceFirst("##", data.get("driver_name").toString()), "DriverTableColumn", data);
			}
		} catch (Exception e) {
			logTestSteps("Fail", "Exception :" + e);
		}

	}
}
