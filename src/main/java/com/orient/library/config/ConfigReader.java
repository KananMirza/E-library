package com.orient.library.config;

import com.orient.library.exception.DataNotFoundException;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Properties;

@Component
public class ConfigReader {
    private final static String CONFIG_FILE = "config.properties";
    private static String IMAGE_SERVICE_PATH;

    static {
        try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (input == null) {
                throw new DataNotFoundException("Sorry, unable to find " + CONFIG_FILE);
            }

            Properties prop = new Properties();
            prop.load(input);

            // PATH set argument
            IMAGE_SERVICE_PATH = prop.getProperty("IMAGE_SERVICE_PATH");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getImageServicePath() {
        return IMAGE_SERVICE_PATH;
    }
}
