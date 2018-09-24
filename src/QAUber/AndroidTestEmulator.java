package QAUber;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class AndroidTestEmulator {

    public static void main(String[] args) throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("deviceName" , "Android");
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("udid", "emulator-5554");
        desiredCapabilities.setCapability("appPackage", "com.android.calculator2");
        desiredCapabilities.setCapability("appActivity", ".Calculator");

        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        //create driver object
        WebDriver driver = new RemoteWebDriver(url, desiredCapabilities);
        //click on digit 5
        driver.findElement(By.id("com.android.calculator2:id/digit_5")).click();

        //click on multiplies
        driver.findElement(By.id("com.android.calculator2:id/op_mul")).click();

        //click on digit 2
        driver.findElement(By.id("com.android.calculator2:id/digit_5")).click();

        //click on '='
        driver.findElement(By.id("com.android.calculator2:id/eq"));

        //result
        String result = driver.findElement(By.id("com.android.calculator2:id/result")).getText();

        System.out.println("Result: " + result);


    }
}
