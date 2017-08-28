package com.selenium.pages;

import com.selenium.TestBase;
import com.selenium.test.core.UserInfo;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class EnquireFormPage extends TestBase {
    public EnquireFormPage() {
    }

    @Step("Fill user info fields")
    public EnquireFormPage fillUserInfo(UserInfo user) {
        $("#RegularEnquiryForm_firstName").setValue(user.firstName);
        $("#RegularEnquiryForm_lastName").setValue(user.lastName);
        $("#RegularEnquiryForm_email").setValue(user.email);
        $("#RegularEnquiryForm_phone").setValue(user.phone);
        $("#RegularEnquiryForm_postcode").setValue(user.postcode);
        return this;
    }

    @Step("Fill why do you want adviser field")
    public EnquireFormPage fillWhyDoYouWantAdviser(String text){
        $("#RegularEnquiryForm_message").setValue(text);
        return this;
    }

    @Step("Set speciality radiobatton")
    public EnquireFormPage fillSpeciality(String speciality) {
        $("#RegularEnquiryForm_specialityClass").click();
        $(byXpath("//input[@value='string:"+speciality+"']")).click();
        return this;
    }

    @Step("Select a specific area of advice")
    public EnquireFormPage fillSpecificAreaOfAdice(String specificArea) {
        $(byXpath("//span[text()='Select a specific area of advice']")).click();
        $(byXpath("//input[@value='string:"+specificArea+"']")).click();
        return this;
    }

    @Step("Select asset value")
    public EnquireFormPage fillAssetValue(String assetValue) {
        $(byXpath("//label/span/span[text()='Select your asset value']")).click();
        $(byXpath("//input[@value='string:"+assetValue+"']")).click();
        return this;
    }

    @Step("Click Send enquiry")
    public EnquireFormPage clickSendEnquiryError() {
        $(byXpath("//span[contains(.,'Send enquiry')]/..")).click();
        return this;
    }

    @Step("Click Send enquiry")
    public EnquireFormPage clickSendEnquiry() {
        $(byXpath("//span[contains(.,'Send enquiry')]/..")).click();
        return this;
    }

    @Step("Check required capcha")
    public EnquireFormPage checkRequiredCapcha() {
        $(byXpath("//p[@field='RegularEnquiryForm[recaptcha]']/ng-transclude/span")).shouldHave(text(" Please solve captcha "));
        return this;
    }

    @Step("Check TY Enquiry")
    public EnquireFormPage checkTYEnquiry() {
        $("p.thank-you__title").shouldHave(text("Thank you!"));
        $("p.thank-you__message").shouldHave(text("Your enquiry was sent successfully.\n" +
                "You can expect a response within 48 hours."));
        return this;
    }
}
