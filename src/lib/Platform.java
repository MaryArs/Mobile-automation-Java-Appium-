package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Platform {
    private static final String PLATFORM_IOS = "iOS";
    private static final String PLATFORM_ANDROID = "android";
    private static final String PLATFORM_MOBILE_WEB = "mobile_web";
    private static String APPIUM_URL = "http://127.0.0.1:4723/wd/hub";
    private static Platform instance;
    private Platform() {}

    public static Platform getInstance(){
        if(instance==null){
            instance=new Platform();
        }
        return instance;
    }

    public RemoteWebDriver getDriver() throws Exception{
        URL URL = new URL(APPIUM_URL);
        if(this.isAndroid()){
            return new AndroidDriver(URL, getAndroidDesiredCapabilities());
        }else if(this.isIOS()){
            return new IOSDriver(URL, getIOSDesiredCapabilities());
        }
        else if(this.isMW()){
            return new ChromeDriver(this.getMWChromeOptions());
        }else {
            throw new Exception("Cannot detect type of the driver. Platform value: " + this.getPlatformVar());
        }
    }

    public boolean isAndroid(){
        return isPlatform(PLATFORM_ANDROID);
    }

    public boolean isIOS(){
        return isPlatform(PLATFORM_IOS);
    }

    public boolean isMW(){
        return isPlatform(PLATFORM_MOBILE_WEB);
    }

    private DesiredCapabilities getAndroidDesiredCapabilities(){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "6.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/marsitova/Desktop/MobileAutomation/apks/org.wikipedia.apk");
        return capabilities;
    }

    private DesiredCapabilities getIOSDesiredCapabilities(){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "iPhone SE");
        capabilities.setCapability("platformVersion", "11.4");
        capabilities.setCapability("app", "/Users/marsitova/Desktop/MobileAutomation/apks/Wikipedia.app");
        return capabilities;
    }

//Run ChromeDriver

    private ChromeOptions getMWChromeOptions(){
        Map<String, Object> deviceMetrics = new HashMap<String, Object>();
        //width, height of device
        deviceMetrics.put("width",360);
        deviceMetrics.put("height", 640);
        deviceMetrics.put("pixelRatio", 3.0);

        Map<String,Object> mobileEmulation = new HashMap<String, Object>();
        mobileEmulation.put("deviceMetrics", deviceMetrics);
        mobileEmulation.put("userAgent", "Mozilla/5.0 (Linux; Android 4.2.1; en-us; Nexus 5 Build/JOP40D) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166 Mobile Safari/535.19");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("window-size=340,640");
        return chromeOptions;
    }


//Compare our platform with the variable my_platform
    private boolean isPlatform(String my_platform){
        String platform = this.getPlatformVar();
        return my_platform.equals(platform);
    }

//Get environment variable
    public String getPlatformVar(){
        return System.getenv("PLATFORM");
    }
}
