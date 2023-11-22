package app;

import model.CheckoutInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CheckoutPage;
import pages.InventoryPage;
import pages.LoginPage;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Application {
    private WebDriver driver;
    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private CheckoutPage checkoutPage;
    private final int MAX_ITEMS_IN_CART = 6;
    private final int MIN_ITEMS_IN_CART = 1;
    private final int MAX_ITEM_INDEX = 5;
    private final int MIN_ITEM_INDEX = 0;

    public Application () {
        driver = new FirefoxDriver();
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        checkoutPage = new CheckoutPage(driver);
    }
    public void quit () {
        driver.quit();
    }

    public boolean isOnThisPage(String id) {
        return !driver.findElements(By.id(id)).isEmpty();
    }
public boolean textIsOnThisPage(String text) {
    boolean present;
    try {
        present = !driver.findElements(By.xpath("//*[text()='" + text + "']")).isEmpty();
        present = true;
    } catch (NoSuchElementException e) {
        present = false;
    }
    return present;
}
    public void login(String username, String password) {
        loginPage.open();
        loginPage.fillFields(username, password);
        loginPage.login();
    }
    public void standardUserLogin() {
        login("standard_user", "secret_sauce");
    }
    public ArrayList<String> getAllProductsFromStore(String elemName) {
        ArrayList<String> list = new ArrayList<>();
        List<WebElement> items = driver.findElements(By.className(elemName));
        for (WebElement item : items){
            list.add(item.getText());
        }
        return list;
    }
    public ArrayList<String> sort(String sortType, String elemName) {
        inventoryPage.sortElements(sortType);
        return getAllProductsFromStore(elemName);
    }
    public HashMap addProductToCart() {
        //refresh app to clean the shopping cart
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(200));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("react-burger-menu-btn")));
        isOnThisPage("react-burger-menu-btn");
        inventoryPage.resetAppState();
        inventoryPage.openInventoryPage();
        int randomQuantityItemsToAdd = inventoryPage.randQuantity(MAX_ITEMS_IN_CART, MIN_ITEMS_IN_CART);
        HashMap productsInCart = new HashMap();
        List<Integer> indexes = new ArrayList<>();
        //select random items
        int itemIndex = -1;
        for (int i = 0; i < randomQuantityItemsToAdd; i++) {
            while (itemIndex == -1 || indexes.contains(itemIndex)) {
                itemIndex = inventoryPage.randQuantity(MAX_ITEM_INDEX,MIN_ITEM_INDEX);
            }
            indexes.add(itemIndex);
            productsInCart.put(inventoryPage.getName(itemIndex), inventoryPage.getPrice(itemIndex));
            inventoryPage.clickAddToCartButton(itemIndex);
        }
        //verify shopping cart badge by items' quantity
        String number = inventoryPage.getNumberByShoppingCartBadge();
        assertEquals(randomQuantityItemsToAdd, Integer.parseInt(number));
        return productsInCart;
    }
    public HashMap checkProductsInCart() {
        inventoryPage.openCart();
        List<WebElement> list = inventoryPage.getCartProducts();
        HashMap ProductsInCart = new HashMap();
        int itemIndex = -1;
        for (int i = 0; i < list.size(); i++) {
            itemIndex++;
            ProductsInCart.put(inventoryPage.getName(itemIndex), inventoryPage.getPrice(itemIndex));
        }
        return ProductsInCart;
    }
    public void inputCheckoutInfo(CheckoutInfo checkInfo) {
        inventoryPage.openCart();
        checkoutPage.checkoutButtonClick();
        checkoutPage.fillCheckoutForm(
                checkInfo.getFirstName(), checkInfo.getLastName(), checkInfo.getPostalCode());
        checkoutPage.continueButtonClick();
    }
    public void checkoutOverview(HashMap price) {
        double expPrice = checkoutPage.countPriceWithoutTax(price);
        double actPrice = checkoutPage.getPriceWithoutTax();
        assertEquals(expPrice, actPrice);
        double expTax = checkoutPage.countTax(expPrice);
        double actTax = checkoutPage.getTax();
        assertEquals(expTax, actTax);
        double expTotal = checkoutPage.countTotalPrice(expPrice, expTax);
        double actTotal = checkoutPage.getTotalPrice();
        assertEquals(expTotal, actTotal);
    }
}
