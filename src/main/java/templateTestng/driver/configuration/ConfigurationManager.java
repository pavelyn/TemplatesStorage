package templateTestng.driver.configuration;

public class ConfigurationManager {

    private ConfigurationManager() {
    }

    public static Configuration configuration() {
        return org.aeonbits.owner.ConfigCache.getOrCreate(Configuration.class);
    }
}

