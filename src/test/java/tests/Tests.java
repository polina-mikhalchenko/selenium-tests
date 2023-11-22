package tests;

import io.qameta.allure.Description;
import model.CheckoutInfo;
import model.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import comparators.Comparators;
import static org.junit.jupiter.api.Assertions.*;

public class Tests extends TestBase{

    @ParameterizedTest
    @MethodSource("tests.Data#validCustomer")
    @Description("Вход пользователя с корректными данными")
    public void correctLogin(Customer customer) {
        app.login(customer.getUsername(), customer.getPassword());
        boolean text = app.textIsOnThisPage("Products");
        assertTrue(text);
    }
    @ParameterizedTest
    @MethodSource("tests.Data#invalidCustomer")
    @Description("Вход пользователя с некорректными данными")
    public void incorrectLogin(Customer customer) {
        app.login(customer.getUsername(), customer.getPassword());
        boolean text = app.textIsOnThisPage(customer.getText());
        assertTrue(text);
    }
    @Test
    @Description("Сортировка товара по убыванию в алфовитном порядке")
    public void sortByZtoA() {
        app.standardUserLogin();
        ArrayList<String> oldList = app.getAllProductsFromStore("inventory_item_name");
        app.sort("Name (Z to A)", "inventory_item_name");
        ArrayList<String> newList = app.getAllProductsFromStore("inventory_item_name");
        ArrayList<String> correctList = new ArrayList<>(oldList);
        correctList.sort(Comparator.reverseOrder());
        assertNotEquals(oldList, newList);
        assertEquals(newList, correctList);
    }
    @Test
    @Description("Сортировка товара по возрастанию цены")
    public void sortByPriceLowToHigh() {
        app.standardUserLogin();
        ArrayList<String> oldList = app.getAllProductsFromStore("inventory_item_price");
        app.sort("Price (low to high)", "inventory_item_price");
        ArrayList<String> newList = app.getAllProductsFromStore("inventory_item_price");
        ArrayList<String> correctList = new ArrayList<>(oldList);
        correctList.sort(Comparators.ascComparator);
        assertNotEquals(oldList, newList);
        assertEquals(correctList, newList);
    }
    @Test
    @Description("Сортировка товара по убыванию цены")
    public void sortByPriceHighToLow() {
        app.standardUserLogin();
        ArrayList<String> oldList = app.getAllProductsFromStore("inventory_item_price");
        app.sort("Price (high to low)", "inventory_item_price");
        ArrayList<String> newList = app.getAllProductsFromStore("inventory_item_price");
        ArrayList<String> correctList = new ArrayList<>(oldList);
        correctList.sort(Comparators.descComparator);
        assertNotEquals(oldList, newList);
        assertEquals(newList, correctList);
    }
    @Test
    @Description("Добаление товара в корзину")
    public void addItemsToCart() {
        app.standardUserLogin();
        HashMap items = app.addProductToCart();
        HashMap itemsInCart = app.checkProductsInCart();
        assertEquals(items, itemsInCart);
    }

    @ParameterizedTest
    @MethodSource("tests.Data#customerInfo")
    @Description("Оформление заказа")
    public void checkout(CheckoutInfo checkoutInfo){
        app.standardUserLogin();
        HashMap items = app.addProductToCart();
        app.inputCheckoutInfo(checkoutInfo);
        app.checkoutOverview(items);
        boolean text = app.textIsOnThisPage(checkoutInfo.getText());
        assertTrue(text);
    }
    @ParameterizedTest
    @MethodSource("tests.Data#invalidCustomerInfo")
    @Description("Проверка ошибок в форме оформления заказа")
    public void invalidCheckout(CheckoutInfo checkoutInfo){
        app.standardUserLogin();
        app.inputCheckoutInfo(checkoutInfo);
        boolean text = app.textIsOnThisPage(checkoutInfo.getText());
        assertTrue(text);
    }
}
