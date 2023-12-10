package tests;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import app.Application;
import driver.DriverManager;
import driver.TargetFactory;

import java.util.UUID;

public class TestBase {
    public Application app;

    protected RemoteWebDriver driver;
    protected WebDriverWait wait;

    @AfterEach
    void quitBrowser() {
        if (driver != null){
            driver.quit();
        }

        if (app != null){
            app.quit();
        }
    }




    protected void beforeStartEachTest(String browser) {
        driver = new TargetFactory().createInstance(browser);
        DriverManager.setDriver(driver);
        app = new Application(driver);

    }


    void updateTestNameAndHistoryIdForAllure(String newName, String browserName) {
        Allure.getLifecycle().updateTestCase(testResult -> testResult.setName(newName));
        String customHistoryId = generateConsistentHistoryId(newName, browserName);
        Allure.getLifecycle().updateTestCase(testResult -> testResult.setHistoryId(customHistoryId));
    }
    private String generateConsistentHistoryId(String newName, String browserName){
        String baseString = newName + browserName;
        return UUID.nameUUIDFromBytes(baseString.getBytes()).toString();
    }
}
