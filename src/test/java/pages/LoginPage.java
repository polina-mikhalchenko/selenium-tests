package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends Page{
    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    @Step("Открыть страницу авторицации")
    public void open() {
        driver.get("https://www.saucedemo.com/");
    }
    @FindBy(id = "user-name")
    public WebElement userName;
    @FindBy(id = "password")
    public WebElement password;
    @Step("Заполнить поля имя пользователя и пароль")
    public void fillFields(String user, String pass) {
        userName.sendKeys(user);
        password.sendKeys(pass);
    }
    @FindBy(id = "login-button")
    public WebElement loginButton;
    @Step("Нажать кнопку вход в систему")
    public void login() {
        loginButton.click();
    }
}
