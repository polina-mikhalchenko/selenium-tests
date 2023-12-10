package driver;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;

import static config.ConfigurationManager.configuration;

public enum BrowserFactory {
    CHROME {
        @Override
        public ChromeOptions getOptions() {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments(START_MAXIMIZED);
            chromeOptions.addArguments("--disable-infobars");
            chromeOptions.addArguments("--disable-notifications");

            if(configuration().headless()) chromeOptions.addArguments("--headless=new");

            return chromeOptions;
        }
    }, FIREFOX {
        @Override
        public FirefoxOptions getOptions() {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.addArguments(START_MAXIMIZED);

            if(configuration().headless()) firefoxOptions.addArguments("--headless");

            return firefoxOptions;
        }
    };

    private static final String START_MAXIMIZED = "--start-maximized";

    public abstract AbstractDriverOptions<?> getOptions();
}
