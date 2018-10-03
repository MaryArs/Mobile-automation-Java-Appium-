package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSSearchPageObject extends SearchPageObject {

    static {
        searchInitElement = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
        searchInput = "xpath://XCUIElementTypeSearchField[@value='Search Wikipedia']";
        searchResultBySubstringTPL = "xpath://XCUIElementTypeLink[contains(@name,'{SUBSTRING}')]";
        searchCancelButton = "xpath:Close";
        searchResultElement = "xpath://XCUIElementTypeLink";
        emptyResultLabel = "xpath://XCUIElementTypeStaticText[@name='No results found']";
        //articleInContainer = "xpath://android.widget.LinearLayout[@index= 'i']";
        //searchField = "id:org.wikipedia:id/search_src_text";
        articleInContainer = "xpath://XCUIElementTypeCell";
        searchField = "id:org.wikipedia:id/search_src_text";
    }

    public iOSSearchPageObject(RemoteWebDriver driver){
        super(driver);
    }
}
