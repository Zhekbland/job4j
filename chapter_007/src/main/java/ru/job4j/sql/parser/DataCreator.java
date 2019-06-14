package ru.job4j.sql.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class DataCreator creates database vacancy and table jobs.
 * Takes information from class Parser and put into table jobs.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 14.06.2019.
 */
public class DataCreator implements AutoCloseable {

    private final Config config;

    private Connection connection;

    /**
     * LOGGER creates log and writes (nameOfVacancy, link) into file jobslog.txt and in console.
     */
    private static final Logger LOGGER = LogManager.getLogger(DataCreator.class.getName());

    /**
     * Create and init config and get values from parser.properties.
     */
    public DataCreator() {
        this.config = new Config();
        config.init();
    }

    /**
     * Get connection and check database exist or not.
     */
    public void getConnection() {
        try {
            this.connection = DriverManager.getConnection(
                    config.get("jdbc.url"),
                    config.get("jdbc.username"),
                    config.get("jdbc.password")
            );
            System.out.println("Database already exists");
        } catch (SQLException e) {
            System.out.println("Database doesn't exist");
            createDB();
        }
    }

    /**
     * Create database if it wasn't create.
     */
    private void createDB() {
        try {
            this.connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/",
                    config.get("jdbc.username"),
                    config.get("jdbc.password")
            );
            try (Statement st = connection.createStatement()) {
                String sql = "CREATE DATABASE vacancy";
                st.executeUpdate(sql);
                System.out.println("Database was create");
                getConnection();
            } catch (SQLException e) {
                System.out.println("DB error: " + e);
            }
        } catch (SQLException e) {
            System.out.println("DB error: " + e);
        }
    }

    /**
     * Check table jobs exist or not.
     * If table doesn't exist then create.
     *
     * @return exist (true) or not exist (false).
     */
    public boolean checkTableExist() {
        boolean result = false;
        try (Statement st = connection.createStatement()) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet rs = metaData.getTables(null, null, "jobs", null);
            if (rs.next()) {
                result = true;
            } else {
                st.executeUpdate("create table jobs (id serial primary key, name varchar(100) UNIQUE,"
                        + "text varchar(10000), link varchar(150))");
                System.out.println("Table jobs was create.");
            }
        } catch (SQLException e) {
            System.out.println("DB error: " + e);
        }
        return result;
    }

    /**
     * Fill database if we'v got elements.
     *
     * @param job - is a list of vacancy from class Parser.
     */
    public void fillDB(List<Vacancy> job) {
        if (job.size() != 0) {
            try (PreparedStatement st = connection.prepareStatement("INSERT INTO jobs(name, text, link) values(?, ?, ?)")) {
                for (Vacancy vacancy : job) {
                    st.setString(1, vacancy.getNameOfVacancy());
                    st.setString(2, vacancy.getTextOfVacancy());
                    st.setString(3, vacancy.getLink());
                    st.addBatch();
                    LOGGER.info("Name of vacancy:   " + vacancy.getNameOfVacancy());
                    LOGGER.info("Link of vacancy:   " + vacancy.getLink());
                    LOGGER.info("-------------------------------------------------------------------------");
                }
                st.executeBatch();
            } catch (SQLException e) {
                System.out.println("DB error: " + e);
            }
        } else {
            System.out.println("There's not new vacancies");
        }
    }

    /**
     * Get list of vacancy from database to compare old list from DB and new list from fresh parse.
     *
     * @return list of vacancy from DB.
     */
    public List<Vacancy> getListFromDB() {
        List<Vacancy> oldList = new ArrayList<>();
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT name, text, link FROM jobs");
            while (rs.next()) {
                oldList.add(new VacancyBuilder()
                        .nameOfVacancy(rs.getString(1))
                        .textOfVacancy(rs.getString(2))
                        .link(rs.getString(3))
                        .build());
            }

        } catch (SQLException e) {
            System.out.println("DB error: " + e);
        }
        return oldList;
    }

    /**
     * Implementation of AutoCloseable.
     */
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
