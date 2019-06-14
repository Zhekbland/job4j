package ru.job4j.sql.parser;

import java.io.InputStream;
import java.util.Properties;

/**
 * Configuration of JDBC.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 14.06.2019.
 */
public class Config {
    private final Properties values = new Properties();

    public void init() {
        try (InputStream in = Config.class.getClassLoader().getResourceAsStream("parser.properties")) {
            values.load(in);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public String get(String key) {
        return this.values.getProperty(key);
    }
}
