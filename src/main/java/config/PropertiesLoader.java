package config;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class PropertiesLoader {

    public String getProperty(String key){
        try {
            PropertiesConfiguration config = new PropertiesConfiguration();
            config.load("application.properties");
            return config.getString(key);

        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
