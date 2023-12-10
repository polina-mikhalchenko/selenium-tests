package config;
import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.LoadType;
@LoadPolicy(LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:general.properties",
        "classpath:grid.properties"})
public interface Configuration extends Config {
    @Key("headless")
    Boolean headless();

    @Key("url.base")
    String url();

    @Key("timeout")
    long timeout();

    @Key("grid.url")
    String gridUrl();

    @Key("grid.port")
    String gridPort();
}
