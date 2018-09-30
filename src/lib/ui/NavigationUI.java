package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

abstract public class NavigationUI extends MainPageObject {

    protected static String
            myListsLink;
            //= "xpath://android.widget.FrameLayout[@content-desc='My lists']";
    public NavigationUI(AppiumDriver driver){

        super(driver);
    }

    public void clickMyLists(){
        //Click on the navigation button to My list.
        this.waitForElementAndClick(
                myListsLink,
                "Cannot find navigation button to My list.",
                5);
    }
}
