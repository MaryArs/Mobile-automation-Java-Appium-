package lib.ui.mobileWeb;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {

    static {
        title = "css:#content h1";
        footerElement = "css:footer";
        optionsAddToMyListButton = "css:#page-actions li#ca-watch.mw-ui-icon-mf-watch button";
        //closeMyListButton = "id: Back";
        folderByNameTPL = "xpath://*[@name='{FolderName}']";
        subtitleBySubstringTPL= "id:{SUBSTRING}";
    }

    public MWArticlePageObject(RemoteWebDriver driver){
        super(driver);
    }

}
