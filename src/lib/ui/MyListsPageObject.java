package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListsPageObject extends MainPageObject {

    public static final String folderByNameTPL = "xpath://*[@text='{FolderName}']",
                               articleTitleByTPL = "xpath://*[@text='{ArticleTitleName}']";


    public MyListsPageObject(AppiumDriver driver){
        super(driver);
    }
    private static String getFolderXPathByName(String nameOfFolder){
        return folderByNameTPL.replace("{FolderName}",nameOfFolder);
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

    public void waitForarticleToDisappearByTitle(String articleTitle){
        String articleTitleXPAth = getArticleXPathByName(articleTitle);
        //Check that the article was deleted.
        this.waitForElementNotPresent(
                articleTitleXPAth,
                "Saved article is still present. " + articleTitle,
                15);
    }

    public void swipeArticleToDelete(String articleTitle){
        this.waitForarticleToAppearByTitle(articleTitle);
        String articleTitleXPAth = getArticleXPathByName(articleTitle);
        //Swipe article from the right to the left and delete the article.
        this.swipeElementToLeft(
                articleTitleXPAth,
                "Cannot find saved article");
        this.waitForarticleToDisappearByTitle(articleTitle);

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
