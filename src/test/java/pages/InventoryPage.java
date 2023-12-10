package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class InventoryPage extends Page{
    public InventoryPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    @Step("Открыть страницу инвентарь")
    public void openInventoryPage() {
        driver.get("https://www.saucedemo.com/inventory.html");
    }
    @Step("Получить количество элементов находящихся в корзине по значку на иконке корзины")
    public String getNumberByShoppingCartBadge() {
        return driver.findElement(By.xpath("//span[@class='shopping_cart_badge']")).getText();
    }
    @Step("Отсортировать элементы")
    public void sortElements(String sortType) {
        Select drpSort = new Select(driver.findElement(By.className("product_sort_container")));
        drpSort.selectByVisibleText(sortType);
    }
    @Step("Сбросить состояние приложения")
    public void resetAppState() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        driver.findElement(By.id("react-burger-menu-btn")).click();
        driver.findElement(By.id("reset_sidebar_link")).click();
        driver.findElement(By.id("react-burger-cross-btn")).click();
    }
    public int randQuantity(int max, int min) {
        return  (int) ((Math.random() * (max - min)) + min);
    }
    @Step("Получить список наименований продуктов")
    public ArrayList<String> getItemNames(String className) {
        ArrayList<String> itemsNames = new ArrayList<>();
        for (WebElement name : driver.findElements(By.className(className))) {
            itemsNames.add(name.getText());
        }
        return itemsNames;
    }
    @Step("Получить список цен продуктов")
    public ArrayList<Double> getProductPrices(String className) {
        ArrayList<Double> itemsPrices = new ArrayList<>();
        for (WebElement price : driver.findElements(By.className(className))) {
            String selectNumbers = price.getText().replace('$', ' ');
            itemsPrices.add(Double.valueOf(selectNumbers));
        }
        return itemsPrices;
    }
    public String getName(int itemIndex) {
        return getItemNames("inventory_item_name").get(itemIndex);
    }
    public double getPrice(int itemIndex) {
        return getProductPrices("inventory_item_price").get(itemIndex);
    }
    @Step("Нажать кнопку добавить в корзину")
    public void clickAddToCartButton(int index) {
        List<WebElement> buttons = driver.findElements(By.xpath("//div[@class=\"inventory_item\"]//button"));
        WebElement button = buttons.get(index);
        button.click();
    }
    @Step("Открыть страницу корзина")
    public void openCart() {
        driver.get("https://www.saucedemo.com/cart.html");
    }
    @Step("Получить список элементов в корзине")
    public ArrayList<WebElement> getCartProducts() {
        return  (ArrayList<WebElement>) driver.findElements(By.className("cart_item"));
    }
    public List<WebElement> productsList(String elemName) {
        return driver.findElements(By.className(elemName));
    }

}
