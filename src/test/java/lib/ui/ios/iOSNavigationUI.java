package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;

public class iOSNavigationUI extends NavigationUI {
    static {
        MY_LIST_LINK = "id:Saved";
        CLOSE_BUTTON_POPUP = "id:Close";
    }

    public iOSNavigationUI (AppiumDriver driver)
    {
        super(driver);
    }
}
