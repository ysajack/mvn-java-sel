import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import utils.*;

public class Revcast {

    WebDriver dr;

    public Revcast(WebDriver dr){
        this.dr=dr;
        PageFactory.initElements(dr,this);
    }
    public void launchHome(){
        dr.get("http://revcast-app.appspot.com/");
    }

    public void validateProjectTable() {
        String table = "//*[@id=\"fill-table\"]/tbody/tr";
        Support.waitUntilElementVisible(dr,By.xpath(table));
        int rows = dr.findElements(By.xpath(table)).size();
        for(int i=2;i<=rows;i++){ //Records starting at row 2nd
            String prjNum = dr.findElement(By.xpath(table+"["+ i +"]/td[2]")).getText();
            String prjName = dr.findElement(By.xpath(table+"["+ i +"]/td[3]")).getText();
            String expectedPrjNme = Reader.getProjectName(prjNum);
            Support.validateText(expectedPrjNme,prjName);
        }
    }

}
