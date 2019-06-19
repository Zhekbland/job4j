package ru.job4j.io.read;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringJoiner;

/**
 * Class Config read values from file and put this into HashMap.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 19.06.2019.
 */
public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    /**
     * Read lines from file, separates them and put into HashMap.
     */
    public void load() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                if (str.contains("=")) {
                    values.put(str.substring(0, str.indexOf('=')),
                            str.substring(str.indexOf('=') + 1));
                }
            }
        } catch (IOException e) {
            System.out.println("Exception: " + e);
        }
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    /**
     * Get values from HashMap by key;
     *
     * @param key - String value for getting values from HashMap.
     * @return String values of HashMap.
     */
    public String value(String key) {
        return values.get(key);
    }
}
