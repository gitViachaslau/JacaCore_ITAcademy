/**
 * Класс подгружает файл с настройками соединения
 */

package by.academy.cinema.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtil {

    public static final Properties PROPERTIES = new Properties();

    static {
        loadProperties();
    }


    public static String getValue(String key) {
        return PROPERTIES.getProperty(key);
    }

    private static void loadProperties() {
        String fileName = "application.properties";
        try (InputStream resourceStream = PropertyUtil.class.getClassLoader().getResourceAsStream(fileName)) {
            PROPERTIES.load(resourceStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
