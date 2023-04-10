package wrappers;

import java.sql.ResultSet;
import java.util.HashMap;

import io.restassured.response.Response;

public interface Wrappers {

	public Response sendCustomRequest(String httpMethod, String endPoint, String body, String expectedStatusCode);

	public boolean stringExactEquals(String expected, String actual);

	public boolean stringContainsEquals(String expected, String actual);

	public void createHeader(HashMap<String, String> map);

	public ResultSet connectToDB(String sql);

	public void loadObjects();

	public void unloadObjects();
}
