package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

abstract public class SearchPageObject extends MainPageObject {

    protected static String
            searchInitElement,
            searchInput,
            searchResultBySubstringTPL,
            searchCancelButton,
            searchResultElement,
            emptyResultLabel ,
            articleInContainer ,
            searchField;
    public SearchPageObject(AppiumDriver driver){
        super(driver);
    }

    /* Templates method */
    private static String getResultSearchElement(String subString){
        return searchResultBySubstringTPL.replace("{SUBSTRING}", subString);
    }
    /* Templates method */

    public void initSearchInput(){
        this.waitForElementAndClick(searchInitElement, "Cannot find  and click on search element.",5);
        this.waitForElementPresent(searchInitElement,
                "Cannot find  search input after clicking search element.",5);
    }

    public void typeSearchLine(String searchLine){
        this.waitForElementAndSendKeys(searchInput,searchLine,"Cannot find and type into search input",5);
    }

    public void waitForSearchResult(String subString){
        String searchResultXPath = getResultSearchElement(subString);
        this.waitForElementPresent(searchResultXPath,"Cannot find search result with substring." + subString,5);
    }

    public  void waitForCancelButtonToAppear(){

        this.waitForElementPresent(searchCancelButton,"Cannot find search cancel button.",5);
    }

    public  void waitForCancelButtonToDisappear(){

        this.waitForElementNotPresent(searchCancelButton,"Search cancel button is still present.",5);
    }

    public void clickCancelSearch(){

        this.waitForElementAndClick(searchCancelButton,"Cannot click on the cancel search button.",5);
    }

    public void clickByArticleWithSubstring(String subString){
        String searchResultXPath = getResultSearchElement(subString);
        this.waitForElementAndClick(searchResultXPath,"Cannot find and click search result with substring." + subString,10);
    }

    public int getAmountOfFoundArticles(){

        this.waitForElementPresent(
                searchResultElement,
                "Cannot find anything by request ",
                30);
        return this.getAmountOfElements(searchResultElement);
    }

    public void waitForEmptyResultsLabel(){
        this.waitForElementPresent(emptyResultLabel, "Cannot find empty result label!",15);
    }

    public void assertThereIsNoResultOfSearch(){

        this.assertElementNotPresent(searchResultElement, "We supposed not to find any results");
    }

    public void waitForSomeFoundArticles(){

        for (int i = 0; i <= 2; i++) {
            this.waitForElementPresent(
                    articleInContainer,
                    "Cannot find  several articles",
                    5);
        }
    }

    public String getTextInSearchField(){
        WebElement search_text = this.waitForElementPresent(
                searchField,
                "Cannot find text 'Search…'",
                5);
        return search_text.getAttribute("text");
    }
}

