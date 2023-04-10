package webservices;

import java.sql.ResultSet;
import java.util.HashMap;

import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;

import io.restassured.response.Response;
import wrappers.GenericWrappers;

public class TeacherService extends GenericWrappers {

	public TeacherService(ExtentTest test) {
		this.test = test;
	}

	public HashMap<String, Object> getBody(HashMap<String, String> map) {
		HashMap<String, Object> data = null;
		if (map.get("MissingField") == null) {
			map.put("MissingField", "positive");
		}
		switch (map.get("MissingField")) {
		case "TeacherName":
			data = new HashMap<>();
			data.put("age", Long.parseLong(map.get("Age")));
			data.put("address", map.get("Address"));
			data.put("head_of_class", map.get("Head of Class"));
			data.put("subject", map.get("Subject"));
			data.put("years_of_experience", map.get("Year of Experience"));
			data.put("phone_number", Long.parseLong((map.get("Phone"))));
			data.put("educational_qualification", map.get("Education Qualification"));
			data.put("complaints", map.get("Complaints"));
			break;

		case "Age":
			data = new HashMap<>();
			data.put("teacher_name", map.get("Name"));
			data.put("address", map.get("Address"));
			data.put("head_of_class", map.get("Head of Class"));
			data.put("subject", map.get("Subject"));
			data.put("years_of_experience", map.get("Year of Experience"));
			data.put("phone_number", Long.parseLong((map.get("Phone"))));
			data.put("educational_qualification", map.get("Education Qualification"));
			data.put("complaints", map.get("Complaints"));
			break;

		case "Address":
			data = new HashMap<>();
			data.put("teacher_name", map.get("Name"));
			data.put("age", Long.parseLong(map.get("Age")));
			data.put("head_of_class", map.get("Head of Class"));
			data.put("subject", map.get("Subject"));
			data.put("years_of_experience", map.get("Year of Experience"));
			data.put("phone_number", Long.parseLong((map.get("Phone"))));
			data.put("educational_qualification", map.get("Education Qualification"));
			data.put("complaints", map.get("Complaints"));
			break;

		case "HeadofClass":
			data = new HashMap<>();
			data.put("teacher_name", map.get("Name"));
			data.put("age", Long.parseLong(map.get("Age")));
			data.put("address", map.get("Address"));
			data.put("subject", map.get("Subject"));
			data.put("years_of_experience", map.get("Year of Experience"));
			data.put("phone_number", Long.parseLong((map.get("Phone"))));
			data.put("educational_qualification", map.get("Education Qualification"));
			data.put("complaints", map.get("Complaints"));
			break;

		case "Subject":
			data = new HashMap<>();
			data.put("teacher_name", map.get("Name"));
			data.put("age", Long.parseLong(map.get("Age")));
			data.put("address", map.get("Address"));
			data.put("head_of_class", map.get("Head of Class"));
			data.put("years_of_experience", map.get("Year of Experience"));
			data.put("phone_number", Long.parseLong((map.get("Phone"))));
			data.put("educational_qualification", map.get("Education Qualification"));
			data.put("complaints", map.get("Complaints"));
			break;

		case "YearofExperience":
			data = new HashMap<>();
			data.put("teacher_name", map.get("Name"));
			data.put("age", Long.parseLong(map.get("Age")));
			data.put("address", map.get("Address"));
			data.put("head_of_class", map.get("Head of Class"));
			data.put("subject", map.get("Subject"));
			data.put("phone_number", Long.parseLong((map.get("Phone"))));
			data.put("educational_qualification", map.get("Education Qualification"));
			data.put("complaints", map.get("Complaints"));
			break;

		case "Phone":
			data = new HashMap<>();
			data.put("teacher_name", map.get("Name"));
			data.put("age", Long.parseLong(map.get("Age")));
			data.put("address", map.get("Address"));
			data.put("head_of_class", map.get("Head of Class"));
			data.put("subject", map.get("Subject"));
			data.put("years_of_experience", map.get("Year of Experience"));
			data.put("educational_qualification", map.get("Education Qualification"));
			data.put("complaints", map.get("Complaints"));
			break;

		case "EducationQualification":
			data = new HashMap<>();
			data.put("teacher_name", map.get("Name"));
			data.put("age", Long.parseLong(map.get("Age")));
			data.put("address", map.get("Address"));
			data.put("head_of_class", map.get("Head of Class"));
			data.put("subject", map.get("Subject"));
			data.put("years_of_experience", map.get("Year of Experience"));
			data.put("phone_number", Long.parseLong((map.get("Phone"))));
			data.put("complaints", map.get("Complaints"));
			break;

		case "Complaints":
			data = new HashMap<>();
			data.put("teacher_name", map.get("Name"));
			data.put("age", Long.parseLong(map.get("Age")));
			data.put("address", map.get("Address"));
			data.put("head_of_class", map.get("Head of Class"));
			data.put("subject", map.get("Subject"));
			data.put("years_of_experience", map.get("Year of Experience"));
			data.put("phone_number", Long.parseLong((map.get("Phone"))));
			data.put("educational_qualification", map.get("Education Qualification"));
			break;

		default:
			data = new HashMap<>();
			data.put("teacher_name", map.get("Name"));
			try {
				data.put("age", Long.parseLong(map.get("Age")));
			} catch (Exception e) {
				data.put("age", map.get("Age"));
			}

			data.put("address", map.get("Address"));
			data.put("head_of_class", map.get("Head of Class"));
			data.put("subject", map.get("Subject"));
			data.put("years_of_experience", map.get("Year of Experience"));
			try {
				data.put("phone_number", Long.parseLong((map.get("Phone"))));
			} catch (Exception e) {
				data.put("phone_number", map.get("Phone"));
			}

			data.put("educational_qualification", map.get("Education Qualification"));
			data.put("complaints", map.get("Complaints"));
			break;
		}
		return data;
	}

	public void createTeacher(HashMap<String, String> map) {

		String endPoint = prop.getProperty("BaseURL").concat(prop.getProperty("TeacherEndPoint"));
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

			ResultSet rs = connectToDB("select * from school_db.teacher_details where teacher_name='"
					+ data.get("teacher_name") + "' order by 1 desc LIMIT 1;");

			String column = prop.getProperty("TeacherTableColumn");
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
