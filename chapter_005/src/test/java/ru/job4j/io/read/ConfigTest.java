package ru.job4j.io.read;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ConfigTest {

    @Test
    public void whenWeGetValueByKey() {
        Config config = new Config("app.properties");
        config.load();
        assertThat(config.value("hibernate.dialect"), is("org.hibernate.dialect.PostgreSQLDialect"));
    }

    @Test
    public void whenWeGetValueByKeyTwo() {
        Config config = new Config("app.properties");
        config.load();
        assertThat(config.value("hibernate.connection.url"),
                is("jdbc:postgresql://127.0.0.1:5432/trackstudio"));
    }

    @Test
    public void whenWeGetValueByKeyThree() {
        Config config = new Config("app.properties");
        config.load();
        assertThat(config.value("hibernate.connection.driver_class"),
                is("org.postgresql.Driver"));
    }

    @Test
    public void whenWeGetValueByKeyFour() {
        Config config = new Config("app.properties");
        config.load();
        assertThat(config.value("hibernate.connection.username"),
                is("postgres"));
    }

    @Test
    public void whenWeGetValueByKeyFive() {
        Config config = new Config("app.properties");
        config.load();
        assertThat(config.value("hibernate.connection.password"),
                is("password"));
    }
}