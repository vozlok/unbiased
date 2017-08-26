package com.selenium.test.search;

import com.selenium.TestBase;
import com.selenium.pages.UnbiasedMainPage;
import org.junit.Test;
import ru.yandex.qatools.allure.annotations.Title;

@Title("Search Advisers Tests")
public class SearchAdvisersTest extends TestBase {

    @Title("Successful search advisers")
    @Test
    public void SuccessfullSearchTest(){
        String typeAdv = "Financial adviser";
        String location = "North West London";

        new UnbiasedMainPage().openUnbiasedMainPage().
            searchAdvisersInLocation(typeAdv, location).
            checkSettingsSearchInFindPage(typeAdv, location);
    }
}
