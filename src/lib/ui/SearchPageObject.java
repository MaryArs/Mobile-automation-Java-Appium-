package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SearchPageObject extends MainPageObject {

    private static final String
            searchInitElement = "//*[contains(@text,'Search Wikipedia')]",
            searchInput = "//*[contains(@text,'Search…')]",
            searchResultBySubstringTPL= "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']",
            searchCancelButton = "org.wikipedia:id/search_close_btn",
            searchResultElement = "//*[@resource-id = 'org.wikipedia:id/search_results_list']/*[@resource-id = 'org.wikipedia:id/page_list_item_container']",
            emptyResultLabel = "//*[@text = 'No results found']",
            articleInContainer = "//android.widget.LinearLayout[@index= 'i']" ;
    public SearchPageObject(AppiumDriver driver){
        super(driver);
    }

    /* Templates method */
    private static String getResultSearchElement(String subString){
        return searchResultBySubstringTPL.replace("{SUBSTRING}", subString);
    }
    /* Templates method */

    public void initSearchInput(){
        this.waitForElementAndClick(By.xpath(searchInitElement), "Cannot find  and click on search element.",5);
        this.waitForElementPresent(By.xpath(searchInitElement),
                "Cannot find  search input after clicking search element.",5);
    }

    public void typeSearchLine(String searchLine){
        this.waitForElementAndSendKeys(By.xpath(searchInput),searchLine,"Cannot find and type into search input",5);
    }

    public void waitForSearchResult(String subString){
        String searchResultXPath = getResultSearchElement(subString);
        this.waitForElementPresent(By.xpath(searchResultXPath),"Cannot find search result with substring." + subString,5);
    }

    public  void waitForCancelButtonToAppear(){

        this.waitForElementPresent(By.xpath(searchCancelButton),"Cannot find search cancel button.",5);
    }

    public  void waitForCancelButtonToDisappear(){

        this.waitForElementNotPresent(By.xpath(searchCancelButton),"Search cancel button is still present.",5);
    }

    public void clickCancelSearch(){

        this.waitForElementAndClick(By.xpath(searchCancelButton),"Cannot click on the cancel search button.",5);
    }

    public void clickByArticleWithSubstring(String subString){
        String searchResultXPath = getResultSearchElement(subString);
        this.waitForElementAndClick(By.xpath(searchResultXPath),"Cannot find and click search result with substring." + subString,10);
    }

    public int getAmountOfFoundArticles(){

        this.waitForElementPresent(
                By.xpath(searchResultElement),
                "Cannot find anything by request ",
                30);
        return this.getAmountOfElements(By.xpath(searchResultElement));
    }

    public void waitForEmptyResultsLabel(){
        this.waitForElementPresent(By.xpath(emptyResultLabel), "Cannot find empty result label!",15);
    }

    public void assertThereIsNoResultOfSearch(){

        this.assertElementNotPresent(By.xpath(searchResultElement), "We supposed not to find any results");
    }

    public void waitForSomeFoundArticles(){

        int i;
        for (i = 0; i <= 2; i++) {
            this.waitForElementPresent(
                    By.xpath(articleInContainer),
                    "Cannot find  several articles",
                    5);
        }
    }

    public String getTextInSearchField(){
        WebElement search_text = this.waitForElementPresent(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find text 'Search…'",
                5);
        return search_text.getAttribute("text");
    }
}

