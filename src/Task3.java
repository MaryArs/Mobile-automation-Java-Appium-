import lib.CoreTestCase;
import lib.ui.MainPageObject;
import lib.ui.SearchPageObject;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Task3 extends CoreTestCase {

private MainPageObject mainPageObject;

protected void setUp() throws Exception {
    super.setUp();
    mainPageObject = new MainPageObject(driver);
}
    @Test
    public void testSearch() {

        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSomeFoundArticles();
        searchPageObject.clickCancelSearch();
        String searchFieldText= searchPageObject.getTextInSearchField();
        assertEquals(
                "We do not see text 'Search…'",
                "Search…",
                searchFieldText);
    }
}
