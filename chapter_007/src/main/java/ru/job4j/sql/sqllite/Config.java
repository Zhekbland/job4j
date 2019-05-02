package ru.job4j.sql.sqllite;

import java.io.InputStream;
import java.util.Properties;

/**
 * Configuration of XML.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 02.05.2019.
 */
public class Config {
    private final Properties values = new Properties();

    public void init() {
        try (InputStream in = Config.class.getClassLoader().getResourceAsStream("magnit.properties")) {
            values.load(in);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public String get(String key) {
        return this.values.getProperty(key);
    }
}
