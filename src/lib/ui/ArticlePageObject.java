package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import lib.Platform;

abstract public class ArticlePageObject extends MainPageObject {

    protected static String
            title,
            footerElement,
            optionsButton,
            optionsAddToMyListButton,
            gotItButton,
            myListName,
            myListOkButton,
            closeMyListButton,
            folderByNameTPL,
            subtitleBySubstringTPL;

    public ArticlePageObject(AppiumDriver driver) {

        super(driver);
    }

    public WebElement waitForTitleElement() {

        return this.waitForElementPresent(title, "Cannot find article title on the page!", 15);
    }

    public WebElement waitForSubtittleElement(String substring)
    {
        String articleSubtitleXpath = getArticleSubtitleLocator(substring);
        return this.waitForElementPresent(articleSubtitleXpath, "Cannot find article subtittle on the page!", 15);
    }

    private static String getFolderXPathByName(String nameOfFolder) {
        return folderByNameTPL.replace("{FolderName}", nameOfFolder);
    }

    public String getArticleTitle() {
        WebElement titleElement = waitForTitleElement();
        if (Platform.getInstance().isAndroid()) {
            return titleElement.getAttribute("text");
        } else {
            return titleElement.getAttribute("name");
        }
    }

    public String getArticleSubtitleLocator(String substring) {
        return subtitleBySubstringTPL.replace("{SUBSTRING}", substring);
    }

    public String getArticleSubtittle(String substring)
    {
        WebElement subtitle_element = waitForSubtittleElement(substring);
        if (Platform.getInstance().isAndroid()) {
            return subtitle_element.getAttribute("text");
        } else {
            return subtitle_element.getAttribute("name");
        }
    }

    public void swipeToFooter() {
        if (Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(
                    footerElement,
                    "Cannot find the end of this article",
                    20);
        } else {
            this.swipeUpTillElementAppear(
                    footerElement,
                    "Cannot find the end of this article",
                    40);
        }
    }

    public void addArticleToMyList(String nameOfFolder) {

        this.waitForElementAndClick(
                optionsButton,
                "Cannot find button to open article options",
                30);

        //Click on the button Add to reading list
        this.waitForElementAndClick(
                optionsAddToMyListButton,
                "Cannot find option to add article to reading list",
                5);

        //Click on the button Got it.
        this.waitForElementAndClick(
                gotItButton,
                "Cannot find 'Got it' tip overlay",
                5);

        //Clear the title field.
        this.waitForElementAndClear(
                myListName,
                "Cannot find input to set name of articles folder",
                5);

        //Enter title for articles folder.

        this.waitForElementAndSendKeys(
                myListName,
                nameOfFolder,
                "Cannot put text into articles folder input",
                5);

        //Click on the button OK.
        this.waitForElementAndClick(
                myListOkButton,
                "Cannot press OK button",
                5);
    }

    public void addArticleToExistingFolder(String nameOfFolder) {

        this.waitForElementAndClick(
                optionsButton,
                "Cannot find button to open article options",
                30);

        //Click on the button Add to reading list
        this.waitForElementAndClick(
                optionsAddToMyListButton,
                "Cannot find option to add article to reading list",
                5);
        //Click on the name_of_folder folder in the list.
        String folderNameXPath = getFolderXPathByName(nameOfFolder);
        this.waitForElementAndClick(
                folderNameXPath,
                "Cannot find folder " + nameOfFolder + "",
                5);
    }

    public void closeArticle() {
        //Click on the X button in the left corner.
        this.waitForElementAndClick(
                closeMyListButton,
                "Cannot close article, cannot find X link.",
                5);
    }

    public void assertArticleTitlePresent() {
        this.assertElementNotPresent(title, "Cannot find article's title on the page!");
    }

//Save articles in My List for iOS
    public void addArticlesToMySaved(){
        this.waitForElementAndClick(
                optionsAddToMyListButton = "id: Save for later",
                "Cannot find option to add article to reading list",
                5);

    }

}