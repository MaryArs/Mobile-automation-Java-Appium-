package lib.ui.mobileWeb;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWSearchPageObject extends SearchPageObject {

    static {
        searchInitElement = "css:button#searchIcon";
        searchInput = "css:form>input[type='search']";
        searchResultBySubstringTPL = "xpath://div[contains(@class, 'wikipedia-description')][contains(text(), '{SUBSTRING}')]";
        searchCancelButton = "css:button.cancel";
        searchResultElement = "css:ul.page-list>li.page-summary";
        emptyResultLabel = "css:p.without-results";
        //articleInContainer = "xpath://android.widget.LinearLayout[@index= 'i']";
        //searchField = "id:org.wikipedia:id/search_src_text";
        articleInContainer = "css:li.page-summary";
        searchField = "css:form>input[type='search']";
    }

    public MWSearchPageObject(RemoteWebDriver driver){
        super(driver);
    }
}
