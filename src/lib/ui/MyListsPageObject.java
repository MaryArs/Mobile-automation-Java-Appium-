package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import lib.Platform;

abstract public class MyListsPageObject extends MainPageObject {

    protected static String
            folderByNameTPL,
            articleTitleByTPL,
            articleSubTitleByTPL;


    public MyListsPageObject(AppiumDriver driver){
        super(driver);
    }
    private static String getFolderXPathByName(String nameOfFolder){
        return folderByNameTPL.replace("{FolderName}",nameOfFolder);
    }

    private static String getSavedArticleXpathBySubtittle(String article_subtittle)
    {
        if (Platform.getInstance().isAndroid()) {
            return articleSubTitleByTPL.replace("{SUBTITTLE}", article_subtittle.toLowerCase());
        } else {
            return articleSubTitleByTPL.replace("{SUBTITTLE}", article_subtittle);
        }

    }

    private static String getArticleXPathByName(String articleTitle){
        return articleTitleByTPL.replace("{ArticleTitleName}",articleTitle);
    }

    public void openFolderByName(String nameOfFolder){
        String folderNameXPath = getFolderXPathByName(nameOfFolder);
        //Click on the folder where i have saved my article in My list.
        this.waitForElementAndClick(
                folderNameXPath,
                "Cannot find folder by name! " + nameOfFolder,
                5);
    }

    public void waitForarticleToAppearByTitle(String articleTitle){
        String articleTitleXPAth = getArticleXPathByName(articleTitle);

        this.waitForElementPresent(
                articleTitleXPAth,
                "Cannot find saved article by title " + articleTitle,
                15);
    }

    public void waitForArticleToAppearBySubtitle(String article_subtittle)
    {
        String article_xpath = getSavedArticleXpathBySubtittle(article_subtittle);
        this.waitForElementPresent(
                article_xpath,
                "Cannot find saved article by subtittle " + article_subtittle,
                15
        );
    }

    public void waitForarticleToDisappearByTitle(String articleTitle){
        String articleTitleXPAth = getArticleXPathByName(articleTitle);
        //Check that the article was deleted.
        this.waitForElementNotPresent(
                articleTitleXPAth,
                "Saved article is still present. " + articleTitle,
                15);
    }

    public void waitForArticleToDisappearBySubtittle(String articleSubtittle)
    {
        String articleXpath = getSavedArticleXpathBySubtittle(articleSubtittle);
        this.waitForElementNotPresent(
                articleXpath,
                "Saved article still present with subtittle " + articleSubtittle,
                15
        );
    }

    public void swipeArticleToDelete(String articleTitle){
        //Search the article and swipe to delete
        this.waitForarticleToAppearByTitle(articleTitle);
        String articleTitleXPAth = getArticleXPathByName(articleTitle);
        //Swipe article from the right to the left and delete the article.
        this.swipeElementToLeft(
                articleTitleXPAth,
                "Cannot find saved article");
        if(Platform.getInstance().isIOS()){
            this.clickElementToTheRightUpperCorner(articleTitleXPAth, "Cannot find saved article");
        }
        this.waitForarticleToDisappearByTitle(articleTitle);

    }

    public void swipeByArticleToDeleteWithSubtittle(String article_subtittle)
    {
        this.waitForArticleToAppearBySubtitle(article_subtittle);
        String article_xpath = getSavedArticleXpathBySubtittle(article_subtittle);
        this.swipeElementToLeft(
                article_xpath,
                "Cannot find saved article " + article_subtittle);
        if (Platform.getInstance().isIOS()) {
            this.clickElementToTheRightUpperCorner(article_xpath, "Cannot find and click article delete button");
        }
        this.waitForarticleToDisappearByTitle(article_subtittle);
    }

    public void openArticleByName(String articleTitle){
        String articleTitleXPAth = getArticleXPathByName(articleTitle);
        this.waitForElementAndClick(
               // By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='" + article2 + "']"),
                articleTitleXPAth,
                "Cannot find article with this title.",
                15);
    }




}
