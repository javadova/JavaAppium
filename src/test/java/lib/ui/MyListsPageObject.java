package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import lib.Platform;

abstract public class MyListsPageObject extends MainPageObject {
    protected static String
        FOLDER_BY_NAME_TPL,
        ARTICLE_BY_TITLE_TPL,
        TITLE_ARTICLE,
        DELETE_ARTICLE;

    private static String getFolderXpathByName(String name_of_folder)
    {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    private static String getSavedArticleXpathByTitle(String article_title)
    {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }

    public MyListsPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public void openFolderByName(String name_of_folder)
    {
        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(
                folder_name_xpath,
                "Cannot find folder by name " + name_of_folder,
                5
        );
    }
    public void waitForArticleAppearByTitle(String article_title)
    {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementPresent(article_xpath, "Cannot find article with title " + article_title, 15);
    }

    public void waitForArticleDisappearByTitle(String article_title)
    {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementNotPresent(article_xpath, "Saved article still present with title " + article_title, 15);
    }

    public void swipeByArticleToDelete(String article_title)
    {
        this.waitForArticleAppearByTitle(article_title);
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.swipeElementToLeft(
                article_xpath,
                "Cannot find saved article"
        );

        if (Platform.getInstance().isIOS()){
            this.clickElementToTheRightUpperCorner(article_xpath, "Cannot find saved article");
        }

        this.waitForArticleDisappearByTitle(article_xpath);
    }

    public void clickArticleByTitle(String substring) {

        String article_title_xpath = getSavedArticleXpathByTitle(substring);
        this.waitForElementAndClick(article_title_xpath, "Cannot tap on a saved article title", 15);
    }

    public String getArticleTitleMyList()
    {
        WebElement title_element = waitForTitleElementMyList();
        return title_element.getAttribute("text");
    }

    public WebElement waitForTitleElementMyList()
    {
        return this.waitForElementPresent(TITLE_ARTICLE, "Cannot find article on page", 15);
    }

    public void checkRightArticleWasDeleted()
    {
        this.waitForElementNotPresent(DELETE_ARTICLE, "Wrong article was deleted",5);
    }
}
