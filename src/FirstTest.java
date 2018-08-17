import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class FirstTest {
    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "6.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/marsitova/Desktop/MobileAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void firstTest() {
        waitForElementByXpathAndClick("//*[contains(@text,'Search Wikipedia')]",
                "Cannot find Searc Wikipedia input",5);
        waitForElementByXPathAndSendKeys("//*[contains(@text,'Search…')]",
                "Java", "Cannot find search input", 5);


        //WebElement elememt_to_init_search = driver.findElementByXPath("//*[contains(@text,'Search Wikipedia')]");
        //elememt_to_init_search.click();
        //WebElement element_to_enter_search_line = waitForElementPresentByXPath("//*[contains(@text,'Search…')]",
         //       "Cannot find search input");
        //element_to_enter_search_line.sendKeys("Java");


        waitForElementPresentByXPath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']",
                "Cannot find Object-oriented programming language topic by Java", 15);

    }

    private WebElement waitForElementPresentByXPath(String xpath, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        By by = By.xpath(xpath);
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement waitForElementPresentByXPath(String xpath, String error_message) {
        return waitForElementPresentByXPath(xpath,
                error_message, 5);
    }

    private WebElement waitForElementByXpathAndClick(String xpath, String error_message, long timeoutInSeconds){
        WebElement element = waitForElementPresentByXPath(xpath, error_message, timeoutInSeconds);
        element.click();
        return  element;
    }

    private WebElement waitForElementByXPathAndSendKeys(String xpath, String value, String error_message, long timeoutInSeconds){
        WebElement element = waitForElementPresentByXPath(xpath, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return  element;
    }

}