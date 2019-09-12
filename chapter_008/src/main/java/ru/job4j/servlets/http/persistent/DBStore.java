package ru.job4j.servlets.http.persistent;

import net.jcip.annotations.ThreadSafe;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class DBStore creates DB and does many actions.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 2.
 * @since 04.09.2019.
 */
@ThreadSafe
public class DBStore implements Store {

    private static final BasicDataSource SOURCE = new BasicDataSource();

    /**
     * Instance of singleton.
     */
    private static final DBStore INSTANCE = new DBStore();

    public DBStore() {
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

    public static DBStore getInstance() {
        return INSTANCE;
    }


    @Override

    public User add(User user) {
        Connection connection = null;
        PreparedStatement st;
        Savepoint savepoint = null;
        try {
            connection = SOURCE.getConnection();
            st = connection.prepareStatement("INSERT INTO store(name, login, email, password, role)"
                    + "values(?, ?, ?, ?, ?);");
            st.setString(1, user.getName());
            st.setString(2, user.getLogin());
            st.setString(3, user.getEmail());
            st.setString(4, user.getPassword());
            st.setString(5, user.getRole().toString());
            savepoint = connection.setSavepoint();
            st.executeUpdate();
            user.setId(getId(connection, savepoint));
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
        return user;
    }

    private int getId(Connection connection, Savepoint savepoint) {
        int id = 0;
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery("SELECT MAX(id) FROM store")
        ) {
            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            try {
                connection.rollback(savepoint);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return id;
    }

    @Override
    public void update(User user) {
        Connection connection = null;
        PreparedStatement st;
        Savepoint savepoint = null;
        try {
            connection = SOURCE.getConnection();
            st = connection.prepareStatement("UPDATE store SET name = ?, login = ?, email = ?, password = ?"
                    + ",role = ? WHERE id = ?;");
            st.setString(1, user.getName());
            st.setString(2, user.getLogin());
            st.setString(3, user.getEmail());
            st.setString(4, user.getPassword());
            st.setString(5, user.getRole().toString());
            st.setInt(6, user.getId());
            savepoint = connection.setSavepoint();
            st.execute();
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

    @Override
    public void delete(User user) {
        Connection connection = null;
        PreparedStatement st;
        Savepoint savepoint = null;
        try {
            connection = SOURCE.getConnection();
            st = connection.prepareStatement("DELETE FROM store WHERE id = ?;");
            st.setInt(1, user.getId());
            savepoint = connection.setSavepoint();
            st.execute();
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

    @Override
    public List<User> findAll() {
        List<User> result = new ArrayList<>();
        Connection connection = null;
        Statement st;
        ResultSet rs;
        Savepoint savepoint = null;
        try {
            connection = SOURCE.getConnection();
            st = connection.createStatement();
            rs = st.executeQuery("SELECT * FROM store");
            savepoint = connection.setSavepoint();
            while (rs.next()) {
                result.add(new User(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        Role.valueOf(rs.getString(6))
                ));
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
        return result;
    }

    @Override
    public User findById(int id) {
        User result = null;
        Connection connection = null;
        Statement st;
        ResultSet rs;
        Savepoint savepoint = null;
        try {
            connection = SOURCE.getConnection();
            st = connection.createStatement();
            rs = st.executeQuery("SELECT * FROM store WHERE id = " + id + ";");
            savepoint = connection.setSavepoint();
            while (rs.next()) {
                result = new User(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        Role.valueOf(rs.getString(6))
                );
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
        return result;
    }

    /**
     * Check table jobs exist or not.
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
            rs = metaData.getTables(null, null, "store", null);
            savepoint = connection.setSavepoint();
            if (!rs.next()) {
                st.executeUpdate("create table store (id SERIAL primary key, name varchar(20),"
                        + "login varchar(20) UNIQUE, email varchar(20), password varchar(20), role varchar(20))");
                createAdmin(connection, savepoint);
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
     * Method creates admin into BD.
     *
     * @param connection get connection.
     * @param savepoint  get savepoint.
     */
    private void createAdmin(Connection connection, Savepoint savepoint) {
        try (PreparedStatement st = connection.prepareStatement("INSERT INTO store(name, login, email, password, role)"
                + "values(?, ?, ?, ?, ?);")) {
            st.setString(1, "admin");
            st.setString(2, "admin");
            st.setString(3, "admin@gmail.com");
            st.setString(4, "root");
            st.setString(5, Role.ADMIN.toString());
            savepoint = connection.setSavepoint();
            st.executeUpdate();
        } catch (SQLException e) {
            try {
                connection.rollback(savepoint);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

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
