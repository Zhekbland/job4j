package ru.job4j.sql.sqllite;

import java.sql.Connection;
import java.util.*;
import java.sql.*;

/**
 * Create table MySQL and fill it.
 * Load table's values into List.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 2.
 * @since 02.05.2019.
 */
public class StoreSQL implements AutoCloseable {
    private final Config config;
    private Connection connect;

    public StoreSQL(Config config) {
        this.config = config;
        this.config.init();
    }

    /**
     * Clear table.
     */
    public void deleteTableIsNotEmpty() {
        try (Statement st = connect.createStatement();
             ResultSet rs = st.executeQuery("select * from entry")) {
            if (rs.next()) {
                st.executeUpdate("delete from entry");
            }
            connect.commit();
        } catch (SQLException e) {
            try {
                connect.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Generate batch of sql queries and put them into sql table.
     *
     * @param size of table (amount elements).
     */
    public void generate(int size) {
        deleteTableIsNotEmpty();
        try (PreparedStatement st = connect.prepareStatement("insert into entry(field) values(?)")) {
            for (int index = 1; index <= size; index++) {
                st.setInt(1, index);
                st.addBatch();
            }
            st.executeBatch();
            connect.commit();
        } catch (SQLException e) {
            try {
                connect.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Load values from sql database to List.
     *
     * @return List.
     */
    public List<Entry> load() {
        List<Entry> list = new ArrayList<>();
        try (Statement st = connect.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT * FROM entry");
            while (rs.next()) {
                list.add(new Entry(rs.getInt(1)));
            }

        } catch (SQLException e) {
            try {
                this.connect.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return list;
    }

    /**
     * Initialization of connection.
     *
     * @return true or false.
     */
    public boolean connection() {
        try {
            //for properties in InelliJ
//            this.connect = DriverManager.getConnection(config.get("db.url") + config.get("db.file"));
            //for Travis CI
            this.connect = DriverManager.getConnection("jdbc:sqlite:magnit.db");
            this.connect.setAutoCommit(false);
            connect.commit();
        } catch (SQLException e) {
            try {
                connect.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        checkDatabase();
        return this.connect != null;
    }

    /**
     * Check database and create table.
     */
    public void checkDatabase() {
        try (Statement st = this.connect.createStatement()) {
            DatabaseMetaData metadata = connect.getMetaData();
            ResultSet rs = metadata.getTables(null, null, "entry", null);
            if (!rs.next()) {
                st.executeUpdate("create table entry(field integer)");
            }
            connect.commit();
        } catch (SQLException e) {
            try {
                connect.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * AutoCloseable realization.
     */
    @Override
    public void close() {
        if (connect != null) {
            try {
                connect.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
