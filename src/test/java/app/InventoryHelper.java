package app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.InventoryPage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InventoryHelper{
    private final int MAX_ITEMS_IN_CART = 6;
    private final int MIN_ITEMS_IN_CART = 1;
    private final int MAX_ITEM_INDEX = 5;
    private final int MIN_ITEM_INDEX = 0;
    private InventoryPage inventoryPage;
    public InventoryHelper(WebDriver driver){
        inventoryPage = new InventoryPage(driver);
    }
    public ArrayList<String> getAllProductsFromStore(String elemName) {
        ArrayList<String> list = new ArrayList<>();
        List<WebElement> items = inventoryPage.productsList(elemName);
        for (WebElement item : items){
            list.add(item.getText());
        }
        return list;
    }
    public void sort(String sortType, String elemName) {
        inventoryPage.sortElements(sortType);
        getAllProductsFromStore(elemName);
    }
    public HashMap<String, Double> addProductToCart() {
        //refresh app to clean the shopping cart
        inventoryPage.resetAppState();
        inventoryPage.openInventoryPage();
        int randomQuantityItemsToAdd = inventoryPage.randQuantity(MAX_ITEMS_IN_CART, MIN_ITEMS_IN_CART);
        HashMap<String, Double> productsInCart = new HashMap<String, Double>();
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
    public HashMap<String, Double> checkProductsInCart() {
        inventoryPage.openCart();
        List<WebElement> list = inventoryPage.getCartProducts();
        HashMap<String, Double> ProductsInCart = new HashMap<String, Double>();
        int itemIndex = -1;
        for (int i = 0; i < list.size(); i++) {
            itemIndex++;
            ProductsInCart.put(inventoryPage.getName(itemIndex), inventoryPage.getPrice(itemIndex));
        }
        return ProductsInCart;
    }
}
