package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import lib.ui.MyListsPageObject;

public class AndroidMyListsPageObject extends MyListsPageObject {

    static{
        folderByNameTPL = "xpath://*[@text='{FolderName}']";
        articleTitleByTPL = "xpath://*[@text='{ArticleTitleName}']";
        articleSubTitleByTPL = "xpath://*[@text='{SUBTITTLE}']";
    }

    public AndroidMyListsPageObject(AppiumDriver driver){
        super(driver);
    }
}
