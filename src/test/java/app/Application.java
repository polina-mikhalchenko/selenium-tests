package app;

import driver.DriverManager;
import io.qameta.allure.Attachment;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Application {
    private RemoteWebDriver driver;
    protected WebDriverWait wait;
    public LoginHelper session;
    public InventoryHelper inventory;
    public CheckoutHelper checkout;



    public Application (RemoteWebDriver _driver) {
        driver = _driver;
        session = new LoginHelper(driver);
        inventory = new InventoryHelper(driver);
        checkout = new CheckoutHelper(driver);
    }
    public static WebDriver getDriver() {
        return DriverManager.getDriver();
    }


    public void quit () {
        driver.quit();
    }

    public boolean textIsOnThisPage(String text) {
        List<WebElement> l= driver.findElements(By.xpath("//*[contains(text(),'" + text + "')]"));
        return !l.isEmpty();
    }






}
