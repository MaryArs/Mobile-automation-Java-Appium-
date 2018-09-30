package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class iOSArticlePageObject extends ArticlePageObject {
    static {
        title = "id:Java (programming language)";
        footerElement = "id: View article in browser";
        optionsAddToMyListButton = "id: Save for later";
        closeMyListButton = "id: Back";
        folderByNameTPL = "xpath://*[@name='{FolderName}']";
        subtitleBySubstringTPL= "id:{SUBSTRING}";
    }

    public iOSArticlePageObject(AppiumDriver driver){
        super(driver);
    }
}
