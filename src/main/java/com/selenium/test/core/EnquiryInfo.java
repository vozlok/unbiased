package com.selenium.test.core;

public class EnquiryInfo {
    public UserInfo userInfo;
    public String whyDoYouWantAdviser;
    public String adviserName;
    public String speciality;
    public String specificArea;
    public String assetValue;

    public EnquiryInfo(UserInfo userInfo, String whyDoYouWantAdviser, String adviserName, String specificArea, String assetValue, String speciality) {
        this.userInfo = userInfo;
        this.whyDoYouWantAdviser = whyDoYouWantAdviser;
        this.adviserName = adviserName;
        this.speciality = speciality;
        this.specificArea = specificArea;
        this.assetValue = assetValue;
    }
}
