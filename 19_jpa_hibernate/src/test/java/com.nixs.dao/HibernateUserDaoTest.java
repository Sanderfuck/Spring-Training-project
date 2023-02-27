package com.nixs.dao;

import com.github.database.rider.core.DBUnitRule;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.core.util.EntityManagerProvider;
import com.nixs.model.Role;
import com.nixs.model.User;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static com.github.database.rider.core.util.EntityManagerProvider.em;
import static junit.framework.TestCase.assertEquals;

public class HibernateUserDaoTest {
    private HibernateDao<User> userDao;
    private HibernateDao<Role> roleDao;

    @ClassRule
    public static EntityManagerProvider emProvider = EntityManagerProvider.instance("TestDB");

    @Rule
    public DBUnitRule dbUnitRule = DBUnitRule.instance(emProvider.connection());

    @Before
    public void setUp() {
//        userDao = new HibernateUserDao(em().unwrap(Session.class).getSessionFactory());
//        roleDao = new HibernateRoleDao(em().unwrap(Session.class).getSessionFactory());
    }

    @Test
    @DataSet("dataset/users.yml")
    public void shouldFindUserById() {
        User user = userDao.findById(1L).get();
        assertEquals("admin", user.getLogin());
    }

    @Test
    @DataSet("dataset/users.yml")
    public void shouldFindUserByLogin() {
        User user = userDao.findByName("user").get();
        assertEquals(Optional.of(2L), Optional.of(user.getId()));
    }

    @Test
    @DataSet("dataset/users.yml")
    public void shouldFindAllUsersDataSet() {
        List<User> users = userDao.findAll();
        assertEquals(2, users.size());
    }

    @Test
    @DataSet(value = "dataset/roles-update.yml", cleanBefore = true)
    public void shouldCreateUser() {

        User userAdmin = new User();
        userAdmin.setLogin("admin");
        userAdmin.setPassword("password");
        userAdmin.setEmail("email@nixs.com");
        userAdmin.setFirstName("firstName");
        userAdmin.setLastName("lastName");
        userAdmin.setBirthday(new Date(1999, 1, 1));
        userAdmin.setRole(roleDao.findByName("ADMIN").get());

        userDao.save(userAdmin);

        User userUser = new User();
        userUser.setLogin("user");
        userUser.setPassword("password2");
        userUser.setEmail("email2@nixs.com");
        userUser.setFirstName("firstName2");
        userUser.setLastName("lastName2");
        userUser.setBirthday(new Date(1990, 1, 1));
        userUser.setRole(roleDao.findByName("USER").get());

        userDao.save(userUser);

        List<User> usersList = userDao.findAll();
        assertEquals(2, usersList.size());

        User adminGetByName = userDao.findByName("admin").get();
        assertEquals(userAdmin, adminGetByName);

        User userGetByName = userDao.findByName("user").get();
        assertEquals(userUser, userGetByName);
    }

    @Test
    @DataSet("dataset/users.yml")
    @ExpectedDataSet("dataset/users-update.yml")
    public void shouldUpdateUser() {
        User user = userDao.findById(1L).get();
        user.setLogin("test");
        userDao.save(user);
    }

    @Test
    @DataSet("dataset/users.yml")
    @ExpectedDataSet("dataset/users-remove.yml")
    public void shouldRemoveUser() {
        User user = new User();
        user.setId(2L);
        userDao.delete(user.getId());
    }
}
