package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSNavigationUI extends NavigationUI {

    static {
       myListsLink = "id:Saved";
    }
    public iOSNavigationUI(RemoteWebDriver driver)
    {
        super(driver);
    }
}
