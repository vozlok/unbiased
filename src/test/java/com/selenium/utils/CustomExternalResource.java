package com.selenium.utils;

import com.codeborne.selenide.Configuration;
import org.junit.rules.ExternalResource;

import static com.codeborne.selenide.Selenide.close;
import static com.selenium.TestBase.clearCookies;
import static com.selenium.TestBase.setTestMode;
import static com.selenium.test.common.Constants.TIMEOUT_SELENIDE;
import static com.selenium.test.utils.PropertiesReader.getProperty;
import static com.selenium.test.utils.PropertiesReader.readPropertiesFile;
import static com.selenium.utils.BrowserFactory.selectBrowser;

public class CustomExternalResource extends ExternalResource {


    @Override
    protected void before() throws Throwable {
        readPropertiesFile();
        Configuration.baseUrl = "https://" + getProperty("baseUrl");
        Configuration.timeout = TIMEOUT_SELENIDE;
        selectBrowser(getProperty("browser"));
        clearCookies();
        setTestMode();
    }

    @Override
    protected void after() {
        close();
    }
}
