package com.selenium.test.core;

public class EnquiryInfo {
    public UserInfo userInfo;
    public String whyDoYouWantAdviser;
    public String speciality;
    public String specificArea;
    public String assetValue;

    public EnquiryInfo(UserInfo userInfo, String whyDoYouWantAdviser, String speciality, String specificArea, String assetValue) {
        this.userInfo = userInfo;
        this.whyDoYouWantAdviser = whyDoYouWantAdviser;
        this.speciality = speciality;
        this.specificArea = specificArea;
        this.assetValue = assetValue;
    }
}
