package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.ui.MainPageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

@Epic("Tests for Search")
public class SearchTests extends CoreTestCase {
    @Test
    @Features(value = {@Feature(value = "Search")})
    @DisplayName("Search test")
    @Description("Init search and check correct Search result")
    @Step("Starting test testSearch")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testSearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("bject-oriented programming language");
    }

    @Test
    @Features(value = {@Feature(value = "Search")})
    @DisplayName("Cancel Search test")
    @Description("Init search and cancel it")
    @Step("Starting test testCancelSearch")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testCancelSearch(){
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    @Features(value = {@Feature(value = "Search")})
    @DisplayName("Search not empty test")
    @Description("Init search and check Amount Of Not Empty")
    @Step("Starting test testAmountOfNotEmptySearch")
    public void testAmountOfNotEmptySearch(){

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        String search_line = "Linkin park Diskography";
        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = SearchPageObject.getAmmountOfFoundArticle();

        Assert.assertTrue(
                "We found a few results",
                amount_of_search_results >0
        );
    }

    @Test
    @Features(value = {@Feature(value = "Search")})
    @DisplayName("Search empty test")
    @Description("Init search and check Amount Of Empty")
    @Step("Starting test testAmountOfEmptySearch")
    public void testAmountOfEmptySearch(){
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        String search_line = "zchfgeteuo";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }

    // Ex3
    @Test
    @Features(value = {@Feature(value = "Search")})
    @DisplayName("Cancel Search after check search result")
    @Description("Init search, check find a few results, cancel search, check empty search result")
    @Step("Starting test testCancelSearch")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testCheckCancelSearch(){
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        String search_line = "Appium";
        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = SearchPageObject.getAmmountOfFoundArticle();
        Assert.assertTrue(
                "Find results less than 2",
                amount_of_search_results >= 2
        );

        SearchPageObject.clickCancelSearch();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }
}
