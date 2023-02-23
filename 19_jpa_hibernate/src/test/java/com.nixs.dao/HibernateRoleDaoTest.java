package com.nixs.dao;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.core.api.dataset.SeedStrategy;
import com.github.database.rider.core.util.EntityManagerProvider;
import com.nixs.model.Role;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;

@RunWith(JUnit4.class)
public class HibernateRoleDaoTest {
    private HibernateDao<Role> roleDao;

    @Rule
    public EntityManagerProvider emProvider = EntityManagerProvider.instance("TestDB");

    @Before
    public void setUp() {
        roleDao = new HibernateRoleDao();
    }

    @Test
    @DataSet("database/dataset/roles.yml")
    public void shouldFindRoleById() {
        Role role = roleDao.findById(1L).get();
        assertEquals("TEST", role.getName());
    }

    @Test
    @DataSet("database/dataset/roles.yml")
    public void shouldFindRoleByName() {
        Role role = roleDao.findByName("USER").get();
        assertEquals(Optional.of(2L), Optional.of(role.getId()));
    }

    @Test
    @DataSet("database/dataset/roles.yml")
    public void shouldFindAllRolesDataSet() {
        List<Role> roles = roleDao.findAll();
        assertEquals(2, roles.size());
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

        roleDao.save(actualTest);
        roleDao.save(actualUser);

        Role expectedTest = roleDao.findByName("TEST").get();
        Role expectedUser = roleDao.findByName("USER").get();

        assertEquals(expectedTest, actualTest);
        assertEquals(expectedUser, actualUser);
    }

    @Test
    @DataSet(value = "database/dataset/roles.yml", strategy = SeedStrategy.CLEAN_INSERT)
    @ExpectedDataSet("database/dataset/roles-update.yml")
    public void shouldUpdateRole() {
        Role role = roleDao.findById(1L).get();
        role.setName("ADMIN");
        roleDao.save(role);
    }

    @Test
    @DataSet(value = "database/dataset/roles.yml", cleanBefore = true)
    public void shouldRemoveRole() {
        Role roleTest = new Role();
        roleTest.setName("TEST");
        roleTest.setId(1L);

        roleDao.delete(roleTest.getId());
        List<Role> rolesBeforeRemove = roleDao.findAll();
        assertEquals(1, rolesBeforeRemove.size());
    }
}
