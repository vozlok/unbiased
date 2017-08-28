package com.selenium.pages;

import com.codeborne.selenide.WebDriverRunner;
import com.selenium.TestBase;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class BaseTestModePage extends TestBase {
    public BaseTestModePage() {
    }

    @Step("Open TestMode Page")
    public BaseTestModePage openTestModePage(){
        open(baseUrl + "/testing-session/start");
        return this;
    }

    @Step("Check TestMode Message on Page")
    public BaseTestModePage checkMessage(){
        $("h1").shouldHave(text("Testing session enabled"));
        return this;
    }
}
