package com.nixs.dao;

import com.nixs.db.DbService;
import com.nixs.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements Dao<User> {
    private static final Logger logger = LogManager.getLogger(UserDao.class);
    private DbService dbService;

    public UserDao() {
        dbService = new DbService();
    }

    @Override
    public boolean add(User user) {
        logger.info("Create method of user was called. Param: user = {}", user);
        String sql = "INSERT INTO users "
                + "(login, password, email, first_name, last_name, birthday, role_id) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = dbService.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            setUser(user, statement);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.error("User not created");
            throw new RuntimeException("User not created ", e);
        }
    }

    @Override
    public boolean update(User user) {
        logger.info("Update method of user was called. Param: user ={}", user);
        String sql = "UPDATE users "
                + "SET login = ?, password = ?, email = ?, first_name = ?, last_name = ?,"
                + " birthday = ?, role_id = ? WHERE id = ?";
        try (Connection connection = dbService.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            setUser(user, statement);
            statement.setLong(8, user.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.error("User not updated");
            throw new RuntimeException("User not updated " + e);
        }
    }

    @Override
    public boolean delete(Long id) {
        logger.info("Remove method of user was called. Param: id ={}", id);
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection connection = dbService.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.error("User not deleted");
            throw new RuntimeException("User not deleted " + e);
        }
    }

    @Override
    public User getById(Long id) {
        logger.info("FindById method of user was called. Param: id = {}", id);
        String sql = "SELECT * FROM users WHERE id = ?";
        try (Connection connection = dbService.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    User user = getUser(resultSet);
                    return user;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            logger.error("User not founded by id");
            throw new RuntimeException("User not founded by id " + e);
        }
    }

    @Override
    public User getByName(String login) {
        logger.info("FindByName method of user was called");
        String sql = "SELECT * FROM users WHERE login = ?";
        try {
            return findUser(login, sql);
        } catch (SQLException e) {
            logger.error("User not found by id");
            throw new RuntimeException("User not found by id", e);
        }
    }

    @Override
    public List<User> getAll() {
        logger.info("FindAll method of user was called");
        String sql = "SELECT * FROM users";
        try (Connection connection = dbService.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                User user = getUser(resultSet);
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            logger.error("Users not founded");
            throw new RuntimeException("Users not founded" + e);
        }
    }

    private void setUser(User user, PreparedStatement statement) throws SQLException {
        statement.setString(1, user.getLogin());
        statement.setString(2, user.getPassword());
        statement.setString(3, user.getEmail());
        statement.setString(4, user.getFirstName());
        statement.setString(5, user.getLastName());
        statement.setDate(6, user.getBirthday());
        statement.setLong(7, user.getRoleId());
    }

    private User getUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("password"));
        user.setEmail(resultSet.getString("email"));
        user.setFirstName(resultSet.getString("first_name"));
        user.setLastName(resultSet.getString("last_name"));
        user.setBirthday(resultSet.getDate("birthday"));
        user.setRoleId(resultSet.getLong("role_id"));
        return user;
    }

    private User findUser(String login, String sql) throws SQLException {
        try (Connection connection = dbService.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, login);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return getUser(resultSet);
                } else {
                    return null;
                }
            }
        }
    }
}
