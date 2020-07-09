package utils;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

import static org.junit.Assert.*;

public class Support {

    public static void waitUntilElementVisible(WebDriver dr, By el){
        WebDriverWait wait = new WebDriverWait(dr, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(el));
    }

    public static void validateText(String expected, String actual){
        System.out.println("EXPECTED: "+ expected + " | ACTUAL: "+ actual);
        assertEquals(expected,actual);
    }

    public static String getResponse(String url) throws IOException {
        return Jsoup.connect(url).ignoreContentType(true).execute().body();
    }

    public static void main(String[] args) throws IOException, ParseException {
        try {
            writeFile(args[0],getResponse(args[1]));
            Logger.getLogger("Success").info("DATA.JSON CREATED!!..reading from "+args[1]);
        } catch (ParseException | IOException e) {
            Logger.getLogger("Failed").info("FAILED importing data from Services..test data will be read locally");
            e.printStackTrace();
        }
    }

    public static void writeFile(String file, String str) throws ParseException, IOException {
        JSONParser parser = new JSONParser();
        FileWriter wr = new FileWriter(new File(file));
        JSONArray arr = (JSONArray) parser.parse(str);
        wr.write(arr.toJSONString()); //write as json file
        wr.close();
    }
}
