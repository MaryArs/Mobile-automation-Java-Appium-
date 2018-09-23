package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class WelcomePageObject extends MainPageObject {

    private static final String
    STEP_LEARN_MORE_LINK = "id:Learn more about Wikipedia",
    STEP_NEW_WAYS_TO_EXPLORE = "id:New ways to explore",
    STEP_ADD_OR_EDIT_PREFFERED_LANGUAGES = "id:Add or edit preferred languages",
    STEP_LEARN_MORE_ABOUT_DATA_COLLECTED = "id:Learn more about data collected",
    STEP_NEXT = "id:Next",
    STEP_GET_STARTED = "id:Get started",
    SKIP = "id:Skip";

    public WelcomePageObject(AppiumDriver driver) {
        super(driver);
    }

    public void waitForMoreLink(){
        this.waitForElementPresent(STEP_LEARN_MORE_LINK,
                "Cannot find 'Learn more about Wikipedia' link!",
                10);
    }

    public void waitForNewWayToExploreText(){
        this.waitForElementPresent(STEP_NEW_WAYS_TO_EXPLORE,
                "Cannot find 'Learn more about Wikipedia' link!",
                10);
    }

    public void waitForAddOrEditPrefferedLangText(){
        this.waitForElementPresent(STEP_ADD_OR_EDIT_PREFFERED_LANGUAGES,
                "Cannot find 'Add or edit preffered languages' link!",
                10);
    }

    public void waitForLearnMoreAboutDataCollectedText(){
        this.waitForElementPresent(STEP_LEARN_MORE_ABOUT_DATA_COLLECTED,
                "Cannot find 'Learn more about data collected' link!",
                10);
    }

    public void clickNextButton(){
        this.waitForElementAndClick(STEP_NEXT,
                "Cannot find and click 'Next' link!",
                10);
    }

    public void clickGetStartedButton(){
        this.waitForElementAndClick(STEP_GET_STARTED,
                "Cannot find and click 'Get started' link!",
                10);
    }

    public void clickSkip(){
        this.waitForElementAndClick(
                SKIP,
                "Cannot find and click skip button.",
                5);
    }
}
