package ru.job4j.sql.tracker;

import ru.job4j.models.*;
import ru.job4j.tracker.ITracker;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Class Create TrackerSQL.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 16.04.2019.
 */
public class TrackerSQL implements ITracker, AutoCloseable {

    private Connection connection;

    /**
     * Initialization of connection.
     *
     * @return true or false.
     */
    public boolean init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            if (in != null) {
                config.load(in);
            }
            Class.forName(config.getProperty("driver-class-name"));
            this.connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
            checkDatabase();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return this.connection != null;
    }

    /**
     * Check database and recreate table.
     */
    public void checkDatabase() {
        try (Statement st = connection.createStatement()) {
            DatabaseMetaData metadata = connection.getMetaData();
            ResultSet rs = metadata.getTables(null, null, "items", null);
            if (rs.next()) {
                st.executeUpdate("drop table items");
            }
            st.executeUpdate("create table items(id serial primary key, name varchar(20), description varchar(200))");
            rs.close();
        } catch (SQLException e) {
            System.err.println("DB error: " + e);
        }
    }

    /**
     * Add item into table in SQL.
     *
     * @param item - has fields name, description.
     * @return item.
     */
    @Override
    public Item add(Item item) {
        try (PreparedStatement st = connection.prepareStatement(
                "insert into items(name, description) values(?, ?)")) {
            st.setString(1, item.getName());
            st.setString(2, item.getDescription());
            st.executeUpdate();
        } catch (SQLException e) {
            System.err.println("DB error: " + e);
        }
        return item;
    }

    /**
     * Replace item in a table.
     *
     * @param id   - id of replace item.
     * @param item - for replace.
     * @return - replace was true or false.
     */
    @Override
    public boolean replace(String id, Item item) {
        boolean result = false;
        try (PreparedStatement st = connection.prepareStatement("update items set name = (?),"
                + "description = (?) where id = (?)")) {
            st.setString(1, item.getName());
            st.setString(2, item.getDescription());
            st.setInt(3, Integer.parseInt(id));
            st.executeUpdate();
            result = true;
        } catch (SQLException e) {
            System.out.println("DB error: " + e);
        }
        return result;
    }


    /**
     * Delete Item from SQL table.
     *
     * @param id - item's id.
     * @return delete was true or false.
     */
    @Override
    public boolean delete(String id) {
        boolean result = false;
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery("select * from items where id = " + id)) {
            if (rs.next()) {
                st.executeUpdate("delete from items where id = " + id);
                result = true;
            }
        } catch (SQLException e) {
            System.err.println("DB error: " + e);
        }
        return result;
    }


    /**
     * Get all items from SQL table.
     *
     * @return Item[].
     */
    @Override
    public Item[] getAll() {
        List<Item> listItems = new ArrayList<>();
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery("select * from items")) {
            while (rs.next()) {
                listItems.add(new Item(rs.getString(2), rs.getString(3)));
            }
        } catch (SQLException e) {
            System.err.println("DB error: " + e);
        }
        return listItems.toArray(new Item[0]);
    }

    /**
     * Find item's name.
     *
     * @param key - item's name.
     * @return - item[].
     */
    @Override
    public Item[] findByName(String key) {
        List<Item> listItems = new ArrayList<>();
        try (PreparedStatement st = connection.prepareStatement("select * from items where name = (?)")) {
            st.setString(1, key);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                listItems.add(new Item(rs.getString(2), rs.getString(3)));
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println("DB error: " + e);
        }
        return listItems.toArray(new Item[0]);
    }

    /**
     * Find item by id.
     *
     * @param id - item's id.
     * @return item.
     */
    @Override
    public Item findById(String id) {
        Item item = new Item();
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery("select * from items where id = " + id)) {
            if (rs.next()) {
                item = new Item(rs.getString(2), rs.getString(3));
            }
        } catch (SQLException e) {
            System.err.println("DB error: " + e);
        }
        return item;
    }

    @Override
    public void close() {
        if (this.connection != null) {
            try {
                this.connection.close();
            } catch (SQLException e) {
                System.err.println("DB error: " + e);
            }
        }
    }
}
