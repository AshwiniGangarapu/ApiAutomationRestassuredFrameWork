package webservices;

import java.sql.ResultSet;
import java.util.HashMap;

import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;

import io.restassured.response.Response;
import wrappers.GenericWrappers;

public class ParentService extends GenericWrappers {

	public ParentService(ExtentTest test) {
		this.test = test;
	}

	public HashMap<String, Object> getBody(HashMap<String, String> map) {
		HashMap<String, Object> data = null;
		if (map.get("MissingField") == null) {
			map.put("MissingField", "positive");
		}
		switch (map.get("MissingField")) {
		case "ParentName":
			data = new HashMap<>();
			data.put("age", Integer.valueOf(map.get("Age")));
			data.put("student_name", map.get("Student  Name"));
			data.put("blood_group", map.get("Blood Group"));
			data.put("educational_qualification", map.get("Education Qualification"));
			data.put("phone_number", Long.parseLong((map.get("Parent Phone"))));
			data.put("address", map.get("Address"));
			break;

		case "Age":
			data = new HashMap<>();
			data.put("parent_name", map.get("Name"));
			data.put("student_name", map.get("Student  Name"));
			data.put("blood_group", map.get("Blood Group"));
			data.put("educational_qualification", map.get("Education Qualification"));
			data.put("phone_number", Long.parseLong((map.get("Parent Phone"))));
			data.put("address", map.get("Address"));
			break;

		case "StudentName":
			data = new HashMap<>();
			data.put("parent_name", map.get("Name"));
			data.put("age", Integer.valueOf(map.get("Age")));
			data.put("blood_group", map.get("Blood Group"));
			data.put("educational_qualification", map.get("Education Qualification"));
			data.put("phone_number", Long.parseLong((map.get("Parent Phone"))));
			data.put("address", map.get("Address"));
			break;

		case "BloodGroup":
			data = new HashMap<>();
			data.put("parent_name", map.get("Name"));
			data.put("age", Integer.valueOf(map.get("Age")));
			data.put("student_name", map.get("Student  Name"));
			data.put("educational_qualification", map.get("Education Qualification"));
			data.put("phone_number", Long.parseLong((map.get("Parent Phone"))));
			data.put("address", map.get("Address"));
			break;

		case "EducationQualification":
			data = new HashMap<>();
			data.put("parent_name", map.get("Name"));
			data.put("age", Integer.valueOf(map.get("Age")));
			data.put("student_name", map.get("Student  Name"));
			data.put("blood_group", map.get("Blood Group"));
			data.put("phone_number", Long.parseLong((map.get("Parent Phone"))));
			data.put("address", map.get("Address"));
			break;

		case "PhoneNumber":
			data = new HashMap<>();
			data.put("parent_name", map.get("Name"));
			data.put("age", Integer.valueOf(map.get("Age")));
			data.put("student_name", map.get("Student  Name"));
			data.put("blood_group", map.get("Blood Group"));
			data.put("educational_qualification", map.get("Education Qualification"));
			data.put("address", map.get("Address"));
			break;

		case "Address":
			data = new HashMap<>();
			data.put("parent_name", map.get("Name"));
			data.put("age", Integer.valueOf(map.get("Age")));
			data.put("student_name", map.get("Student  Name"));
			data.put("blood_group", map.get("Blood Group"));
			data.put("educational_qualification", map.get("Education Qualification"));
			data.put("phone_number", Long.parseLong((map.get("Parent Phone"))));
			break;

		default:
			data = new HashMap<>();
			data.put("parent_name", map.get("Name"));
			try {
				data.put("age", Long.parseLong(map.get("Age")));
			} catch (Exception e) {
				data.put("age", map.get("Age"));
			}

			data.put("student_name", map.get("Student  Name"));
			data.put("blood_group", map.get("Blood Group"));
			data.put("educational_qualification", map.get("Education Qualification"));
			try {
				data.put("phone_number", Long.parseLong((map.get("Parent Phone"))));
			} catch (Exception e) {
				data.put("phone_number", map.get("Parent Phone"));
			}

			data.put("address", map.get("Address"));
			break;
		}
		return data;
	}

	public void createParent(HashMap<String, String> map) {

		String endPoint = prop.getProperty("BaseURL").concat(prop.getProperty("ParentEndPoint"));
		logTestSteps("pass", "Endpoint: " + endPoint);

		String HTTPMethod = null;
		if (endPoint.contains("create")) {
			HTTPMethod = "post";
		} else if (endPoint.contains("search")) {
			HTTPMethod = "get";
		} else if (endPoint.contains("delete")) {
			HTTPMethod = "delete";
		}
		logTestSteps("pass", "HTTP Method: " + HTTPMethod);

		HashMap<String, Object> data = getBody(map);
		logTestSteps("pass", "User Input: " + data);

		String body = createBody(data);
		logTestSteps("pass", "Json Body: " + body);

		try {
			Response rep = sendCustomRequest(HTTPMethod, endPoint, body, map.get("ExpectedStatusCode"));
			logTestSteps("pass", "Send Customer Responce: Status => " + rep.jsonPath().getString("Status"));
			logTestSteps("pass", "Send Customer Responce: Description => " + rep.jsonPath().getString("description"));
			logTestSteps("pass", "Send Customer Responce: Get Status Code => " + rep.getStatusCode());
			logTestSteps("pass", "Send Customer Responce: Get Status Line => " + rep.getStatusLine());

			ResultSet rs = connectToDB("select * from school_db.parents_details where parent_name='"
					+ data.get("driver_name") + "' order by 1 desc LIMIT 1;");

			String column = prop.getProperty("ParentTableColumn");
			String[] columnName = column.split(",");

			while (rs.next()) {
				for (String c : columnName) {
					try {
						Assert.assertEquals(data.get(c).toString(), rs.getString(c));
						logTestSteps("pass", "Validate Customer Details in DB: " + c + " => " + rs.getString(c));
					} catch (AssertionError error) {
						logTestSteps("Fail", "Exception -  " + error);
						throw new AssertionError();
					}
				}
			}

		} catch (Exception e) {
			logTestSteps("Fail", "Exception :" + e);
		}
	}

}
