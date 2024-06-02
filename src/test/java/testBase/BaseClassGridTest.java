package testBase;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

public class BaseClassGridTest {
    public static WebDriver driver;
//    public String url = "http://localhost/opencart/upload/index.php"; //was deactivated here. URL is imported lower in this class from the config file.
    public Logger logger;
    public Properties properties;


    @BeforeClass(groups = {"sanity", "regression", "master"}) //Base class methods should be also matched with groups, else they won't be executed with other tests of a certain group.
    @Parameters({"os", "browser"})
    public void setup(String os, String br) throws IOException {

        //loading properties file
        FileReader file = new FileReader(".//src/test/resources/config.properties");
        properties = new Properties();
        properties.load(file);

        //loading log4j2 file
        logger = LogManager.getLogger(this.getClass()); //this will load all logging configuration

        if (properties.getProperty("execution_env").equalsIgnoreCase("remote")) {
            DesiredCapabilities capabilities = new DesiredCapabilities();

            //os
            if (os.equalsIgnoreCase("windows")) {
                capabilities.setPlatform(Platform.WIN11);
            } else if (os.equalsIgnoreCase("Mac")) {
                capabilities.setPlatform(Platform.MAC);
            } else {
                System.out.println("no matching os.");
                return;
            }
            //browser
            switch (br.toLowerCase()) {
                case "chrome" : capabilities.setBrowserName("chrome"); break;
                case "edge" : capabilities.setBrowserName("MicrosoftEdge"); break; //just Edge won't work, they say.
                default : System.out.println("no matching browser."); return;
            }

            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
        }
        else if (properties.getProperty("execution_env").equalsIgnoreCase("local")) {
            //launching browser based on condition - locally
            switch (br.toLowerCase()) {
                case "chrome": System.setProperty("webdriver.chrome.silentOutput","true");
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--remote-allow-origins=*","ignore-certificate-errors");
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(chromeOptions);
                    break;
                case "edge": System.setProperty("webdriver.edge.silentOutput", "true");
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS,true);
                    edgeOptions.addArguments("--remote-allow-origins=*");
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver(edgeOptions);
                    break;
                default:
                    System.out.println("no matching browser."); return;
            }
        }

        //launching browsers based on conditions


//        System.setProperty("webdriver.chrome.silentOutput","true");
//        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.addArguments("--remote-allow-origins=*","ignore-certificate-errors");
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver(chromeOptions);
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));

        driver.get(properties.getProperty("appURL")); //reading url from the properties config file
        driver.manage().window().maximize();

    }

    @AfterClass(groups = {"sanity", "regression", "master"})
    public void tearDown() {
        driver.quit();
    }

    public String randomString() {
        String generateRandString = RandomStringUtils.randomAlphabetic(5);
        return generateRandString;
    }
    public String randomNumber() {
        String generateRandNumber = RandomStringUtils.randomNumeric(10);
        return generateRandNumber;
    }
    public String randomAlphaNumeric() {
        String generateRandAlphaNumeric = RandomStringUtils.randomAlphanumeric(8);
        return generateRandAlphaNumeric;
    }

    public String setEmailFromFile() {
       return properties.getProperty("email");
    }

    public String setPasswordFromFile() {
        return properties.getProperty("password");
    }

    public String captureScreen(String tname) {
        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

        String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
        File targetFile = new File(targetFilePath);

        sourceFile.renameTo(targetFile);
        return targetFilePath;
    }
}
