package driver;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.logging.Logger;

import static config.ConfigurationManager.configuration;
import static java.lang.String.format;

public class TargetFactory {
    private static final Logger logger = Logger.getLogger(TargetFactory.class.getName());

    public RemoteWebDriver createInstance(String browser) {
        logger.info(format("Creating a [%s] browser instance", browser));
        return createRemoteInstance(BrowserFactory.valueOf(browser.toUpperCase()).getOptions());
    }

    private RemoteWebDriver createRemoteInstance(MutableCapabilities capability) {
        RemoteWebDriver remoteWebDriver = null;
        try {
            String gridURL = format("http://%s:%s", configuration().gridUrl(), configuration().gridPort());
            logger.info(format("Tests will run at: %s for %s", gridURL, capability));

            remoteWebDriver = new RemoteWebDriver(new URL(gridURL), capability);
        } catch (java.net.MalformedURLException e) {
            logger.severe("Grid URL is invalid or Grid is not available");
            logger.severe(format("Browser: %s", capability.getBrowserName()));
            logger.severe(e.toString());
        } catch (IllegalArgumentException e) {
            logger.severe(format("Browser %s is not valid or recognized", capability.getBrowserName()));
            logger.severe(e.toString());
        }

        return remoteWebDriver;
    }
}
