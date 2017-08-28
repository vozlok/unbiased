package com.selenium.pages;

import com.codeborne.selenide.Condition;
import com.selenium.TestBase;
import com.selenium.test.core.EnquiryInfo;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class MyEnquiriesPage extends TestBase{
    public MyEnquiriesPage() {
    }

    @Step("Check enquiry status in profile")
    public MyEnquiriesPage checkDetailEnquire(EnquiryInfo enquiry) {
        $(byXpath("//div[@class='timeline-item__title' and contains(.,'Enquiry sent to "+enquiry.adviserName+"')]")).shouldBe(visible);
        $("div.timeline-item__date").shouldBe(visible);
        $("div.timeline-item__time").shouldBe(visible);
        return this;
    }
}
