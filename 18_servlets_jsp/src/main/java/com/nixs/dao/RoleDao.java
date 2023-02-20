package com.nixs.dao;

import com.nixs.db.DbService;
import com.nixs.model.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RoleDao implements Dao<Role> {
    private static final String ID_COLUMN = "id";
    private static final String NAME_COLUMN = "name";
    private static final Logger logger = LogManager.getLogger(RoleDao.class);
    private DbService dbService;
//    private static List<Role> roles;

//    public RoleDao() {
//        roles = new ArrayList<>();
//        roles.add(new Role(1L,"ADMIN"));
//        roles.add(new Role(2L,"USER"));
//    }


    public RoleDao() {
        dbService = new DbService();
    }

    @Override
    public boolean add(Role role) {
        logger.info("Create method of role was called. Param: role ={}", role);
        String sql = "INSERT INTO role (name) VALUES (?)";
        try (Connection connection = dbService.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, role.getName());
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    role.setId(generatedKeys.getLong(1));
                }
            }
        } catch (SQLException e) {
            logger.error("Role not created");
            throw new RuntimeException("Role not created " + e);
        }
        return true;
    }

    @Override
    public boolean update(Role role) {
        logger.trace("Update method of role was called. Param: role ={}", role);
        String sql = "UPDATE role SET name = ? WHERE id = ?";
        try (Connection connection = dbService.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, role.getName());
            statement.setLong(2, role.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Role not updated");
            throw new RuntimeException("Role not updated " + e);
        }
        return true;
    }

    @Override
    public boolean delete(Long id) {
        logger.info("Remove method of role was called. Param: id ={}", id);
        String sql = "DELETE FROM role WHERE id = ?";
        try (Connection connection = dbService.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Role not removed");
            throw new RuntimeException("Role not removed " + e);
        }
        return true;
    }

    @Override
    public Role getByName(String name) {
//        for(Role role : roles) {
//            if (role.getName().equals(name)) {
//                return role;
//            }
//        }
//        return null;
        logger.info("FindByName method of role was called. Param: name ={}", name);
        String sql = "SELECT * FROM role WHERE name = ?";
        try (Connection connection = dbService.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            return getRole(statement);
        } catch (SQLException e) {
            logger.error("Role not founded by name");
            throw new RuntimeException("Role not founded by name " + e);
        }
    }

    @Override
    public Role getById(Long id) {
//        for(Role role : roles) {
//            if (role.getId().equals(id)) {
//                return role;
//            }
//        }
//        return null;
        logger.info("FindById method of role was called. Param: id ={}", id);
        String sql = "SELECT * FROM role WHERE id = ?";
        try (Connection connection = dbService.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            return getRole(statement);
        } catch (SQLException e) {
            logger.error("Role not founded by id");
            throw new RuntimeException("Role not founded by id " + e);
        }
    }

    @Override
    public List<Role> getAll() {
//        return roles;
        logger.info("FindAll method of role was called.");
        String sql = "SELECT * FROM role";
        try (Connection connection = dbService.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            List<Role> roles = new ArrayList<>();
            while (resultSet.next()) {
                Role role = new Role();
                role.setId(resultSet.getLong(ID_COLUMN));
                role.setName(resultSet.getString(NAME_COLUMN));
                roles.add(role);
            }
            return roles;
        } catch (SQLException e) {
            logger.error("Roles not founded");
            throw new RuntimeException("Roles not founded " + e);
        }
    }

    private Role getRole(PreparedStatement statement) throws SQLException {
        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                Role role = new Role();
                role.setId(resultSet.getLong(ID_COLUMN));
                role.setName(resultSet.getString(NAME_COLUMN));
                return role;
            } else {
                return null;
            }
        }
    }
}
