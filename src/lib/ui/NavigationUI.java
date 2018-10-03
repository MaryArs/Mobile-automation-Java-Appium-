package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class NavigationUI extends MainPageObject {

    protected static String
            myListsLink,
            openNavigation;
            //= "xpath://android.widget.FrameLayout[@content-desc='My lists']";
    public NavigationUI(RemoteWebDriver driver){

        super(driver);
    }

    public void openNavigation()
    {
        if (Platform.getInstance().isMW()) {
            this.waitForElementAndClick(openNavigation, "Cannot find and click open navigation button.", 5);
        } else {
            System.out.println("Method openNavigation() do nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }
    public void clickMyLists(){
        //Click on the navigation button to My list.
        if (Platform.getInstance().isMW()) {
            this.tryClickElementWithFewAttempts(
                    myListsLink,
                    "Cannot find navigation button to My list",
                    5
            );
        } else {
            this.waitForElementAndClick(
                    myListsLink,
                    "Cannot find navigation button to My list",
                    5
            );
        }
    }
}
