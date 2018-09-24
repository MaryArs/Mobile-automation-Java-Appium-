package QAUber;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class LoginFitsInBrowserStack {

    public static final String USERNAME = "marina355";
    public static final String AUTOMATE_KEY = "aPFGPEzSpXv3mavX6jzJ";
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

    public static void main(String[] args) throws Exception {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browserName", "android");
        caps.setCapability("device", "Google Nexus 5");
        caps.setCapability("realMobile", "true");
        caps.setCapability("os_version", "4.4");
        caps.setCapability("browserName", "Chrome");

        WebDriver driver = new RemoteWebDriver(new URL(URL), caps);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get("http://fits.qauber.com");

        //Enter email
        WebElement email = driver.findElement(By.id("exampleInputEmail1"));
        email.sendKeys("mary.arsitova@gmail.com");

        //Enter password
        WebElement password = driver.findElement(By.id("exampleInputPassword1"));
        password.sendKeys("123456");
        Thread.sleep(3000);

        //Click on the 'Login button'
        WebElement loginButton = driver.findElement(By.xpath("//button[contains(.,'Login')]"));
        loginButton.click();

        driver.quit();
    }
}
