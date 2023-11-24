package app;

import org.openqa.selenium.WebDriver;
import pages.LoginPage;

public class LoginHelper{
    private LoginPage loginPage;
    public LoginHelper (WebDriver driver) {
        loginPage = new LoginPage(driver);
    }
    public void login(String username, String password) {
        loginPage.open();
        loginPage.fillFields(username, password);
        loginPage.login();
    }
    public void standardUserLogin() {
        login("standard_user", "secret_sauce");
    }
}
