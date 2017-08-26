package com.selenium.pages;

import com.selenium.TestBase;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class FindAdviserPage extends TestBase {
    public FindAdviserPage() {
    }

    @Step("Check Parameters of search advisors")
    public FindAdviserPage checkSettingsSearchInFindPage(String typeAdv, String location) {
        $("span[ng-bind='searchForm.getServiceTitle()']").shouldHave(text(typeAdv));
        $("input[name=search]").shouldHave(value(location));
        return this;
    }

    @Step("Click Contact us, on the first item in list")
    public EnquireFormPage clickContactUsOnFirstAdviser() {
        executeJavaScript("window.scrollBy(0,-250)", "");
        $$(byXpath("//div[@class='adviser-card__actions']/button")).first().click();
        return new EnquireFormPage();
    }

    @Step("Set filter by advice")
    public FindAdviserPage setFilterByAdvice(String filter) {
        $("[name='[financial planning]']+span").click();
        $("div[ng-show=\"showProgress()\"]").shouldBe(visible);
        $("div[ng-show=\"showProgress()\"]").shouldNotBe(visible);
        return this;
    }

    @Step("Set filter by asset value")
    public FindAdviserPage setFilterByAssetValue(String assetValue) {
        $(byXpath("//a[@href='#your-income-asset-value']")).click();
        $(byXpath("//ul[@id='your-income-asset-value']/li/label/span[contains(.,'"+assetValue+"')]")).click();
        $("div[ng-show=\"showProgress()\"]").shouldBe(visible);
        $("div[ng-show=\"showProgress()\"]").shouldNotBe(visible);
        return this;
    }
}
