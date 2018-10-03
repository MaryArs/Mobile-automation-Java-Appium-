package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {

    private static final String nameOfFolder = "Learning programming";
    private static final String
            login = "vinkotov",
            password = "qazwsx";
    @Test
    public void testsaveFirstArticleToMyList() {

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("bject-oriented programming language");
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleElement();
        String articleTitle = articlePageObject.getArticleTitle();
        if(Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToMyList(nameOfFolder);
        }else {
            articlePageObject.addArticlesToMySaved();
        }
        if (Platform.getInstance().isMW()) {
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLogInData(login, password);
            Auth.submitForm();

            // wait for redirect back to the title
            articlePageObject.waitForTitleElement();

            // check we back to the same page
            assertEquals(
                    "We back not to the same page after login.",
                    articleTitle,
                    articlePageObject.getArticleTitle()
            );

            articlePageObject.addArticlesToMySaved();
        }
        articlePageObject.closeArticle();

        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        navigationUI.openNavigation();
        navigationUI.clickMyLists();
        MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);
        if(Platform.getInstance().isAndroid()){
            myListsPageObject.openFolderByName(nameOfFolder);
        }
        myListsPageObject.swipeArticleToDelete(articleTitle);
    }

    @Test
    public void testsaveTwoArticlesToMyList() {

        String articleFirstSubstring = "Java (programming language)";
        String articleSecondSubstring = "Python (programming language)";

        //Add first article in the folder.
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForSubtittleElement(articleFirstSubstring);
        String articleFirstSubtittle = articlePageObject.getArticleSubtittle(articleFirstSubstring);
        if(Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToMyList(nameOfFolder);
        }else {
            articlePageObject.addArticlesToMySaved();
        }
        articlePageObject.closeArticle();

        //Add second article in the same folder
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Python");
        searchPageObject.clickByArticleWithSubstring("Python (programming language)");
        articlePageObject.waitForSubtittleElement(articleSecondSubstring);
        String articleSecondSubtitle = articlePageObject.getArticleSubtittle(articleSecondSubstring);
        if(Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToExistingFolder(nameOfFolder);
        }else {
            articlePageObject.addArticlesToMySaved();
        }
        articlePageObject.closeArticle();

        //Click on the navigation button to My list and delete one article.
        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        navigationUI.clickMyLists();
        MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);
        if(Platform.getInstance().isAndroid()) {
            myListsPageObject.openFolderByName(nameOfFolder);
        }
        myListsPageObject.swipeArticleToDelete(articleFirstSubtittle);

        //Check that the article was deleted.
        myListsPageObject.waitForArticleToDisappearBySubtittle(articleFirstSubtittle);
        //Check that the second article is in the folder.
        myListsPageObject.waitForArticleToAppearBySubtitle(articleSecondSubtitle);

        //Check that the title is the same.
        myListsPageObject.openArticleByName(articleSecondSubtitle);
        articlePageObject.waitForSubtittleElement(articleSecondSubtitle);
        String articleActualSubtitle = articlePageObject.getArticleSubtittle(articleSecondSubtitle);
        Assert.assertEquals(
                "We see unexpected subtitle.",
                articleSecondSubtitle,
                articleActualSubtitle);
    }
}
