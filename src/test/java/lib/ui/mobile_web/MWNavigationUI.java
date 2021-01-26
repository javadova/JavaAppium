package lib.ui.mobile_web;

import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWNavigationUI extends NavigationUI {
        static {
            MY_LIST_LINK = "css:a[data-event-name='menu.unStar']";
            OPEN_NAVIGATION = "css:label[data-event-name='ui.mainmenu']";
        }

        public MWNavigationUI (RemoteWebDriver driver)
        {
            super(driver);
        }
}
