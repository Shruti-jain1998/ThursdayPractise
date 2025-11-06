package data;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

 public   WebDriver driver;

    @BeforeMethod
    public void setup() {
        ConfigReader.loadConfig();

        String browser = ConfigReader.getProperty("browser");
        String environment = ConfigReader.getProperty("environment");

        String url = null;
        if (environment.equalsIgnoreCase("prod")) {
            url = ConfigReader.getProperty("urlProd");
        } else if (environment.equalsIgnoreCase("staging")) {
            url = ConfigReader.getProperty("urlStaging");
        }

        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(
            Long.parseLong(ConfigReader.getProperty("timeout"))
        ));
        driver.get(url);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
