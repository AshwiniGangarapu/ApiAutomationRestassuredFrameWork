package utils;

// Importing HashMap class
import java.util.HashMap;

public class ParameterHandler {
    // Create an empty hash map by declaring object
    // of string and string type
   static  HashMap<String, String> globalUsageParameterMap = new HashMap<>();// static means no need to create object to access in another class.
    public static void setGlobalVariable(String key ,String value)
    {
        // Adding elements to the Map
        // using standard put() method
        globalUsageParameterMap.put(key, value);
    }
    public static String getGlobalVariable(String key)
    {
        return globalUsageParameterMap.get(key);
    }
}

    /* Print size and content of the Map
        System.out.println("Size of globalUsageParameterMap is:- "
                + globalUsageParameterMap.size());

    // Printing elements in object of Map
        System.out.println(globalUsageParameterMap);*/


  /*  public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public int userId;*/ //this is a pojo class where in we set variables that can be used globally.Instead a better approach is using hash map as above.


