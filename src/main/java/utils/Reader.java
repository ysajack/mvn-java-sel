package utils;

/*Aurguments say:
  The static block is called once when the JVM initializes and loads the class.
  The constructor is called every time the object is created.
  Therefore, either have a static block then have the class load,
  or have constructor and instantiate the object once.
  In example below, using static block*/

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class Reader {
    static String jsonFile = "src/main/resources/data.json";
    static JSONArray arr;

    //Using static block to parse the json file once when the class is initiated and loaded by JVM
    static {
        arr = parse(jsonFile);
    }

    private static JSONArray parse(String path)  {
        JSONParser ps = new JSONParser();
        FileReader rd;
        try {
            rd = new FileReader(path);
            return (JSONArray) ps.parse(rd);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //read Json file
    public static String getProjectName(String prjNum) {
        for(Object obj : arr){
            JSONObject el = (JSONObject) obj;
            if(el.get("projectId").toString().equals(prjNum)) return el.get("projectName").toString();
        }
        return "No project found";
    }
}

