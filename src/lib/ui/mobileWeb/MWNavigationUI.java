package lib.ui.mobileWeb;

import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWNavigationUI extends NavigationUI
{
    static {
        myListsLink = "css:a[data-event-name='watchlist']";
        openNavigation = "css:#mw-mf-main-menu-button";
    }

    public MWNavigationUI(RemoteWebDriver driver)
    {
        super(driver);
    }
}
