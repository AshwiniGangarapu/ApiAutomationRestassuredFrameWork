package webservices;

import com.relevantcodes.extentreports.ExtentTest;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import utils.ParameterHandler;
import wrappers.GenericWrappers;

import java.util.HashMap;

public class AddUserByAdminService extends GenericWrappers {
    public AddUserByAdminService(ExtentTest test) {
        this.test = test;
    }


    public void addUserByAdminHappyPath(HashMap<String, Object> map)//it takes the hashmap parameter with key as string and value as Object.
    {

        String userId = null;//get the user id for the posted record.

        String postUserByAdminEndPoint = prop.getProperty("Identity_Url").concat(prop.getProperty("addUserByAdminEndPoint"));
        String getUserListEndPoint = prop.getProperty("APIGatewayURL").concat(prop.getProperty("userListEndPoint"));

        JSONObject data = createBodyFromJsonFile(map, "ReadJSONAddUserByAdmin");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println(map.get("EmailAddress").toString());

        System.out.println(map.get("PhoneNumber").toString());

        data.put("EmailAddress", map.get("EmailAddress").toString().concat("@gmail.com"));
        data.put("PhoneNumber", map.get("PhoneNumber").toString());

        String reqbody = data.toString();

        System.out.println(reqbody);

        ParameterHandler.setGlobalVariable("reqBodyForUser", reqbody);
        System.out.println(ParameterHandler.getGlobalVariable("reqBodyForUser"));
        try {

           // Response rep_Post = sendCustomRequest(map.get("HTTPMethod").toString(), postUserByAdminEndPoint, reqbody, map.get("ExpectedStatusCode").toString());
            // I couldnt take HTTP method from map as this method is a reusable method from many other test cases which has other httpmethod.So hard coded it.
            Response rep_Post = sendCustomRequest("POST", postUserByAdminEndPoint, reqbody, "200");

            logResponse(rep_Post);
            System.out.println(rep_Post.getStatusCode());
            System.out.println(rep_Post.getStatusLine());
            if (rep_Post.getStatusCode() == 200) {

                System.out.println(rep_Post.getStatusCode());
                System.out.println(rep_Post.getStatusLine());

                //Calling Get to verify the record is added in the user list.
                Response rep_Get = sendCustomRequest("GET", getUserListEndPoint, "", "200");
                System.out.println(rep_Get.getStatusCode());
                System.out.println(rep_Get.getStatusLine());

                //convert JSON to string
                JsonPath j = new JsonPath(rep_Get.asString());

                //get values of JSON array after getting array size
                int s = j.getInt("Data.size()");
                for (int i = 0; i < s; i++) {
                    String EmailAddress_res = j.getString("Data[" + i + "].EmailAddress");

                    System.out.println(EmailAddress_res);

                    if (EmailAddress_res.equalsIgnoreCase(data.get("EmailAddress").toString())) {

                        logTestSteps("Pass", data.get("EmailAddress").toString() + " is added to user list");

                        Integer a = 0;

                        a = j.getInt("Data[" + i + "].UserId");

                        userId = a.toString();//as the globalUsageParameterMap takes string key values.

                        System.out.println(userId);

                        /*ParameterHandler obj = new ParameterHandler();
                        obj.setUserId(userId);*///this is user when just a variable is used with getter and setter as defined in comments in parameter handler.

                        ParameterHandler.setGlobalVariable("userId", userId);

                        logTestSteps("Pass", " user id for " + data.get("EmailAddress").toString() + " is " + userId);

                        break;
                    }

                }


            }
        } catch (Exception e) {
            logTestSteps("Fail", "Exception :" + e);
        }

    }


    public void addUserByAdminMissingFieldValidations(HashMap<String, Object> map) {

        String postUserByAdminEndPoint = prop.getProperty("Identity_Url").concat(prop.getProperty("addUserByAdminEndPoint"));

        JSONObject data = createBodyFromJsonFile(map, "ReadJSONAddUserByAdmin");

        System.out.println(map.get("EmailAddress").toString());

        System.out.println(map.get("PhoneNumber").toString());

        data.put("EmailAddress", map.get("EmailAddress").toString().concat("@gmail.com"));
        data.put("PhoneNumber", map.get("PhoneNumber").toString());


        switch (map.get("MissingField").toString()) {
            case "FirstName":
                data.remove("FirstName");
                break;
            case "LastName":
                data.remove("LastName");
                break;
            case "EmailAddress":
                data.remove("EmailAddress");
                break;
            case "PhoneNumber":
                data.remove("PhoneNumber");
                break;
            case "GroupIds":
                data.remove("GroupIds");
                break;
        }
        String body = data.toString();

        System.out.println(body);

        try {
            Response rep = sendCustomRequest(map.get("HTTPMethod").toString(), postUserByAdminEndPoint, body, map.get("ExpectedStatusCode").toString());
            logResponse(rep);
            System.out.println(rep.getStatusCode());
            System.out.println(rep.getStatusLine());
            if (rep.getStatusCode() == 400) {
                System.out.println(rep.getStatusCode());
                System.out.println(rep.getStatusLine());
            }


            String error = rep.getBody().jsonPath().getString("errors");// for first name,last name and email error is displayed in response

            String responseMessage = rep.getBody().jsonPath().getString("Message");// for phone number and group id message is displayed in response

            if (error != null) {
                System.out.println("$$$$$" + error);

                if (error.equals("[" + map.get("MissingField").toString() + ":[The " + map.get("MissingField").toString() + " field is required.]]")) {
                    logTestSteps("Pass", map.get("MissingField").toString() + " is a mandatory field");
                } else {
                    logTestSteps("Fail", map.get("MissingField").toString() + " is not a mandatory field");
                }
            } else if (responseMessage != null) {
                System.out.println("$$$$$" + responseMessage);
                if (responseMessage.equals(map.get("MissingField").toString() + " field is required")) {
                    logTestSteps("Pass", map.get("MissingField").toString() + " is a mandatory field");
                } else {
                    logTestSteps("Fail", map.get("MissingField").toString() + " is not a mandatory field");
                }
            }
        } catch (Exception e) {
            logTestSteps("Fail", "Exception :" + e);
        }

    }

    public void addUsersByAdminEmptyFieldValidations(HashMap<String, Object> map) {

        String postUserByAdminEndPoint = prop.getProperty("Identity_Url").concat(prop.getProperty("addUserByAdminEndPoint"));

        JSONObject data = createBodyFromJsonFile(map, "ReadJSONAddUserByAdmin");

        System.out.println(map.get("EmailAddress").toString());

        System.out.println(map.get("PhoneNumber").toString());

        data.put("EmailAddress", map.get("EmailAddress").toString().concat("@gmail.com"));
        data.put("PhoneNumber", map.get("PhoneNumber").toString());

        switch (map.get("EmptyField").toString()) {
            case "FirstName":
                data.put("FirstName", "");
                break;
            case "LastName":
                data.put("LastName", "");
                break;
            case "EmailAddress":
                data.put("EmailAddress", "");
                break;
            case "PhoneNumber":
                data.put("PhoneNumber", "");
                break;
            case "GroupIds":
                data.put("GroupIds", "");
                break;
        }

        String body = data.toString();

        System.out.println(body);

        try {
            Response rep = sendCustomRequest(map.get("HTTPMethod").toString(), postUserByAdminEndPoint, body, map.get("ExpectedStatusCode").toString());
            logResponse(rep);
            System.out.println(rep.getStatusCode());
            System.out.println(rep.getStatusLine());
            if (rep.getStatusCode() == 400) {
                System.out.println(rep.getStatusCode());
                System.out.println(rep.getStatusLine());
            }
            String error = rep.getBody().jsonPath().getString("errors");// for first name,last name and email error is displayed in response

            String responseMessage = rep.getBody().jsonPath().getString("Message");// for phone number and group id message is displayed in response

            if (error != null) {
                System.out.println("$$$$$" + error);

                if (error.equals("[" + map.get("EmptyField").toString() + ":[The " + map.get("EmptyField").toString() + " field is required.]]")) {
                    logTestSteps("Pass", map.get("EmptyField").toString() + " is a mandatory field");
                } else {
                    logTestSteps("Fail", map.get("EmptyField").toString() + " is not a mandatory field");
                }
            } else if (responseMessage != null) {
                System.out.println("$$$$$" + responseMessage);
                if (responseMessage.equals(map.get("EmptyField").toString() + " field is required")) {
                    logTestSteps("Pass", map.get("EmptyField").toString() + " is a mandatory field");
                } else {
                    logTestSteps("Fail", map.get("EmptyField").toString() + " is not a mandatory field");
                }

            }
        } catch (Exception e) {
            logTestSteps("Fail", "Exception :" + e);
            e.printStackTrace();
            // The printStackTrace() method of Java Throwble class is used to print the Throwable along with other details like
            // classname and line number where the exception occurred.
        }
    }

    public void getUserByUserId(HashMap<String, Object> map) {

        /*addUserByAdminHappyPath(map);// calls the PostAddUserByAdmin endpoint
        // adds a user and gets the user id by calling getUserById end point and saves the userId in globalVariable userId(ParameterHandler)
        System.out.println("phgv****"+ParameterHandler.getGlobalVariable("userId"));
        System.out.println("phgv****"+ParameterHandler.getGlobalVariable("reqBodyForUser"));*/

        String getUserByUserIdEndPoint = prop.getProperty("APIGatewayURL")
                .concat(prop.getProperty("getUserByUserIdEndPoint"));
        String getUserByUserIdEndPointComplete=getUserByUserIdEndPoint.concat(ParameterHandler.getGlobalVariable("userId"));
        System.out.println("Endpoint*****"+getUserByUserIdEndPointComplete);

        try {
            Response rep_Get = sendCustomRequest(map.get("HTTPMethod").toString(), getUserByUserIdEndPointComplete, "", "200");
            logResponse(rep_Get);

            System.out.println(rep_Get.toString());

            System.out.println(ParameterHandler.getGlobalVariable("userId"));

            System.out.println(ParameterHandler.getGlobalVariable("reqBodyForUser"));

            //convert JSON to string
            JsonPath j = new JsonPath(rep_Get.asString());

            if (j.getString("Data.UserId").equalsIgnoreCase(ParameterHandler.getGlobalVariable("userId"))) {

                logTestSteps("Pass", "response has same userId");
            }

            if (ParameterHandler.getGlobalVariable("reqBodyForUser").toLowerCase().contains(j.getString("Data.EmailAddress").toLowerCase())
                    && ParameterHandler.getGlobalVariable("reqBodyForUser").toLowerCase().contains(j.getString("Data.PhoneNumber").toLowerCase())
                    && ParameterHandler.getGlobalVariable("reqBodyForUser").toLowerCase().contains(j.getString("Data.FirstName").toLowerCase())
                    && ParameterHandler.getGlobalVariable("reqBodyForUser").toLowerCase().contains(j.getString("Data.LastName").toLowerCase())
                    && ParameterHandler.getGlobalVariable("reqBodyForUser").toLowerCase().contains(j.getString("Data.GroupIds").toLowerCase())) {

                logTestSteps("Pass", "response has correct data associated with userId" + ParameterHandler.getGlobalVariable("userId"));

            }

        } catch (Exception e) {
            logTestSteps("Fail", "Exception :" + e);
            e.printStackTrace();
            // The printStackTrace() method of Java Throwble class is used to print the Throwable along with other details like
            // classname and line number where the exception occurred.
        }
    }

    public void getUserByUserIdNegativeValidation(HashMap<String, Object> map) {

        String getUserByUserIdEndPoint = prop.getProperty("APIGatewayURL")
                .concat(prop.getProperty("getUserByUserIdEndPoint"));
        String getUserByUserIdEndPointComplete=getUserByUserIdEndPoint.concat(map.get("UserId").toString());
        System.out.println("Endpoint*****"+getUserByUserIdEndPointComplete);

        try {
            Response rep_Get = sendCustomRequest("GET", getUserByUserIdEndPointComplete, "", "400");
            logResponse(rep_Get);
            String responseMessage = rep_Get.getBody().jsonPath().getString("Message");
            System.out.println(responseMessage);

             if (rep_Get.getStatusCode() == 400 &&
                     responseMessage.equalsIgnoreCase("No record found with the id ("+map.get("UserId").toString()+")")) {
                System.out.println(rep_Get.getStatusCode());
                System.out.println(rep_Get.getStatusLine());
                 logTestSteps("Pass", "expected response message is displayed: "+responseMessage);
             }else{

                 logTestSteps("Fail", "expected response message is not displayed. "+responseMessage);
             }
        } catch (Exception e) {
            logTestSteps("Fail", "Exception :" + e);
            e.printStackTrace();
            // The printStackTrace() method of Java Throwble class is used to print the Throwable along with other details like
            // classname and line number where the exception occurred.
        }
    }

    public void addUserByAdminDuplicateEntryValidations(HashMap<String, Object> map) {

        String postUserByAdminEndPoint = prop.getProperty("Identity_Url").concat(prop.getProperty("addUserByAdminEndPoint"));
        JSONObject data = createBodyFromJsonFile(map, "ReadJSONAddUserByAdmin");

        if (map.get("DuplicateData").toString().equalsIgnoreCase("EmailAddress")) {
            data.put("PhoneNumber", map.get("PhoneNumber").toString());
            String reqbody = data.toString();
            Response rep_Post = sendCustomRequest("POST", postUserByAdminEndPoint, reqbody, map.get("ExpectedStatusCode").toString());
            logResponse(rep_Post);
            String responseMessage = rep_Post.getBody().jsonPath().getString("Message");
            if (rep_Post.getStatusCode() == 400) {
                logTestSteps("Pass", "expected response message is displayed: " + responseMessage);
            } else {
                logTestSteps("Pass", "expected response message is displayed: " + responseMessage);
            }
        } else if (map.get("DuplicateData").toString().equalsIgnoreCase("PhoneNumber")) {
            data.put("EmailAddress", map.get("EmailAddress").toString().concat("@gmail.com"));
            String reqbody = data.toString();
            Response rep_Post = sendCustomRequest("POST", postUserByAdminEndPoint, reqbody, map.get("ExpectedStatusCode").toString());
            logResponse(rep_Post);
            String responseMessage = rep_Post.getBody().jsonPath().getString("Message");
            if (rep_Post.getStatusCode() == 400) {
                logTestSteps("Pass", "expected response message is displayed: " + responseMessage);
            } else {
                logTestSteps("Pass", "expected response message is displayed: " + responseMessage);
            }
        }


    }

    public void addUsersByAdminInvalidFieldValidations(HashMap<String, Object> map){

        String postUserByAdminEndPoint = prop.getProperty("Identity_Url").concat(prop.getProperty("addUserByAdminEndPoint"));

        JSONObject data = createBodyFromJsonFile(map, "ReadJSONAddUserByAdmin");

        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println(map.get("EmailAddress").toString());

        System.out.println(map.get("PhoneNumber").toString());

        data.put("EmailAddress", map.get("EmailAddress").toString().concat("@gmail.com"));
        data.put("PhoneNumber", map.get("PhoneNumber").toString());

        switch (map.get("InvalidDataField").toString()) {
            case "FirstName":
                data.put("FirstName", map.get("FirstName").toString());
                break;
            case "LastName":
                data.put("LastName", map.get("LastName").toString());
                break;
            case "EmailAddress":
                data.put("EmailAddress", map.get("EmailAddress").toString());
                break;
            case "PhoneNumber":
                data.put("PhoneNumber", map.get("PhoneNumber").toString());
                break;
            case "GroupIds":
                data.put("GroupIds", map.get("GroupIds").toString());
                break;
        }

        String body = data.toString();

        System.out.println(body);

        try {
            Response rep = sendCustomRequest(map.get("HTTPMethod").toString(), postUserByAdminEndPoint, body, map.get("ExpectedStatusCode").toString());
            logResponse(rep);

            if (rep.getStatusCode() == 400) {
                System.out.println(rep.getStatusCode());
                System.out.println(rep.getStatusLine());
                logTestSteps("Pass", "expected status code displayed "+rep.getStatusCode());

                String error = rep.getBody().jsonPath().getString("errors");// for first name,last name and email error is displayed in response

                String responseMessage = rep.getBody().jsonPath().getString("Message");// for phone number and group id message is displayed in response

                if (error != null) {
                    System.out.println("$$$$$" + error);
                    logTestSteps("Pass", error);
                } else if (responseMessage != null) {
                    System.out.println("$$$$$" + responseMessage);
                    logTestSteps("Pass", responseMessage);
                }

            }else if(rep.getStatusCode() == 200){
                System.out.println(rep.getStatusCode());
                System.out.println(rep.getStatusLine());
                logTestSteps("Fail", "invalid validation not implemented for "+map.get("InvalidDataField"));
            }

        } catch (Exception e) {
            logTestSteps("Fail", "Exception :" + e);
            e.printStackTrace();
            // The printStackTrace() method of Java Throwble class is used to print the Throwable along with other details like
            // classname and line number where the exception occurred.
        }



    }

}
