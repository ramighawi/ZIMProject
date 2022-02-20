package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import pageobjects.TablePage;

import java.sql.SQLException;

public class CommonOps extends Base {

    @Parameters({"browser"})
    @BeforeClass
    public void startSession(String value) throws SQLException, ClassNotFoundException, InterruptedException {
        myWebStarter(value);
    }


    @AfterClass
    public void endSession() {
        webDriver.quit();

    }


    @Step("init WEB")
    public void myWebStarter(String browser) throws SQLException, ClassNotFoundException, InterruptedException {
        url = Utilities.getDataXML("Url");

        switch (browser.toLowerCase()) {

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                webDriver = new FirefoxDriver();
                break;

            case "ie":
                WebDriverManager.iedriver().setup();
                webDriver = new InternetExplorerDriver();
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                webDriver = new EdgeDriver();
                break;
            default:
                WebDriverManager.chromedriver().setup();
                webDriver = new ChromeDriver();
                break;
        }

        webDriver.get(url);
        webDriver.manage().window().maximize();
        tablePage = PageFactory.initElements(webDriver, TablePage.class);

    }

    @Attachment(value = "Page Screen-Shot", type = "image/png")
    public static byte[] saveScreenshot() {

            return ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);

    }


   }







