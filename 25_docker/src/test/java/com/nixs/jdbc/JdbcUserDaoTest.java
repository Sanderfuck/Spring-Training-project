package com.nixs.jdbc;

import com.github.database.rider.core.DBUnitRule;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.nixs.model.User;
import com.nixs.service.DbService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;

public class JdbcUserDaoTest {
    private JdbcUserDao userDao;
    private DbService dbService = new DbService();

    @Rule
    public DBUnitRule dbUnitRule = DBUnitRule.instance(dbService.getConnection());


    @Before
    public void setUp() {
        userDao = new JdbcUserDao(dbService);
    }

    @Test
    @DataSet(cleanBefore = true)
    public void shouldCreateUser() {
        User userAdmin = new User();
        userAdmin.setId(1L);
        userAdmin.setLogin("admin");
        userAdmin.setPassword("password");
        userAdmin.setEmail("email@nixs.com");
        userAdmin.setFirstName("firstName");
        userAdmin.setLastName("lastName");
        userAdmin.setBirthday(new Date(1999,1,1));
        userAdmin.setRole_id(1L);

        userDao.create(userAdmin);

        User userUser = new User();
        userUser.setId(2L);
        userUser.setLogin("user");
        userUser.setPassword("password2");
        userUser.setEmail("email2@nixs.com");
        userUser.setFirstName("firstName2");
        userUser.setLastName("lastName2");
        userUser.setBirthday(new Date(1990, 1, 1));
        userUser.setRole_id(2L);

        userDao.create(userUser);

        List<User> usersList = userDao.findAll();
        assertEquals(2, usersList.size());
        User userGetById = userDao.findById(1L);
        assertEquals(userAdmin, userGetById);
    }

    @Test
    @DataSet("database/dataset/users.yml")
    @ExpectedDataSet("database/dataset/users-update.yml")
    public void shouldUpdateUser() {
        User user = userDao.findById(1L);
        user.setLogin("test");
        userDao.update(user);
    }

    @Test
    @DataSet("database/dataset/users.yml")
    @ExpectedDataSet("database/dataset/users-remove.yml")
    public void shouldRemoveUser() {
        User user = new User();
        user.setId(2L);
        userDao.remove(user);
    }

    @Test
    @DataSet("database/dataset/users.yml")
    public void shouldFindAllUsersDataSet() {
        List<User> users = userDao.findAll();
        assertEquals(2, users.size());
    }

    @Test
    @DataSet("database/dataset/users.yml")
    public void shouldFindUserById() {
        User user = userDao.findById(1L);
        assertEquals("admin", user.getLogin());
    }

    @Test
    @DataSet("database/dataset/users.yml")
    public void shouldFindUserByLogin() {
        User user = userDao.findByLogin("user");
        assertEquals(Optional.of(2L), Optional.of(user.getId()));
    }

    @Test
    @DataSet("database/dataset/users.yml")
    public void shouldFindUserByEmail() {
        User user = userDao.findByEmail("email@nixs.com");
        assertEquals(Optional.of(1L), Optional.of(user.getId()));
    }
}
