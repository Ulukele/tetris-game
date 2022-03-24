package common;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesParser {
    private final Properties properties;

    public PropertiesParser(String filename) throws IOException {
        properties = new Properties();
        properties.load(new FileReader(filename));
    }

    public PropertiesParser(Properties properties) {
        this.properties = properties;
    }

    public String getString(String key) throws NullPointerException {
        return properties.get(key).toString();
    }

    public int getInteger(String key) throws NullPointerException, NumberFormatException {
        return Integer.parseInt(getString(key));
    }
}
