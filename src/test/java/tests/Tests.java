package tests;

import io.qameta.allure.Description;
import model.CheckoutInfo;
import model.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import comparators.Comparators;
import utils.MyExtension;
import utils.MyTestWatcher;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MyExtension.class)
public class Tests extends TestBase{

    @ParameterizedTest
    @MethodSource("tests.Data#validCustomer")
    @Description("Вход пользователя с корректными данными")
    public void correctLogin(Customer customer) {
        app.session.login(customer.getUsername(), customer.getPassword());
        boolean text = app.textIsOnThisPage("Products");
        assertTrue(text);
    }
    @Test
    public void error() {
        app.session.login("error", "123");
        app.inventory.addProductToCart();
    }
    @ParameterizedTest
    @MethodSource("tests.Data#invalidCustomer")
    @Description("Вход пользователя с некорректными данными")
    public void incorrectLogin(Customer customer) {
        app.session.login(customer.getUsername(), customer.getPassword());
        boolean text = app.textIsOnThisPage(customer.getText());
        assertTrue(text);
    }
    @Test
    @Description("Сортировка товара по убыванию в алфовитном порядке")
    public void sortByZtoA() {
        app.session.standardUserLogin();
        ArrayList<String> oldList = app.inventory.getAllProductsFromStore("inventory_item_name");
        app.inventory.sort("Name (Z to A)", "inventory_item_name");
        ArrayList<String> newList = app.inventory.getAllProductsFromStore("inventory_item_name");
        ArrayList<String> correctList = new ArrayList<>(oldList);
        correctList.sort(Comparator.reverseOrder());
        assertNotEquals(oldList, newList);
        assertEquals(newList, correctList);
    }
    @Test
    @Description("Сортировка товара по возрастанию цены")
    public void sortByPriceLowToHigh() {
        app.session.standardUserLogin();
        ArrayList<String> oldList = app.inventory.getAllProductsFromStore("inventory_item_price");
        app.inventory.sort("Price (low to high)", "inventory_item_price");
        ArrayList<String> newList = app.inventory.getAllProductsFromStore("inventory_item_price");
        ArrayList<String> correctList = new ArrayList<>(oldList);
        correctList.sort(Comparators.ascComparator);
        assertNotEquals(oldList, newList);
        assertEquals(correctList, newList);
    }
    @Test
    @Description("Сортировка товара по убыванию цены")
    public void sortByPriceHighToLow() {
        app.session.standardUserLogin();
        ArrayList<String> oldList = app.inventory.getAllProductsFromStore("inventory_item_price");
        app.inventory.sort("Price (high to low)", "inventory_item_price");
        ArrayList<String> newList = app.inventory.getAllProductsFromStore("inventory_item_price");
        ArrayList<String> correctList = new ArrayList<>(oldList);
        correctList.sort(Comparators.descComparator);
        assertNotEquals(oldList, newList);
        assertEquals(newList, correctList);
    }
    @Test
    @Description("Добаление товара в корзину")
    public void addItemsToCart() {
        app.session.standardUserLogin();
        HashMap<String, Double> items = app.inventory.addProductToCart();
        HashMap<String, Double> itemsInCart = app.inventory.checkProductsInCart();
        assertEquals(items, itemsInCart);
    }

    @ParameterizedTest
    @MethodSource("tests.Data#customerInfo")
    @Description("Оформление заказа")
    public void checkout(CheckoutInfo checkoutInfo){
        app.session.standardUserLogin();
        HashMap<String, Double> items = app.inventory.addProductToCart();
        app.checkout.inputCheckoutInfo(checkoutInfo);
        app.checkout.checkoutOverview(items);
        boolean text = app.textIsOnThisPage(checkoutInfo.getText());
        assertTrue(text);
    }
    @ParameterizedTest
    @MethodSource("tests.Data#invalidCustomerInfo")
    @Description("Проверка ошибок в форме оформления заказа")
    public void invalidCheckout(CheckoutInfo checkoutInfo){
        app.session.standardUserLogin();
        app.checkout.inputCheckoutInfo(checkoutInfo);
        boolean text = app.textIsOnThisPage(checkoutInfo.getText());
        assertTrue(text);
    }
}
