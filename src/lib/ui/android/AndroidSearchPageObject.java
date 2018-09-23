package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class AndroidSearchPageObject extends SearchPageObject {

     static {
                 searchInitElement = "xpath://*[contains(@text,'Search Wikipedia')]";
                 searchInput = "xpath://*[contains(@text,'Search…')]";
                 searchResultBySubstringTPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']";
                 searchCancelButton = "xpath:org.wikipedia:id/search_close_btn";
                 searchResultElement = "xpath://*[@resource-id = 'org.wikipedia:id/search_results_list']/*[@resource-id = 'org.wikipedia:id/page_list_item_container']";
                 emptyResultLabel = "xpath://*[@text = 'No results found']";
                 articleInContainer = "xpath://android.widget.LinearLayout[@index= 'i']";
                 searchField = "id:org.wikipedia:id/search_src_text";
     }

    public AndroidSearchPageObject(AppiumDriver driver){
        super(driver);
    }
}
