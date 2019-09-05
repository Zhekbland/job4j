package ru.job4j.servlets.http.persistent;

import net.jcip.annotations.ThreadSafe;
import org.apache.commons.dbcp2.BasicDataSource;
import org.checkerframework.checker.lock.qual.GuardedBy;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class DBStore creates DB and does many actions.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 04.09.2019.
 */
@ThreadSafe
public class DBStore implements Store {

    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static final DBStore INSTANCE = new DBStore();

    @GuardedBy("addLock")
    private final Lock addLock = new ReentrantLock();

    @GuardedBy("updateLock")
    private final Lock updateLock = new ReentrantLock();

    @GuardedBy("deleteLock")
    private final Lock deleteLock = new ReentrantLock();

    public DBStore() {
        SOURCE.setDriverClassName("org.postgresql.Driver");
        SOURCE.setUrl("jdbc:postgresql://localhost:5432/tracker");
        SOURCE.setUsername("postgres");
        SOURCE.setPassword("password");
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
        checkTableExist();
    }

    public static DBStore getInstance() {
        return INSTANCE;
    }


    @Override

    public User add(User user) {
        addLock.lock();
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection
                     .prepareStatement("INSERT INTO store(name, login, email) values(?, ?, ?);")
        ) {
            st.setString(1, user.getName());
            st.setString(2, user.getLogin());
            st.setString(3, user.getEmail());
            st.execute();
            user.setId(getId(connection));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            addLock.unlock();
        }
        return user;
    }

    private int getId(Connection connection) {
        int id = 0;
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery("SELECT MAX(id) FROM store")
        ) {
            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public void update(User user) {
        updateLock.lock();
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection
                     .prepareStatement("UPDATE store SET name = ?, login = ?, email = ? WHERE id = ?;")
        ) {
            st.setString(1, user.getName());
            st.setString(2, user.getLogin());
            st.setString(3, user.getEmail());
            st.setInt(4, user.getId());
            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            updateLock.unlock();
        }
    }

    @Override
    public void delete(User user) {
        deleteLock.lock();
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection
                     .prepareStatement("DELETE FROM store WHERE id = ?;")
        ) {
            st.setInt(1, user.getId());
            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            deleteLock.unlock();
        }
    }

    @Override
    public List<User> findAll() {
        List<User> result = new ArrayList<>();
        try (Connection connection = SOURCE.getConnection();
             Statement st = connection
                     .createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM store")) {
            while (rs.next()) {
                result.add(new User(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public User findById(int id) {
        User result = null;
        try (Connection connection = SOURCE.getConnection();
             Statement st = connection
                     .createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM store WHERE id = " + id + ";")
        ) {
            while (rs.next()) {
                result = new User(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Check table jobs exist or not.
     * If table doesn't exist then create.
     */
    public void checkTableExist() {
        try (Connection connection = SOURCE.getConnection();
             Statement st = connection.createStatement()) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet rs = metaData.getTables(null, null, "store", null);
            if (!rs.next()) {
                st.executeUpdate("create table store (id SERIAL primary key, name varchar(20),"
                        + "login varchar(20) UNIQUE, email varchar(20))");
            }
        } catch (SQLException e) {
            System.out.println("DB error: " + e);
        }
    }
}
