package com.nixs.jdbc;

import com.github.database.rider.core.DBUnitRule;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.nixs.model.User;
import com.nixs.service.DbService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.swing.text.html.parser.Entity;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;

@RunWith(JUnit4.class)
public class JdbcUserDaoTest {
    private DbService dbService = new DbService();

    @Rule
    public DBUnitRule dbUnitRule = DBUnitRule.instance(dbService.getConnection());

    private JdbcUserDao userDao;

    @Before
    public void setUp() {
        userDao = new JdbcUserDao(dbService);
    }

    @Test
    @DataSet(cleanBefore = true)
    @ExpectedDataSet("user.xml")
    public void shouldCreateUsers() {
        User user = new User();
        user.setLogin("login");
        user.setPassword("password");
        user.setEmail("email@nixs.com");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setBirthday(LocalDate.of(1998, 1, 1));
        user.setRole_id(1L);

        userDao.create(user);
    }

    @Test
    @DataSet("users.xml")
    @ExpectedDataSet("users-result.xml")
    public void shouldUpdateUser() {
        User user = userDao.findById(1L);
        user.setLogin("ADMIN");
        userDao.update(user);
    }

    @Test
    @DataSet("roles-default.xml")
    @ExpectedDataSet("users-result.xml")
    public void shouldRemoveUser() {
        User user = new User();
        user.setId(2L);
        user.setLogin("USER");
        user.setPassword("password");
        user.setEmail("email!@nixs.com");
        user.setFirstName("firstName!");
        user.setLastName("lastName!");
        user.setBirthday(LocalDate.of(1998, 1, 1));
        user.setRole_id(2L);
        userDao.remove(user);
    }

    @Test
    @DataSet("result-default.xml")
    public void shouldFindAllUsersDataSet() {
        List<User> users = userDao.findAll();
        assertEquals(2, users.size());
    }

    @Test
    @DataSet("users-result.xml")
    public void shouldFindUserById() {
        User user = userDao.findById(1L);
        assertEquals("ADMIN", user.getLogin());
    }

    @Test
    @DataSet("result-default.xml")
    public void shouldFindUserByLogin() {
        User user = userDao.findByLogin("USER");
        assertEquals(Optional.of(2L), Optional.of(user.getId()));
    }

    @Test
    @DataSet("result-default.xml")
    public void shouldFindUserByEmail() {
        User user = userDao.findByEmail("email@nixs.com");
        assertEquals(Optional.of(1L), Optional.of(user.getId()));
    }
}
