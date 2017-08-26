package com.selenium;

import com.codeborne.selenide.WebDriverRunner;
import com.selenium.pages.BaseTestModePage;
import com.selenium.utils.CustomExternalResource;
import com.selenium.utils.CustomWatcher;
import org.junit.ClassRule;
import org.junit.Rule;
import ru.yandex.qatools.allure.annotations.Step;

public class TestBase {

    @Rule
    public CustomWatcher customWatcher = new CustomWatcher();

    @ClassRule
    public static CustomExternalResource customExternalResource = new CustomExternalResource();

    @Step("Clear cookies before run tests")
    public static void clearCookies() {
        WebDriverRunner.getWebDriver().manage().deleteAllCookies();
    }

    @Step("Set test mode")
    public static void setTestMode() {
        new BaseTestModePage().openTestModePage().checkMessage();
    }
}
