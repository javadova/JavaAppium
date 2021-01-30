package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {

    private static String name_of_folder = "Learning programming";
    private static final String
            login = "Elvirajavadova",
            password = "javadova93";

    @Test
    public void testSaveFirstArticleToMyList() throws InterruptedException {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("bject-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()){
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticleToMySaved();
        }

        if(Platform.getInstance().isMW()){
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Thread.sleep(2000);
            Auth.enterLoginData(login, password);
            Auth.submitForm();

            ArticlePageObject.waitForTitleElement();
            Assert.assertEquals("We are not on the same page after login",
                    article_title,
                    ArticlePageObject.getArticleTitle()
            );

            ArticlePageObject.addArticleToMySaved();
        }
        
        ArticlePageObject.closeArticle();

        if(Platform.getInstance().isIOS()){
            SearchPageObject.clickCancelSearch();
        }

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.openNavigation();
        NavigationUI.clickMyLists();

        if(Platform.getInstance().isIOS()){
            NavigationUI.clickCloseButtonOnPopup();
        }

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()){
            MyListsPageObject.openFolderByName(name_of_folder);
        }

        MyListsPageObject.swipeByArticleToDelete(article_title);
    }

    //Ex5, Ex11
    @Test
    public void testSaveTwoArticleAndDeleteOne() throws InterruptedException {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        String title_article_one = "British journalist";
        String title_article_two = "British archaeologist";
        String name_of_folder = "British famous people";
        String search_line_find_one = "Dilys Powell";
        String search_line_find_two = "Humfry Payone";

        //Ищем в поиске первую статью
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line_find_one);
        //Открываем первую статью
        if(Platform.getInstance().isAndroid()) {
            SearchPageObject.clickByArticleWithSubstring(title_article_one);
        } else if (Platform.getInstance().isIOS()){
            SearchPageObject.clickByArticleWithSubstring(search_line_find_one);
        } else {
            SearchPageObject.clickByArticleWithSubstringByTitle(search_line_find_one);
        }

        //Ожидаем появления первой статьи
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();

        //Добавляем в My List
        if (Platform.getInstance().isAndroid()){
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            Thread.sleep(4000);
            ArticlePageObject.addArticleToMySaved();
        }

        //Авторизуемся на mobile web
        if(Platform.getInstance().isMW()) {
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Thread.sleep(4000);
            Auth.enterLoginData(login, password);
            Auth.submitForm();
        }
        if(Platform.getInstance().isMW()) {
            ArticlePageObject.addArticleToMySaved();
        }


        //закрываем первую статью для ios и android
        if(Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()) {
            ArticlePageObject.closeArticle();
        }

        //В iOS отменяем предыдущий поиск
        if(Platform.getInstance().isIOS()){
            SearchPageObject.clickCancelSearch();
        }

        //Вводим в Поиск вторую статью
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line_find_two);

        //Открываем в Поиске вторую статью
        SearchPageObject.clickByArticleWithSubstring(title_article_two);
        ArticlePageObject.waitForTitleElement();

        //Добавляем вторую статью в My List
        if (Platform.getInstance().isAndroid()){
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticleToMySaved();
        }

        //закрываем вторую статью
        ArticlePageObject.closeArticle();

        //В iOS отменяем предыдущий поиск
        if(Platform.getInstance().isIOS()){
            SearchPageObject.clickCancelSearch();
        }

        //идем в My List
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.openNavigation();
        NavigationUI.clickMyLists();

        //закрываем на ios попап sync
        if(Platform.getInstance().isIOS()){
            NavigationUI.clickCloseButtonOnPopup();
        }

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);

        //на Android переходим в папку
        if (Platform.getInstance().isAndroid()){
            MyListsPageObject.openFolderByName(name_of_folder);
        }

        //удаляем вторую(последнюю) статью
        if(Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()) {
            MyListsPageObject.swipeByArticleToDelete(title_article_two);
        } else{
            MyListsPageObject.swipeByArticleToDelete(search_line_find_two);
        }

        //убеждаемся что осталась первая статья
        if(Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()) {
            MyListsPageObject.waitForArticleAppearByTitle(title_article_one);
        } else {
            MyListsPageObject.waitForArticleAppearByTitle(search_line_find_one);
        }

        //проверяем что удалена верная статья
        if(Platform.getInstance().isAndroid() || Platform.getInstance().isMW()) {
        String title_expected = MyListsPageObject.getArticleTitleMyList();
        MyListsPageObject.clickArticleByTitle(title_article_one);

        String title_result = ArticlePageObject.getArticleTitle();
            Assert.assertEquals(
                "Article title " + title_result + "cannot expected",
                title_expected,
                title_result);
        } else {
            MyListsPageObject.checkRightArticleWasDeleted();
        }
    }
}
