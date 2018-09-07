package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Assert;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {

    @Test
    public void testsaveFirstArticleToMyList() {

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.waitForTitleElement();
        String articleTitle = articlePageObject.getArticleTitle();
        String nameOfFolder = "Learning programming";
        articlePageObject.addArticleToMyList(nameOfFolder);
        articlePageObject.closeArticle();
        NavigationUI navigationUI = new NavigationUI(driver);
        navigationUI.clickMyLists();
        MyListsPageObject myListsPageObject = new MyListsPageObject(driver);
        myListsPageObject.openFolderByName(nameOfFolder);
        myListsPageObject.swipeArticleToDelete(articleTitle);
    }

    @Test
    public void testsaveTwoArticlesToMyList() {

        String article1 = "Java (programming language)";
        String article2 = "Python (programming language)";

        //Add first article in the folder.
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.waitForTitleElement();
        String articleTitle = articlePageObject.getArticleTitle();
        String nameOfFolder = "Learning programming";
        articlePageObject.addArticleToMyList(nameOfFolder);
        articlePageObject.closeArticle();

        //Add second article in the same folder
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Python");
        searchPageObject.clickByArticleWithSubstring("Python (programming language)");
        articlePageObject.waitForTitleElement();
        String articleTitle2 = articlePageObject.getArticleTitle();
        articlePageObject.addArticleToExistingFolder(nameOfFolder);
        articlePageObject.closeArticle();

        //Click on the navigation button to My list and delete one article.
        NavigationUI navigationUI = new NavigationUI(driver);
        navigationUI.clickMyLists();
        MyListsPageObject myListsPageObject = new MyListsPageObject(driver);
        myListsPageObject.openFolderByName(nameOfFolder);
        myListsPageObject.swipeArticleToDelete(articleTitle);

        //Check that the article was deleted.
        myListsPageObject.waitForarticleToDisappearByTitle(articleTitle);
        //Check that the second article is in the folder.
        myListsPageObject.waitForarticleToAppearByTitle(articleTitle2);

        //Check that the title is the same.
        myListsPageObject.openArticleByName(articleTitle2);
        articlePageObject.waitForTitleElement();
        String article_title = articlePageObject.getArticleTitle();
        Assert.assertEquals(
                "We see unexpected title.",
                "Python (programming language)",
                article_title);
    }
}
