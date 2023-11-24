package tests;

import app.Application;
import io.qameta.allure.Step;
import org.junit.jupiter.api.BeforeEach;

public class TestBase {
    public static ThreadLocal<Application> tlApp = new ThreadLocal<>();
    public Application app;
    @Step("Start the application")
    @BeforeEach
    public void start () {
        System.setProperty("webdriver.gecko.driver", "C:\\drivers\\geckodriver-v0.33.0-win64\\geckodriver.exe");

        if (tlApp.get() != null) {
            app = tlApp.get();
            return;
        }
        app = new Application();
        tlApp.set(app);

        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> { app.quit(); app = null;}));
    }
}
