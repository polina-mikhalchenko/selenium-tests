package tests;

import comparators.Comparators;
import driver.BrowserFactory;
import io.qameta.allure.Description;
import model.CheckoutInfo;
import model.Customer;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import utils.MyExtension;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MyExtension.class)
public class CrossBrowserTests extends TestBase{
    @ParameterizedTest
    @MethodSource("tests.DataForLogin#validCustomer")
    @Description("Вход пользователя с корректными данными")
    public void correctLogin(Customer customer) {
        beforeStartEachTest(customer.getBrowser());
        updateTestNameAndHistoryIdForAllure((
                customer.getBrowser() + ": " + "Пользователь: " + customer.getUsername()), (customer.getBrowser()));
        app.session.login(customer.getUsername(), customer.getPassword());
        boolean text = app.textIsOnThisPage("Products");
        assertTrue(text);
    }
    @ParameterizedTest
    @MethodSource("tests.DataForLogin#invalidCustomer")
    @Description("Вход пользователя с некорректными данными")
    public void incorrectLogin(Customer customer) {
        beforeStartEachTest(customer.getBrowser());
        updateTestNameAndHistoryIdForAllure((
                customer.getBrowser() + ": " + "Проверить ошибку при входе в систему: " + customer.getText()), (customer.getBrowser()));
        app.session.login(customer.getUsername(), customer.getPassword());
        boolean text = app.textIsOnThisPage(customer.getText());
        assertTrue(text);
    }
    @ParameterizedTest
    @EnumSource(BrowserFactory.class)
    @Description("Сортировка товара по убыванию в алфовитном порядке")
    public void sortByZtoA(BrowserFactory browser) {
        beforeStartEachTest(browser.name().toLowerCase());
        updateTestNameAndHistoryIdForAllure((
                browser.name() + ": " + "Отсортировать товары в каталоге от Z до A"), (browser.name()));
        app.session.standardUserLogin();
        ArrayList<String> oldList = app.inventory.getAllProductsFromStore("inventory_item_name");
        app.inventory.sort("Name (Z to A)", "inventory_item_name");
        ArrayList<String> newList = app.inventory.getAllProductsFromStore("inventory_item_name");
        ArrayList<String> correctList = new ArrayList<>(oldList);
        correctList.sort(Comparator.reverseOrder());
        assertNotEquals(oldList, newList);
        assertEquals(newList, correctList);
    }
    @ParameterizedTest
    @EnumSource(BrowserFactory.class)
    @Description("Сортировка товара по возрастанию цены")
    public void sortByPriceLowToHigh(BrowserFactory browser) {
        beforeStartEachTest(browser.name().toLowerCase());
        updateTestNameAndHistoryIdForAllure((
                browser.name() + ": " + "Отсортировать товары в каталоге по возрастанию цены"), (browser.name()));
        app.session.standardUserLogin();
        ArrayList<String> oldList = app.inventory.getAllProductsFromStore("inventory_item_price");
        app.inventory.sort("Price (low to high)", "inventory_item_price");
        ArrayList<String> newList = app.inventory.getAllProductsFromStore("inventory_item_price");
        ArrayList<String> correctList = new ArrayList<>(oldList);
        correctList.sort(Comparators.ascComparator);
        assertNotEquals(oldList, newList);
        assertEquals(correctList, newList);
    }

    @ParameterizedTest
    @EnumSource(BrowserFactory.class)
    @Description("Сортировка товара по убыванию цены")
    public void sortByPriceHighToLow(BrowserFactory browser) {
        beforeStartEachTest(browser.name().toLowerCase());
        updateTestNameAndHistoryIdForAllure((
                browser.name() + ": " + "Отсортировать товары в каталоге по убыванию цены"), (browser.name()));
        app.session.standardUserLogin();
        ArrayList<String> oldList = app.inventory.getAllProductsFromStore("inventory_item_price");
        app.inventory.sort("Price (high to low)", "inventory_item_price");
        ArrayList<String> newList = app.inventory.getAllProductsFromStore("inventory_item_price");
        ArrayList<String> correctList = new ArrayList<>(oldList);
        correctList.sort(Comparators.descComparator);
        assertNotEquals(oldList, newList);
        assertEquals(newList, correctList);
    }
    @ParameterizedTest
    @EnumSource(BrowserFactory.class)
    @Description("Добаление товара в корзину")
    public void addItemsToCart(BrowserFactory browser) {
        beforeStartEachTest(browser.name().toLowerCase());
        updateTestNameAndHistoryIdForAllure((
                browser.name() + ": " + "Добавить товары в корзину"), (browser.name()));
        app.session.standardUserLogin();
        HashMap<String, Double> items = app.inventory.addProductToCart();
        HashMap<String, Double> itemsInCart = app.inventory.checkProductsInCart();
        assertEquals(items, itemsInCart);
    }

    @ParameterizedTest
    @MethodSource("tests.DataForCheckout#customerInfo")
    @Description("Оформление заказа")
    public void checkout(CheckoutInfo checkoutInfo){
        beforeStartEachTest(checkoutInfo.getBrowser());
        updateTestNameAndHistoryIdForAllure((
                checkoutInfo.getBrowser() + ": " + "Оформить заказ на Имя: " +
                        checkoutInfo.getFirstName() + " " + checkoutInfo.getLastName() ), (checkoutInfo.getBrowser()));
        app.session.standardUserLogin();
        HashMap<String, Double> items = app.inventory.addProductToCart();
        app.checkout.inputCheckoutInfo(checkoutInfo);
        app.checkout.checkoutOverview(items);
        boolean text = app.textIsOnThisPage(checkoutInfo.getText());
        assertTrue(text);
    }
    @ParameterizedTest
    @MethodSource("tests.DataForCheckout#invalidCustomerInfo")
    @Description("Проверка ошибок в форме оформления заказа")
    public void invalidCheckout(CheckoutInfo checkoutInfo){
        beforeStartEachTest(checkoutInfo.getBrowser());
        updateTestNameAndHistoryIdForAllure((
                checkoutInfo.getBrowser() + ": " + "В форме оформления заказа проверить ошибку:" +
                        checkoutInfo.getText()), (checkoutInfo.getBrowser()));
        app.session.standardUserLogin();
        app.checkout.inputCheckoutInfo(checkoutInfo);
        boolean text = app.textIsOnThisPage(checkoutInfo.getText());
        assertTrue(text);
    }
}
