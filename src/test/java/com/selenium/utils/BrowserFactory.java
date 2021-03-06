package com.selenium.utils;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.OperaDriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BrowserFactory {

    public static void selectBrowser(String browser) throws Exception {
        switch (browser) {
            case "chrome":
                Configuration.browser = "chrome";
                Configuration.startMaximized=true;
                WebDriverManager.chromedriver().setup();
                break;
            case "opera":
                Configuration.browser = "opera";
                //WebDriverManager.firefoxdriver().setup();
                OperaDriverManager.getInstance().setup();
                break;
            default:
                throw new IllegalStateException("Browser " + browser + " not supported in tests");
        }
    }
}
