package webservices;

import com.relevantcodes.extentreports.ExtentTest;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import utils.ParameterHandler;
import wrappers.GenericWrappers;

import java.util.HashMap;

public class VolunteerItemService extends GenericWrappers {
    public VolunteerItemService(ExtentTest test ){
        this.test=test;
    }

    public void VolunteerItemHappyPath(HashMap<String, Object> map) {
        String volunteerItemId = null;//get the user id for the posted record.

        String endPointPostVolunteerItem = prop.getProperty("APIGatewayURL").concat(prop.getProperty("postVolunteerItemEndPoint"));
        String getVolunteerListEndPoint = prop.getProperty("APIGatewayURL").concat(prop.getProperty("volunteerItemList"));

        JSONObject data = createBodyFromJsonFile(map, "ReadJSONVolunteerItem");

        System.out.println(map.get("ItemName").toString());

        data.put("ItemName", map.get("ItemName"));

        String reqbody = data.toString();

        System.out.println(reqbody);

        try {

            Response rep_Post = sendCustomRequest(map.get("HTTPMethod").toString(), endPointPostVolunteerItem, reqbody, map.get("ExpectedStatusCode").toString());
            logResponse(rep_Post);
            System.out.println(rep_Post.getStatusCode());
            System.out.println(rep_Post.getStatusLine());
            if (rep_Post.getStatusCode() == 200) {

                System.out.println(rep_Post.getStatusCode());
                System.out.println(rep_Post.getStatusLine());

                //Calling Get to verify the record is added in the user list.
                Response rep_Get = sendCustomRequest("GET", getVolunteerListEndPoint, "", "200");
                System.out.println(rep_Get.getStatusCode());
                System.out.println(rep_Get.getStatusLine());

                //convert JSON to string
                JsonPath j = new JsonPath(rep_Get.asString());

                //get values of JSON array after getting array size
                int s = j.getInt("Data.size()");
                for (int i = 0; i < s; i++) {
                    String ItemName_res = j.getString("Data[" + i + "].ItemName");

                    System.out.println(ItemName_res);

                    if (ItemName_res.equalsIgnoreCase(data.get("ItemName").toString())) {

                        logTestSteps("Pass", data.get("ItemName").toString() + " is added to volunteer list");

                        Integer a = 0;

                        a = j.getInt("Data[" + i + "].VolunteerItemId");

                        volunteerItemId = a.toString();//as the globalUsageParameterMap takes string key values.

                        System.out.println(volunteerItemId);

                        ParameterHandler.setGlobalVariable("volunteerItemId", volunteerItemId);

                        logTestSteps("Pass", " user id for " + data.get("ItemName").toString() + "is " + volunteerItemId);

                        break;
                    }

                }


            }
        } catch (Exception e) {
            logTestSteps("Fail", "Exception :" + e);
        }
    }

    public void PostVolunteerItemMissingFieldValidations(HashMap<String, Object> map) {

        String endPointPostVolunteerItem = prop.getProperty("APIGatewayURL").concat(prop.getProperty("postVolunteerItemEndPoint"));

        JSONObject data = createBodyFromJsonFile(map, "ReadJSONVolunteerItem");

        System.out.println(map.get("ItemName").toString());

        data.put("ItemName", map.get("ItemName"));

        String reqbody = data.toString();

        System.out.println(reqbody);

        switch (map.get("MissingField").toString()) {
            case "ItemName":
                data.remove("ItemName");
                break;
            case "ItemDescription":
                data.remove("ItemDescription");
                break;
            case "ItemIsActive":
                data.remove("ItemIsActive");
                break;

        }
        String body = data.toString();

        System.out.println(body);

        try {
            Response rep = sendCustomRequest(map.get("HTTPMethod").toString(),endPointPostVolunteerItem, body, map.get("ExpectedStatusCode").toString());
            logResponse(rep);
            System.out.println(rep.getStatusCode());
            System.out.println(rep.getStatusLine());
            String responseMessage = rep.getBody().jsonPath().getString("Message");

            if ( map.get("MissingField").toString().equalsIgnoreCase("ItemName")&&
                    rep.getStatusCode() == 400 &&
                    responseMessage != null) {

                logTestSteps("Pass",responseMessage);

            }else if(map.get("MissingField").toString().equalsIgnoreCase("ItemDescription") ||
                    map.get("MissingField").toString().equalsIgnoreCase("ItemIsActive")) {

                if (rep.getStatusCode() == 200) {
                    logTestSteps("Pass", map.get("MissingField").toString() + " is not a mandatory field");
                } else {
                    logTestSteps("Fail", map.get("MissingField").toString() + " should not be a mandatory field");
                }
            }else{

                logTestSteps("Fail", map.get("MissingField").toString() + " is not a mandatory field");

            }


        } catch (Exception e) {
            logTestSteps("Fail", "Exception :" + e);
        }

    }

    public void PostVolunteerItemEmptyFieldValidations(HashMap<String, Object> map){
        String endPointPostVolunteerItem = prop.getProperty("APIGatewayURL").concat(prop.getProperty("postVolunteerItemEndPoint"));

        JSONObject data = createBodyFromJsonFile(map, "ReadJSONVolunteerItem");

        System.out.println(map.get("ItemName").toString());

        data.put("ItemName", map.get("ItemName"));

        String reqbody = data.toString();

        System.out.println(reqbody);

        switch (map.get("EmptyField").toString()) {
            case "ItemName":
                data.put("ItemName", "");
                break;
            case "ItemDescription":
                data.put("ItemDescription", "");
                break;


        }
        String body = data.toString();

        System.out.println(body);

        try {
            Response rep = sendCustomRequest(map.get("HTTPMethod").toString(),endPointPostVolunteerItem, body, map.get("ExpectedStatusCode").toString());
            logResponse(rep);
            System.out.println(rep.getStatusCode());
            System.out.println(rep.getStatusLine());
            String responseMessage = rep.getBody().jsonPath().getString("Message");

            if ( map.get("EmptyField").toString().equalsIgnoreCase("ItemName")&&
                    rep.getStatusCode() == 400 &&
                    responseMessage != null) {

                logTestSteps("Pass", responseMessage);

            }else if(map.get("EmptyField").toString().equalsIgnoreCase("ItemDescription")){

                if (rep.getStatusCode() == 200) {
                    logTestSteps("Pass", map.get("EmptyField").toString() + " takes null value");
                } else {
                    logTestSteps("Fail", map.get("EmptyField").toString() + " is not accepting null as value");
                }
            }else{

                logTestSteps("Fail", map.get("EmptyField").toString() + " taking null value");

            }


        } catch (Exception e) {
            logTestSteps("Fail", "Exception :" + e);
        }

    }

    public void PostVolunteerItemDuplicateValidations(HashMap<String, Object> map) {

        String endPointPostVolunteerItem = prop.getProperty("APIGatewayURL").concat(prop.getProperty("postVolunteerItemEndPoint"));
        JSONObject data = createBodyFromJsonFile(map, "ReadJSONVolunteerItem");

        if (map.get("DuplicateData").toString().equalsIgnoreCase("ItemName")) {

            String reqbody = data.toString();
            System.out.println(reqbody);
            Response rep_Post = sendCustomRequest("POST", endPointPostVolunteerItem, reqbody, map.get("ExpectedStatusCode").toString());
            logResponse(rep_Post);
            String responseMessage = rep_Post.getBody().jsonPath().getString("Message");
            if (rep_Post.getStatusCode() == 400) {
                logTestSteps("Pass", "expected response message is displayed: " + responseMessage);
            } else {
                logTestSteps("Pass", "expected response message is displayed: " + responseMessage);
            }
        }


    }

   /* public void PostVolunteerItemInvalidFieldValidations(HashMap<String, Object> map){

        String endPointPostVolunteerItem = prop.getProperty("APIGatewayURL").concat(prop.getProperty("postVolunteerItemEndPoint"));
        JSONObject data = createBodyFromJsonFile(map, "ReadJSONVolunteerItem");

        String body = data.toString();

        System.out.println(body);

        try {


            Response rep = sendCustomRequest(map.get("HTTPMethod").toString(), endPointPostVolunteerItem, body, map.get("ExpectedStatusCode").toString());
            logResponse(rep);

            if (rep.getStatusCode() == 400) {
                System.out.println(rep.getStatusCode());
                System.out.println(rep.getStatusLine());
                logTestSteps("Pass", "expected status code displayed "+rep.getStatusCode());
            }else if(rep.getStatusCode() == 200){
                System.out.println(rep.getStatusCode());
                System.out.println(rep.getStatusLine());
                logTestSteps("Fail", "invalid validation not implemented "+rep.getStatusCode());
            }
            String error = rep.getBody().jsonPath().getString("errors");// for first name,last name and email error is displayed in response

            String responseMessage = rep.getBody().jsonPath().getString("Message");// for phone number and group id message is displayed in response

            if (error != null) {
                System.out.println("$$$$$" + error);
                logTestSteps("Pass", error);
            } else if (responseMessage != null) {
                System.out.println("$$$$$" + responseMessage);
                logTestSteps("Pass", responseMessage);
            }
        } catch (Exception e) {
            logTestSteps("Fail", "Exception :" + e);
            e.printStackTrace();
            // The printStackTrace() method of Java Throwble class is used to print the Throwable along with other details like
            // classname and line number where the exception occurred.
        }



    }*/


}
