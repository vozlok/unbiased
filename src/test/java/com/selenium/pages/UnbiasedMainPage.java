package com.selenium.pages;

import com.codeborne.selenide.Condition;
import com.selenium.TestBase;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class UnbiasedMainPage extends TestBase {

    public UnbiasedMainPage() {
    }

    @Step("Open Main Page: unbiased.co.uk")
    public UnbiasedMainPage openUnbiasedMainPage() {
        open(baseUrl);
        $("div.test_mode_notification").shouldBe(visible);
        return this;
    }

    @Step("Search advisors in Location")
    public FindAdviserPage searchAdvisersInLocation(String typeAdv, String location) {
        $(byXpath("//button/span[text()='Select one']")).click();
        $(byXpath("//a[text()='"+typeAdv+"']")).click();
        $("input[name=search]").setValue(location);
        $(byXpath("//span[@class='ng-binding' and contains(.,'"+location+"')]")).click();
        $(byXpath("//button[@ladda='searchForm.searching']")).click();
        $(byXpath("//button[contains(.,'Contact us')]")).shouldBe(visible);
        $("div[ng-show=\"showProgress()\"]").shouldNotBe(visible);
        return new FindAdviserPage();
    }
}
