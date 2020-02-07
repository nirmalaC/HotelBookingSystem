package helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Utils {
    /**
     *This is the method to read the config.properties file
     */
    public static Properties readPropertisFile() throws IOException {
        FileInputStream fis = null;
        fis = new FileInputStream("./src/test/resources/Config.properties");
        Properties property = new Properties();
        property.load(fis);
        return property;
    }

}
