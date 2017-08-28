package com.selenium.pages;

import com.selenium.TestBase;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

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
    public EnquiryFormPage clickContactUsOnFirstAdviser() {
        executeJavaScript("window.scrollBy(0,-250)", "");
        $$(byXpath("//div[@class='adviser-card__actions']/button")).first().click();
        return new EnquiryFormPage();
    }

    @Step("Set filter by advice")
    public FindAdviserPage setFilterByAdvice(String filter) {
        $("[name='["+filter.toLowerCase()+"]']+span").click();
        waitLoadPage();
        return this;
    }

    @Step("Set filter by asset value")
    public FindAdviserPage setFilterByAssetValue(String assetValue) {
        $(byXpath("//a[@href='#your-income-asset-value']")).click();
        $(byXpath("//ul[@id='your-income-asset-value']/li/label/span[contains(.,'"+assetValue+"')]")).click();
        waitLoadPage();
        return this;
    }

    @Step("Click Next page")
    public FindAdviserPage clickNextPageAdvisers() {
        $("a[rel=next]").click();
        return this;
    }

    @Step("Wait load page after action")
    public void waitLoadPage(){
        $("div[ng-show=\"showProgress()\"]").shouldBe(visible);
        $("div[ng-show=\"showProgress()\"]").shouldNotBe(visible);
    }

    @Step("research adviser with disabled capcha")
    public FindAdviserPage researchWithDisabledCapcha() {
        String url = url();
        StringBuilder str = new StringBuilder(url);
        int offset = url.substring(0,url.indexOf("?")).length();
        open(String.valueOf(str.insert(offset+1,"disable_captcha=1")));
        $("div #results-list").shouldBe(visible);
        $(byXpath("//button[contains(.,'Contact us')]")).shouldBe(visible);
        $("div[ng-show=\"showProgress()\"]").shouldNotBe(visible);
        $(byXpath("//span[text()='Page']")).shouldBe(visible);
        return this;
    }

    @Step("Set filter by adviser restrictions")
    public FindAdviserPage setFilterByAdviserRestrictions(String adviserRestrictions) {
        $(byXpath("//span[contains(.,'More filters')]")).click();
        $(byXpath("//a[@href='#adviser-restrictions']")).click();
        $(byXpath("//ul[@id='adviser-restrictions']/li/label/span[contains(.,'"+adviserRestrictions+"')]")).click();
        waitLoadPage();
        return this;
    }

    @Step("Click Contact us, by adviser name")
    public EnquiryFormPage clickAdviserContactUs(String name) {
        $(byXpath("//a[text()='"+name+"']/../../../div[@class='adviser-card__actions']/button")).click();
        return new EnquiryFormPage();
    }
}
