import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

public class SaveTwoArticles {
    private AppiumDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
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

    //1. Save two articles in the same folder
    //
    //2. Delete one article.
    //
    //3. Check that another article is exist.
    //
    //4. Open article and check the title.
    @Test
    public void saveTwoArticlesToMyList() {

        String article1 = "Java (programming language)";
        String article2 = "Python (programming language)";

        //Add first article in the folder.
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find Searc Wikipedia input",
                5);

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannot find search input",
                5);

        //Find and click on the "Object-oriented programming language" article.
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find Wikipedia input",
                5);

        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                15);

        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find button to open article options",
                30);

        //Click on the button Add to reading list
        waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find option to add article to reading list",
                5);

        //Click on the button Got it.
        waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find 'Got it' tip overlay",
                5);

        //Clear the title field.
        waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Cannot find input to set name of articles folder",
                5);

        //Enter title for articles folder.
        String name_of_folder = "Learning programming";
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Cannot put text into articles folder input",
                5);

        //Click on the button OK.
        waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Cannot press OK button",
                5);

        //Click on the X button in the left corner.
        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close article, cannot find X link.",
                5);

        //Add second article in the same folder
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find Searc Wikipedia input",
                5);

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Python",
                "Cannot find search input",
                5);

        //Find and click on the "Python (programming language)" article.
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='" + article2 + "']"),
                "Cannot find Wikipedia input",
                5);

        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                15);

        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find button to open article options",
                30);

        //Click on the button Add to reading list
        waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find option to add article to reading list",
                5);

        //Click on the name_of_folder folder in the list.
        waitForElementAndClick(
                By.xpath("//*[@text='" + name_of_folder + "']"),
                "Cannot find folder " + name_of_folder + "",
                5);

        //Click on the X button in the left corner.
        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close article, cannot find X link.",
                5);

        //Click on the navigation button to My list.
        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find navigation button to My list.",
                5);

        //Click on the folder name_of_folder.
        waitForElementAndClick(
                By.xpath("//*[@text='" + name_of_folder + "']"),
                "Cannot find created folder.",
                5);

        //Swipe article from the right to the left and delete the article.
        swipeElementToLeft(
                By.xpath("//*[@text='" + article1 + "']"),
                "Cannot find saved article");

        //Check that the article was deleted.
        waitForElementNotPresent(
                By.xpath("//*[@text='" + article1 + "']"),
                "Cannot delete saved article.",
                5);

        //Check that the second article is in the folder.
        waitForElementPresent(
                By.xpath("//*[@text='" + article2 + "']"),
                "Cannot delete saved article.",
                5);

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='" + article2 + "']"),
                "Cannot find article with this title.",
                15);

        //Check that the title is the same.
        WebElement title_element = waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title.",
                15);

        String article_title = title_element.getAttribute("text");
        Assert.assertEquals(
                "We see unexpected title.",
                article2,
                article_title);
    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    private WebElement waitForElementAndClear(By by, String error_massage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_massage, timeoutInSeconds);
        element.clear();
        return element;
    }

    protected void swipeElementToLeft(By by, String error_message) {
        WebElement element = waitForElementPresent(
                by,
                error_message,
                10);
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(right_x, middle_y)
                .waitAction(300)
                .moveTo(left_x, middle_y)
                .release()
                .perform();
    }
}
