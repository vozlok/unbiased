package com.selenium.utils;

import com.codeborne.selenide.Screenshots;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import static com.selenium.TestBase.clearCookies;

public class CustomWatcher extends TestWatcher {

    private final HTMLTemplate report = new HTMLTemplate();

    private boolean onFailedTest = true;
    private boolean onSucceededTest = true;

    @Override
    protected void starting(Description test) {
        if (onFailedTest || onSucceededTest) {
            Screenshots.startContext(test.getClassName(), test.getMethodName());
            report.start();
        }
    }

    @Override
    protected void succeeded(Description description) {
        if (onSucceededTest) {
            report.finish();
            AllureReportUtil.attachScreenshot();
        }
    }

    @Override
    protected void failed(Throwable e, Description description) {
        if (onFailedTest) {
            report.finish();
            AllureReportUtil.attachScreenshot();
        }
    }

    @Override
    protected void finished(Description description) {
        report.clean();
    }

}
