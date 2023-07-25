package com.alex.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropsUtil {
    private static final Properties properties = new Properties();
    private static final InputStream dbStream = PropsUtil.class.getClassLoader().getResourceAsStream("db.properties");


    // Read properties from the properties file
    public static String getDbProps(String key) throws IOException {
        properties.load(dbStream);

        return properties.getProperty(key);
    }
}
