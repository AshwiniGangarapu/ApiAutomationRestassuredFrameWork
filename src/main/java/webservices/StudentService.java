package webservices;

import java.sql.ResultSet;
import java.util.HashMap;

import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;

import io.restassured.response.Response;
import wrappers.GenericWrappers;

public class StudentService extends GenericWrappers {

	public StudentService(ExtentTest test) {
		this.test = test;
	}

	public HashMap<String, Object> getBody(HashMap<String, String> map) {
		HashMap<String, Object> data = null;
		if (map.get("MissingField") == null) {
			map.put("MissingField", "positive");
		}
		switch (map.get("MissingField")) {
		case "StudentName":
			data = new HashMap<>();
			data.put("age", Long.parseLong(map.get("Age")));
			data.put("class_name", map.get("Class Name"));
			data.put("section", map.get("Section"));
			data.put("blood_group", map.get("Blood Group"));
			data.put("parent_name", map.get("Parent Name"));
			data.put("parent_phone_number", Long.parseLong((map.get("Parent Phone"))));
			data.put("class_teacher", map.get("Class Teacher"));
			data.put("favourite_sport", map.get("Favourite Sport"));
			data.put("favourite_subject", map.get("favourite_subject"));
			break;

		case "Age":
			data = new HashMap<>();
			data.put("student_name", map.get("Name"));
			data.put("class_name", map.get("Class Name"));
			data.put("section", map.get("Section"));
			data.put("blood_group", map.get("Blood Group"));
			data.put("parent_name", map.get("Parent Name"));
			data.put("parent_phone_number", Long.parseLong((map.get("Parent Phone"))));
			data.put("class_teacher", map.get("Class Teacher"));
			data.put("favourite_sport", map.get("Favourite Sport"));
			data.put("favourite_subject", map.get("favourite_subject"));
			break;

		case "ClassName":
			data = new HashMap<>();
			data.put("student_name", map.get("Name"));
			data.put("age", Long.parseLong(map.get("Age")));
			data.put("section", map.get("Section"));
			data.put("blood_group", map.get("Blood Group"));
			data.put("parent_name", map.get("Parent Name"));
			data.put("parent_phone_number", Long.parseLong((map.get("Parent Phone"))));
			data.put("class_teacher", map.get("Class Teacher"));
			data.put("favourite_sport", map.get("Favourite Sport"));
			data.put("favourite_subject", map.get("favourite_subject"));
			break;

		case "Section":
			data = new HashMap<>();
			data.put("student_name", map.get("Name"));
			data.put("age", Long.parseLong(map.get("Age")));
			data.put("class_name", map.get("Class Name"));
			data.put("blood_group", map.get("Blood Group"));
			data.put("parent_name", map.get("Parent Name"));
			data.put("parent_phone_number", Long.parseLong((map.get("Parent Phone"))));
			data.put("class_teacher", map.get("Class Teacher"));
			data.put("favourite_sport", map.get("Favourite Sport"));
			data.put("favourite_subject", map.get("favourite_subject"));
			break;

		case "BloodGroup":
			data = new HashMap<>();
			data.put("student_name", map.get("Name"));
			data.put("age", Long.parseLong(map.get("Age")));
			data.put("class_name", map.get("Class Name"));
			data.put("blood_group", map.get("Blood Group"));
			data.put("parent_name", map.get("Parent Name"));
			data.put("parent_phone_number", Long.parseLong((map.get("Parent Phone"))));
			data.put("class_teacher", map.get("Class Teacher"));
			data.put("favourite_sport", map.get("Favourite Sport"));
			data.put("favourite_subject", map.get("favourite_subject"));
			break;

		case "ParentName":
			data = new HashMap<>();
			data.put("student_name", map.get("Name"));
			data.put("age", Long.parseLong(map.get("Age")));
			data.put("class_name", map.get("Class Name"));
			data.put("section", map.get("Section"));
			data.put("blood_group", map.get("Blood Group"));
			data.put("parent_phone_number", Long.parseLong((map.get("Parent Phone"))));
			data.put("class_teacher", map.get("Class Teacher"));
			data.put("favourite_sport", map.get("Favourite Sport"));
			data.put("favourite_subject", map.get("favourite_subject"));
			break;

		case "ParentPhone":
			data = new HashMap<>();
			data.put("student_name", map.get("Name"));
			data.put("age", Long.parseLong(map.get("Age")));
			data.put("class_name", map.get("Class Name"));
			data.put("section", map.get("Section"));
			data.put("blood_group", map.get("Blood Group"));
			data.put("parent_name", map.get("Parent Name"));
			data.put("class_teacher", map.get("Class Teacher"));
			data.put("favourite_sport", map.get("Favourite Sport"));
			data.put("favourite_subject", map.get("favourite_subject"));
			break;

		case "ClassTeacher":
			data = new HashMap<>();
			data.put("student_name", map.get("Name"));
			data.put("age", Long.parseLong(map.get("Age")));
			data.put("class_name", map.get("Class Name"));
			data.put("section", map.get("Section"));
			data.put("blood_group", map.get("Blood Group"));
			data.put("parent_name", map.get("Parent Name"));
			data.put("parent_phone_number", Long.parseLong((map.get("Parent Phone"))));
			data.put("favourite_sport", map.get("Favourite Sport"));
			data.put("favourite_subject", map.get("favourite_subject"));
			break;

		case "FavouriteSport":
			data = new HashMap<>();
			data.put("student_name", map.get("Name"));
			data.put("age", Long.parseLong(map.get("Age")));
			data.put("class_name", map.get("Class Name"));
			data.put("section", map.get("Section"));
			data.put("blood_group", map.get("Blood Group"));
			data.put("parent_name", map.get("Parent Name"));
			data.put("parent_phone_number", Long.parseLong((map.get("Parent Phone"))));
			data.put("class_teacher", map.get("Class Teacher"));
			data.put("favourite_subject", map.get("favourite_subject"));
			break;

		case "FavouriteSubject":
			data = new HashMap<>();
			data.put("student_name", map.get("Name"));
			data.put("age", Long.parseLong(map.get("Age")));
			data.put("class_name", map.get("Class Name"));
			data.put("section", map.get("Section"));
			data.put("blood_group", map.get("Blood Group"));
			data.put("parent_name", map.get("Parent Name"));
			data.put("parent_phone_number", Long.parseLong((map.get("Parent Phone"))));
			data.put("class_teacher", map.get("Class Teacher"));
			data.put("favourite_sport", map.get("Favourite Sport"));
			break;

		default:
			data = new HashMap<>();
			data.put("student_name", map.get("Name"));
			try {
				data.put("age", Long.parseLong(map.get("Age")));
			} catch (Exception e) {
				data.put("age", map.get("Age"));
			}

			data.put("class_name", map.get("Class Name"));
			data.put("section", map.get("Section"));
			data.put("blood_group", map.get("Blood Group"));
			data.put("parent_name", map.get("Parent Name"));
			try {
				data.put("parent_phone_number", Long.parseLong((map.get("Parent Phone"))));
			} catch (Exception e) {
				data.put("parent_phone_number", map.get("Parent Phone"));
			}

			data.put("class_teacher", map.get("Class Teacher"));
			data.put("favourite_sport", map.get("Favourite Sport"));
			data.put("favourite_subject", map.get("favourite_subject"));
			break;
		}
		return data;

	}

	public void createStudent(HashMap<String, String> map) {

		String endPoint = prop.getProperty("BaseURL").concat(prop.getProperty("StudentEndPoint"));
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

			ResultSet rs = connectToDB("select * from school_db.student_details where student_name='"
					+ data.get("student_name") + "' order by 1 desc LIMIT 1;");

			String column = prop.getProperty("StudentTableColumn");
			String[] columnName = column.split(",");
rs.getFetchSize();
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
