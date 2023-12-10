package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;

public class CheckoutPage extends Page{
    public CheckoutPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    @FindBy(id = "checkout")
    public WebElement checkoutButton;
    @Step("Нажать кнопку оформить заказ")
    public void checkoutButtonClick() {
        checkoutButton.click();
    }
    @FindBy(id = "continue-shopping")
    public WebElement continueShoppingButton;
    @Step("Нажать кнопку продолжить покупки")
    public void continueShoppingButtonClick() {
        continueShoppingButton.click();
    }
    @FindBy(id = "continue")
    public WebElement continueButton;
    @Step("Нажать кнопку продолжить")
    public void continueButtonClick() {
        continueButton.click();
    }
    @FindBy(id = "finish")
    public WebElement finishButton;
    @Step("Нажать кнопку завершить")
    public void finishButtonClick(){finishButton.click();}
    @Step("Заполнить форму оформления заказа")
    public void fillCheckoutForm(String firstName, String lastName, String postalCode) {
        driver.findElement(By.id("first-name")).sendKeys(firstName);
        driver.findElement(By.id("last-name")).sendKeys(lastName);
        driver.findElement(By.id("postal-code")).sendKeys(postalCode);
    }
    public double findPriceOnPage(String str) {
        String elem = driver.findElement(By.className(str))
                .getText();
        return Double.parseDouble(elem.substring(elem.lastIndexOf('$')+1));
    }
    @Step("Посчитать общую стоимость добавленных в корзину товаров без учета налога")
    public double countPriceWithoutTax(HashMap<String, Double> list) {
        double totalPrice = 0.0;
        for (String key : list.keySet()){
            totalPrice += list.get(key);
        }
        return totalPrice;
    }
    @Step("Получить общую стоимость добавленных в корзину товаров без учета налога")
    public double getPriceWithoutTax() {
        return findPriceOnPage("summary_subtotal_label");
    }
    @Step("Получить сумму налога")
    public double getTax(){
        return findPriceOnPage("summary_tax_label");
    }
    @Step("Посчитать 8% налог")
    public BigDecimal countTax(double price){
        return BigDecimal.valueOf((price / 100) * 8).setScale(2, RoundingMode.HALF_UP);
    }
    @Step("Посчитать итоговую сумму")
    public BigDecimal countTotalPrice(double price, BigDecimal tax) {
        BigDecimal bd = new BigDecimal(price + tax.doubleValue()).setScale(2, RoundingMode.HALF_UP);
        return bd.setScale(2, RoundingMode.HALF_UP);
    }
    @Step("Получить итоговую сумму")
    public double getTotalPrice() {
        return findPriceOnPage("summary_total_label");
    }
}
