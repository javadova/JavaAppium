package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.remote.RemoteWebDriver;
import lib.Platform;

abstract public class NavigationUI extends MainPageObject {
     protected static String
        MY_LIST_LINK,
        CLOSE_BUTTON_POPUP,
        OPEN_NAVIGATION;

    public NavigationUI(RemoteWebDriver driver)
    {
        super(driver);
    }

    @Step("open navigation button")
    public void openNavigation()
    {
        if(Platform.getInstance().isMW()){
            this.waitForElementAndClick(OPEN_NAVIGATION,"Cannot find and click open navigation button.",5);
        } else {
            System.out.println("Method openNavigation() do nothing for platform" + Platform.getInstance().getPlatformVar());
        }
    }

    @Step("click on My List")
    public void clickMyLists()
    {
        if (Platform.getInstance().isMW()) {
            this.tryClickElementWithFewAttempts(
                    MY_LIST_LINK,
                    "Cannot find navigation button to My list",
                    5
            );
        } else {
            this.waitForElementAndClick(
                    MY_LIST_LINK,
                    "Cannot find navigation button to My list",
                    5
            );
        }
    }

    @Step("Click Close Button On Popup")
    public void clickCloseButtonOnPopup()
    {
        this.waitForElementAndClick(
                CLOSE_BUTTON_POPUP,
                "Cannot find close button on alert",
                5
        );
    }
}
