package app;

import model.CheckoutInfo;
import org.openqa.selenium.WebDriver;
import pages.CheckoutPage;
import pages.InventoryPage;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckoutHelper {
    private InventoryPage inventoryPage;
    private CheckoutPage checkoutPage;
    public CheckoutHelper(WebDriver driver) {
        inventoryPage = new InventoryPage(driver);
        checkoutPage = new CheckoutPage(driver);
    }
    public void inputCheckoutInfo(CheckoutInfo checkInfo) {
        inventoryPage.openCart();
        checkoutPage.checkoutButtonClick();
        checkoutPage.fillCheckoutForm(
                checkInfo.getFirstName(), checkInfo.getLastName(), checkInfo.getPostalCode());
        checkoutPage.continueButtonClick();
    }
    public void checkoutOverview(HashMap <String, Double> price) {
        double expPrice = checkoutPage.countPriceWithoutTax(price);
        double actPrice = checkoutPage.getPriceWithoutTax();
        assertEquals(expPrice, actPrice);
        BigDecimal expTax = checkoutPage.countTax(expPrice);
        double actTax = checkoutPage.getTax();
        assertEquals(expTax, BigDecimal.valueOf(actTax).setScale(2, RoundingMode.HALF_UP));
        BigDecimal expTotal = checkoutPage.countTotalPrice(expPrice, expTax);
        double actTotal = checkoutPage.getTotalPrice();
        assertEquals(expTotal, BigDecimal.valueOf(actTotal).setScale(2, RoundingMode.HALF_UP));
        checkoutPage.finishButtonClick();
    }
}
