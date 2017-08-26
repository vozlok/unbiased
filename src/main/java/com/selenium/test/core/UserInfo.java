package com.selenium.test.core;

public class UserInfo {
    public String firstName;
    public String lastName;
    public String email;
    public String phone;
    public String postcode;

    public UserInfo(String firstName, String lastName, String email, String phone, String postcode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.postcode = postcode;
    }

    public UserInfo() {
    }
}
