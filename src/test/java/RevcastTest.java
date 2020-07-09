import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class RevcastTest {
    WebDriver dr;
    Revcast revcast;

    @Before
    public void setUp() throws Exception {
        WebDriverManager.chromedriver().setup();
        dr = new ChromeDriver();
        revcast = new Revcast(dr);
        revcast.launchHome();
    }

    @After
    public void tearDown() throws Exception {
        dr.quit();
    }

    @Test
    public void validateProjectTable() {
        revcast.validateProjectTable();
    }
}