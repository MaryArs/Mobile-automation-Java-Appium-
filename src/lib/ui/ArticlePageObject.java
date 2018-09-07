package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

    private static final String title = "org.wikipedia:id/view_page_title_text",
                                footerElement = "//*[@text = 'View page in browser']",
                                optionsButton = "//android.widget.ImageView[@content-desc='More options']",
                                optionsAddToMyListButton = "//*[@text='Add to reading list']",
                                gotItButton = "org.wikipedia:id/onboarding_button",
                                myListName ="org.wikipedia:id/text_input",
                                myListOkButton = "//*[@text='OK']",
                                closeMyListButton = "//android.widget.ImageButton[@content-desc='Navigate up']",
                                folderByNameTPL = "//*[@text='{FolderName}']";

    public ArticlePageObject(AppiumDriver driver) {

        super(driver);
    }

    public WebElement waitForTitleElement(){

        return this.waitForElementPresent(By.id(title), "Cannot find article title on the page!",15);
    }

    private static String getFolderXPathByName(String nameOfFolder){
        return folderByNameTPL.replace("{FolderName}",nameOfFolder);
    }

    public String getArticleTitle(){
        WebElement titleElement = waitForTitleElement();
        return titleElement.getAttribute("text");
    }

    public void swipeToFooter(){
        this.swipeUpToFindElement(By.xpath(footerElement), "Cannot find the end of this article", 20);
    }

    public void addArticleToMyList(String nameOfFolder) {

        this.waitForElementAndClick(
                By.xpath(optionsButton),
                "Cannot find button to open article options",
                30);

        //Click on the button Add to reading list
        this.waitForElementAndClick(
                By.xpath(optionsAddToMyListButton),
                "Cannot find option to add article to reading list",
                5);

        //Click on the button Got it.
        this.waitForElementAndClick(
                By.id(gotItButton),
                "Cannot find 'Got it' tip overlay",
                5);

        //Clear the title field.
        this.waitForElementAndClear(
                By.id(myListName),
                "Cannot find input to set name of articles folder",
                5);

        //Enter title for articles folder.

        this.waitForElementAndSendKeys(
                By.id(myListName),
                nameOfFolder,
                "Cannot put text into articles folder input",
                5);

        //Click on the button OK.
        this.waitForElementAndClick(
                By.xpath(myListOkButton),
                "Cannot press OK button",
                5);
    }

    public void addArticleToExistingFolder(String nameOfFolder){

        this.waitForElementAndClick(
                By.xpath(optionsButton),
                "Cannot find button to open article options",
                30);

        //Click on the button Add to reading list
        this.waitForElementAndClick(
                By.xpath(optionsAddToMyListButton),
                "Cannot find option to add article to reading list",
                5);
        //Click on the name_of_folder folder in the list.
        String folderNameXPath = getFolderXPathByName(nameOfFolder);
        this.waitForElementAndClick(
                By.xpath(folderNameXPath),
                "Cannot find folder " + nameOfFolder + "",
                5);
    }

    public void closeArticle() {
        //Click on the X button in the left corner.
        this.waitForElementAndClick(
                By.xpath(closeMyListButton),
                "Cannot close article, cannot find X link.",
                5);
    }

    public void assertArticleTitlePresent(){
        this.assertElementNotPresent(By.xpath(title), "Cannot find article's title on the page!");
    }

}