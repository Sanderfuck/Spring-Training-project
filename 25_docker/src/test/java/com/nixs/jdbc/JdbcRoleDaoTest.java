package com.nixs.jdbc;

import com.github.database.rider.core.DBUnitRule;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.core.api.dataset.SeedStrategy;
import com.nixs.model.Role;
import com.nixs.service.DbService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;

public class JdbcRoleDaoTest {
    private JdbcRoleDao roleDao;
    private DbService dbService = new DbService();

    @Rule
    public DBUnitRule dbUnitRule = DBUnitRule.instance(dbService.getConnection());

    @Before
    public void setUp() {
        roleDao = new JdbcRoleDao(dbService);

    }

    @Test
    @DataSet(cleanBefore = true)
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
    @DataSet(value = "database/dataset/roles.yml", strategy = SeedStrategy.CLEAN_INSERT)
    @ExpectedDataSet("database/dataset/roles-update.yml")
    public void shouldUpdateRole() {
        Role role = roleDao.findById(1L);
        role.setName("ADMIN");
        roleDao.update(role);
    }

    @Test
    @DataSet(value = "database/dataset/roles.yml", cleanBefore = true)
    public void shouldRemoveRole() {
        Role roleTest = new Role();
        roleTest.setName("TEST");
        roleTest.setId(1L);

        roleDao.remove(roleTest);
        List<Role> rolesBeforeRemove = roleDao.findAll();
        assertEquals(1, rolesBeforeRemove.size());
    }

    @Test
    @DataSet("database/dataset/roles.yml")
    public void shouldFindAllRolesDataSet() {
        List<Role> roles = roleDao.findAll();
        assertEquals(2, roles.size());
    }

    @Test
    @DataSet("database/dataset/roles.yml")
    public void shouldFindRoleById() {
        Role role = roleDao.findById(1L);
        assertEquals("TEST", role.getName());
    }

    @Test
    @DataSet("database/dataset/roles.yml")
    public void shouldFindRoleByName() {
        Role role = roleDao.findByName("USER");
        assertEquals(Optional.of(2L), Optional.of(role.getId()));
    }
}
