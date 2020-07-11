package utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class Reader {
    static String jsonFile = "src/main/resources/data.json";
    static JSONArray arr;

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

