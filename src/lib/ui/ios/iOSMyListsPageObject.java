package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSMyListsPageObject extends MyListsPageObject {

    static{
        articleTitleByTPL = "xpath://XCUIElementTypeLink[contains(@name='{ArticleTitleName}')]";
        articleSubTitleByTPL = "xpath://XCUIElementTypeLink[contains(@name, '{SUBTITTLE}')]";
    }

    public iOSMyListsPageObject(RemoteWebDriver driver){
        super(driver);
    }
}
