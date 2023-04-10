package wrappers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import java.nio.file.FileSystemNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.Reports;

public class GenericWrappers extends Reports implements Wrappers {

	public static Properties prop;

	public Response sendCustomRequest(String httpMethod, String endPoint, String body, String expectedStatusCode) {
		
		Response response = null;
		logTestSteps("pass", "HTTPMethod: " + httpMethod);
		logTestSteps("pass", "Endpoint: " + endPoint);
		logTestSteps("pass", "Expeced Status Code: " + expectedStatusCode);

		try {
			RequestSpecification reqSpec = RestAssured.given().contentType(ContentType.JSON).request()
					.urlEncodingEnabled(false);
			if (endPoint.contains(prop.getProperty("ContainTeacher"))) {
				reqSpec.header(prop.getProperty("TeacherAuthorization"), prop.getProperty("TeacherAuthorizationValue"));
				logTestSteps("pass", "Auth Type: " + (prop.getProperty("TeacherAuthorization")));
				logTestSteps("pass", "Auth Value " + prop.getProperty("TeacherAuthorizationValue"));
			} else if (endPoint.contains(prop.getProperty("ContainStudent"))) {
				reqSpec.header(prop.getProperty("GeneralAuthorization"), prop.getProperty("StudentAuthorizationValue"));
				logTestSteps("pass", "Auth Type: " + prop.getProperty("GeneralAuthorization"));
				logTestSteps("pass", "Auth Value " + prop.getProperty("StudentAuthorizationValue"));
			} else if (endPoint.contains(prop.getProperty("ContainParent"))) {
				reqSpec.header(prop.getProperty("GeneralAuthorization"), prop.getProperty("ParentAuthorizationValue"));
				logTestSteps("pass", "Auth Type: " + prop.getProperty("GeneralAuthorization"));
				logTestSteps("pass", "Auth Value " + prop.getProperty("ParentAuthorizationValue"));
			}
			
			reqSpec.body(body);
			logTestSteps("pass", "Body: " + body);

			switch (httpMethod.toLowerCase()) {
			case "post":
				response = reqSpec.post(endPoint).then().extract().response();
				break;
			case "get":
				response = reqSpec.get(endPoint).then().extract().response();
				break;
			case "delete":
				response = reqSpec.delete(endPoint).then().extract().response();
				break;
			case "put":
				response = reqSpec.put(endPoint).then().extract().response();
				break;
			default:
				response = reqSpec.post(endPoint).then().extract().response();
				break;
			}

			if (stringExactEquals(expectedStatusCode, Integer.toString(response.getStatusCode()))) {
				logTestSteps("Pass", "Response Status code " + expectedStatusCode);
			} else {
				logTestSteps("fail", "Expected status code " + expectedStatusCode + " Actial status code "
						+ response.getStatusCode());
			}

		} catch (InstantiationError e) {
			logTestSteps("Fail", "Exception :" + e);
		} catch (IllegalStateException e) {
			logTestSteps("Fail", "Exception :" + e);
		}
		return response;
	}

	public boolean stringExactEquals(String expected, String actual) {

		boolean returnValue = false;
		try {
			if (expected.equals(actual)) {
				logTestSteps("Pass", "String contains" + actual);
				returnValue = true;
			} else {
				logTestSteps("fail", "Actual string " + actual + " Expected string " + expected);
			}

		} catch (InstantiationError e) {
			logTestSteps("Fail", "Exception :" + e);
		}
		return returnValue;
	}

	public boolean stringContainsEquals(String expected, String actual) {
		boolean returnValue = false;
		try {
			if (expected.contains(actual)) {
				logTestSteps("Pass", "String contains" + actual);
				returnValue = true;
			} else {
				logTestSteps("fail", "Actual string " + actual + " Expected string " + expected);
			}

		} catch (Exception e) {
			logTestSteps("Fail", "Exception :" + e);
		}
		return returnValue;
	}

	public ResultSet connectToDB(String sql) {
		ResultSet rs = null;
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection con = DriverManager.getConnection(prop.getProperty("DBUrl"), prop.getProperty("DBUserName"),
					prop.getProperty("DBPassword"));

			Statement statement = con.createStatement();

			rs = statement.executeQuery(sql);

			logTestSteps("Pass", "Data " + rs);

		} catch (ClassNotFoundException e) {
			logTestSteps("Fail", "Exception :" + e);
			e.printStackTrace();
		} catch (SQLException e) {
			logTestSteps("Fail", "Exception :" + e);
			e.printStackTrace();
		}
		return rs;

	}

	public void createHeader(HashMap<String, String> map) {
		// TODO Auto-generated method stub
		try {
			// RequestSpecification reqSpec = Resr
			RequestSpecification reqSpec = RestAssured.given();
			reqSpec.headers(map);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@SuppressWarnings("unchecked")
	public JSONObject createBodyFromJsonFile(HashMap<String, Object> map, String fileLocation) {
		JSONObject jsonObject = new JSONObject();
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader(prop.getProperty(fileLocation)));
			jsonObject = (JSONObject) obj;
			Set<String> jsonKeys = jsonObject.keySet();

			for (String itemsKey : jsonKeys) {
				String jsonValue = jsonObject.get(itemsKey).toString();
				if (jsonValue.equalsIgnoreCase(map.get("MissingField").toString())) {
					jsonObject.remove(itemsKey);
					continue;
				}
				String dataTypeChange = "age,phone_number";
				if (dataTypeChange.contains(itemsKey)) {
					try {
						jsonObject.put(itemsKey, Long.parseLong(map.get(jsonValue).toString()));
					} catch (Exception e) {
						jsonObject.put(itemsKey, map.get(jsonValue).toString());
					}
				} else {
					jsonObject.put(itemsKey, map.get(jsonValue).toString());
					
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonObject;
	}

	public void validateDBRecord(String sql, String tableColumn, JSONObject data) {

		try {
			ResultSet rs = connectToDB(sql);

			String column = prop.getProperty(tableColumn);
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AssertionError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public String createBody(HashMap<String, Object> map) {

		HashMap<String, Object> data = map;
		JSONObject jsonObj = new JSONObject(data);
		for (Entry<String, Object> mapdata : data.entrySet()) {
			jsonObj.put(mapdata.getKey(), mapdata.getValue());
		}
		return jsonObj.toString();

	}
	
	public void logResponse(Response rep) {
		logTestSteps("pass", "Send Customer Responce: Status => " + rep.jsonPath().getString("Status"));
		logTestSteps("pass", "Send Customer Responce: Description => " + rep.jsonPath().getString("description"));
		logTestSteps("pass", "Send Customer Responce: Get Status Code => " + rep.getStatusCode());
		logTestSteps("pass", "Send Customer Responce: Get Status Line => " + rep.getStatusLine());
	}

	public void loadObjects() {

		try {
			FileInputStream f = new FileInputStream("./properties/object.properties");
			FileInputStream sql = new FileInputStream("./properties/sqlQuery.properties");
			prop = new Properties();
			try {
				prop.load(f);
				prop.load(sql);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
		} catch (FileSystemNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void unloadObjects() {
		prop = null;
	}

	HashMap<String, String> inputSetData = new HashMap<String, String>();

	// @Test
	// HashMap<String, String> map
	public void setData() {
		inputSetData.put("ExecuteTest", "Gokul");
		// String[][] excelData = null;
		String sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		// File file = new File("./src/test/java/SampleReport.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet("result");
		int latRowCount = sheet.getLastRowNum();
		String[] columnName = { "TestCaseName", "TestCaseDescription", "ExecuteTest", "Result" };
		// Create Header if header not exist
		// if(latRowCount >1) {

		int rownum = 0;
		XSSFRow row = sheet.createRow(rownum);
		// for (String cName : columnName) {
		for (int i = 0; i < columnName.length; i++) {
			// Cell cellData = (Cell) row.cellIterator();
			Cell cellData = row.createCell(i);
			cellData.setCellValue(columnName[i]);
		}
		// }
		for (Entry<String, String> inputDataItem : inputSetData.entrySet()) {

			XSSFRow lastRow = sheet.createRow(++latRowCount);
			int columnIndex = 0;
			for (int i = 0; i < columnName.length; i++) {
				if (columnName[i].equalsIgnoreCase(inputDataItem.getKey())) {
					columnIndex = i;
					break;
				}
			}
			Cell cellDataInsert = lastRow.createCell(columnIndex);
			cellDataInsert.setCellValue(inputDataItem.getValue());
		}

		try {

			FileOutputStream outputStream = new FileOutputStream("./src/test/java/SampleReport" + sdf + ".xlsx");
			wb.write(outputStream);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return;
	}
}
