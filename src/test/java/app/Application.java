package app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.NoSuchElementException;

public class Application {
    private WebDriver driver;
    public LoginHelper session;
    public InventoryHelper inventory;
    public CheckoutHelper checkout;

    public Application () {
        driver = new FirefoxDriver();
        session = new LoginHelper(driver);
        inventory = new InventoryHelper(driver);
        checkout = new CheckoutHelper(driver);
    }
    public void quit () {
        driver.quit();
    }

    public boolean textIsOnThisPage(String text) {
    try {
        driver.findElements(By.xpath("//*[text()='" + text + "']"));
        return true;
    } catch (NoSuchElementException e) {
        return false;
    }
}

}
