package com.selenium.test.enquiry;

import com.selenium.TestBase;
import com.selenium.pages.UnbiasedMainPage;
import com.selenium.test.core.AdviserInfo;
import com.selenium.test.core.EnquiryInfo;
import com.selenium.test.core.UserInfo;
import org.junit.Test;
import ru.yandex.qatools.allure.annotations.Title;

@Title("Send Enquiry Tests")
public class EnquiryTest extends TestBase {

    private String whyDoYouWantAdviser = "This is a test email from the unbiased.co.uk web team.\n" +
            "Please ignore this email if you receive it incorrectly.\n" +
            "Apologies for any inconvenience caused.\n" +
            "Best regards,\n" +
            "unbiased.co.uk";


    @Title("Test: Fill form send enquiry")
    @Test
    public void FillFormSendEnquiryTest(){
        String typeAdv = "Financial adviser";
        String location = "North West London";
        UserInfo user = new UserInfo("Andrey", "Kozlov", "t7days.1@gmail.com", "7397077139", "E4 9RT");
        EnquiryInfo enquiry = new EnquiryInfo(user, whyDoYouWantAdviser, "Financial planning", "Wealth management", "£50,000+");

        new UnbiasedMainPage().openUnbiasedMainPage().
                searchAdvisersInLocation(typeAdv, location).
                setFilterByAdvice(enquiry.speciality).
                setFilterByAssetValue(enquiry.assetValue).
                clickContactUsOnFirstAdviser().
                fillUserInfo(user).
                fillWhyDoYouWantAdviser(whyDoYouWantAdviser).
                fillSpeciality(enquiry.speciality).
                fillSpecificAreaOfAdice(enquiry.specificArea).
                fillAssetValue(enquiry.assetValue).
                clickSendEnquiryError().
                checkRequiredCapcha();
    }

    @Title("Test: Send enquiry to test adviser")
    @Test
    public void SendEnquiryTest(){
        String typeAdv = "Financial adviser";
        String location = "Cardiff";
        UserInfo user = new UserInfo("Andrey", "Kozlov", "t7days.1@gmail.com", "7397077139", "E4 9RT");
        AdviserInfo adviser = new AdviserInfo("test consumer 2507","Financial planning", "Independent Financial Adviser");
        EnquiryInfo enquiry = new EnquiryInfo(user, whyDoYouWantAdviser, adviser.typeAdviser, "Wealth management", "£20,000+");

        new UnbiasedMainPage().openUnbiasedMainPage().
                searchAdvisersInLocation(typeAdv, location).
                researchWithDisabledCapcha().
                setFilterByAdviserRestrictions(adviser.adviserRestrictions).
                setFilterByAdvice(enquiry.speciality).
                setFilterByAssetValue(enquiry.assetValue).
                clickAdviserContactUs(adviser.name).
                fillUserInfo(user).
                fillWhyDoYouWantAdviser(whyDoYouWantAdviser).
                fillSpeciality(enquiry.speciality).
                fillSpecificAreaOfAdice(enquiry.specificArea).
                fillAssetValue(enquiry.assetValue).
                clickSendEnquiry().
                checkTYEnquiry();
    }

}
