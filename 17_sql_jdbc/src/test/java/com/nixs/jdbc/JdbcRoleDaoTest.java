package com.nixs.jdbc;

import com.github.database.rider.core.DBUnitRule;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.nixs.model.Role;
import com.nixs.service.DbService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;

@RunWith(JUnit4.class)
public class JdbcRoleDaoTest {
    private DbService dbService = new DbService();

    @Rule
    public DBUnitRule dbUnitRule = DBUnitRule.instance(dbService.getConnection());

    private JdbcRoleDao roleDao;


    @Before
    public void setUp() {
        roleDao = new JdbcRoleDao(dbService);
    }

    @Test
    public void shouldCreateRoles() {
        Role actualTest = new Role();
        actualTest.setName("TEST");
        actualTest.setId(1L);

        Role actualUser = new Role();
        actualUser.setName("USER");
        actualUser.setId(2L);


        roleDao.create(actualTest);
        roleDao.create(actualUser);

        Role expectedTest = roleDao.findByName("TEST");
        Role expectedUser = roleDao.findByName("USER");

        assertEquals(expectedTest, actualTest);
        assertEquals(expectedUser, actualUser);
    }

    @Test
    @DataSet(value = "roles.xml")
    @ExpectedDataSet("roles-dataset.xml")
    public void shouldUpdateRole() {
        Role role = roleDao.findById(1L);
        role.setName("ADMIN");
        roleDao.update(role);
    }

    @Test
    @DataSet("roles.xml")
    public void shouldRemoveRoleAfterAdd() {
        Role roleTest = new Role();
        roleTest.setName("TEST");

        roleDao.create(roleTest);
        List<Role> rolesBeforeRemove = roleDao.findAll();
        assertEquals(3, rolesBeforeRemove.size());

        roleDao.remove(roleTest);
        List<Role> rolesAfterRemove = roleDao.findAll();
        assertEquals(2, rolesAfterRemove.size());
    }

    @Test
    @DataSet("roles-dataset.xml")
    public void shouldFindAllRolesDataSet() {
        List<Role> roles = roleDao.findAll();
        assertEquals(3, roles.size());
    }

    @Test
    @DataSet("roles-default.xml")
    public void shouldFindAdminById() {
        Role role = roleDao.findById(1L);
        assertEquals("ADMIN", role.getName());
    }

    @Test
    @DataSet("roles-default.xml")
    public void shouldFindUserByName() {
        Role role = roleDao.findByName("USER");
        assertEquals(Optional.of(2L), Optional.of(role.getId()));
    }
}
