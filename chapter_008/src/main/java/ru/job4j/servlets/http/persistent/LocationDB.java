package ru.job4j.servlets.http.persistent;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.*;

/**
 * Class LocationDB creates tables: Country and City with references by country_id.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 3.
 * @since 27.09.2019.
 */
public class LocationDB {

    private static final BasicDataSource SOURCE = new BasicDataSource();

    private static final LocationDB INSTANCE = new LocationDB();

    public LocationDB() {
        SOURCE.setDriverClassName("org.postgresql.Driver");
        SOURCE.setUrl("jdbc:postgresql://localhost:5432/tracker");
        SOURCE.setUsername("postgres");
        SOURCE.setPassword("password");
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
        SOURCE.setDefaultAutoCommit(false);
        checkTableExist();
    }

    public static LocationDB getInstance() {
        return INSTANCE;
    }

    /**
     * This method get map of cities with specific country_id
     * via join request by country.id
     *
     * @param countryName name
     * @return map of cities in specific country
     */
    public Map<Integer, String> getCities(String countryName) {
        Map<Integer, String> cities = new TreeMap<>();
        Connection connection = null;
        PreparedStatement st;
        ResultSet rs;
        Savepoint savepoint = null;
        try {
            connection = SOURCE.getConnection();
            st = connection.prepareStatement("select city.id, city.name from city join country on country_id = country.id where country.name = ?;");
            st.setString(1, countryName);
            rs = st.executeQuery();
            savepoint = connection.setSavepoint();
            while (rs.next()) {
                cities.put(rs.getInt(1), rs.getString(2));
            }
            connection.commit();
        } catch (SQLException e) {
            try {
                Objects.requireNonNull(connection).rollback(savepoint);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            close(connection);
        }
        return cities;
    }

    /**
     * This method get map of countries
     *
     * @return map of countries
     */
    public Map<Integer, String> getCountries() {
        Map<Integer, String> countries = new TreeMap<>();
        Connection connection = null;
        Statement st;
        ResultSet rs;
        Savepoint savepoint = null;
        try {
            connection = SOURCE.getConnection();
            st = connection.createStatement();
            rs = st.executeQuery("SELECT * FROM country;");
            savepoint = connection.setSavepoint();
            while (rs.next()) {
                countries.put(rs.getInt(1), rs.getString(2));
            }
            connection.commit();
        } catch (SQLException e) {
            try {
                Objects.requireNonNull(connection).rollback(savepoint);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            close(connection);
        }
        return countries;
    }

    /**
     * Check table country exist or not.
     * If table doesn't exist then create.
     */
    public void checkTableExist() {
        Connection connection = null;
        DatabaseMetaData metaData;
        Savepoint savepoint = null;
        ResultSet rs;
        try {
            connection = SOURCE.getConnection();
            Statement st = connection.createStatement();
            metaData = connection.getMetaData();
            rs = metaData.getTables(null, null, "country", null);
            savepoint = connection.setSavepoint();
            if (!rs.next()) {
                st.executeUpdate("create table country (id SERIAL primary key, name varchar(20))");
                fillCountry(connection, savepoint);
                st.executeUpdate("create table city (id SERIAL primary key, name varchar(20),"
                        + "country_id integer references country(id))");
                fillCity(connection, savepoint);
            }
            connection.commit();
        } catch (SQLException e) {
            try {
                Objects.requireNonNull(connection).rollback(savepoint);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            close(connection);
        }
    }

    /**
     * Method creates cities into BD.
     *
     * @param connection get connection.
     * @param savepoint  get savepoint.
     */
    private void fillCity(Connection connection, Savepoint savepoint) {
        Map<Integer, List<String>> cities = new TreeMap<>();
        cities.put(1, List.of("Airdrie", "Brooks", "Cold Lake", "Duncan", "Thompson"));
        cities.put(2, List.of("Allstedt", "Baiersdorf", "Dortmund", "Elsfleth", "Gotha"));
        cities.put(3, List.of("Ainan", "Erimo", "Ginan", "Heguri", "Ide"));
        cities.put(4, List.of("Moscow", "Nizhny Novgorod", "St-Peterburg", "Vladimir", "Vladivostok"));
        cities.put(5, List.of("Chicago", "Houston", "Los Angeles", "New York", "Phoenix"));
        try (PreparedStatement st = connection.prepareStatement("INSERT INTO city(name, country_id)"
                + "values(?, ?);")) {
            for (Map.Entry<Integer, List<String>> entry : cities.entrySet()) {
                for (String city : entry.getValue()) {
                    st.setString(1, city);
                    st.setInt(2, entry.getKey());
                    st.addBatch();
                }
            }
            savepoint = connection.setSavepoint();
            st.executeBatch();
        } catch (SQLException e) {
            try {
                connection.rollback(savepoint);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Method creates countries into BD.
     *
     * @param connection get connection.
     * @param savepoint  get savepoint.
     */
    private void fillCountry(Connection connection, Savepoint savepoint) {
        List<String> countries = new ArrayList<>(List.of("Canada", "Germany", "Japan", "Russia", "USA"));
        try (PreparedStatement st = connection.prepareStatement("INSERT INTO country(name)"
                + "values(?);")) {
            for (String country : countries) {
                st.setString(1, country);
                st.addBatch();
            }
            savepoint = connection.setSavepoint();
            st.executeBatch();
        } catch (SQLException e) {
            try {
                connection.rollback(savepoint);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * This method close connection.
     *
     * @param connect connection
     */
    private void close(Connection connect) {
        if (connect != null) {
            try {
                connect.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}