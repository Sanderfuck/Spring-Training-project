package com.nixs.dao;

import com.nixs.exception.DataProcessingException;
import com.nixs.model.Role;
import com.nixs.util.HibernateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class HibernateRoleDao implements HibernateDao<Role> {
    private static final Logger logger = LogManager.getLogger(HibernateRoleDao.class);
    private SessionFactory sessionFactory;

    public HibernateRoleDao() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public Optional<Role> findById(Long id) {
        logger.info("Find By id method of role was called. Param: id = {}", id);

        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(Role.class, id));
        } catch (Exception e) {
            logger.error("Role not find by ID");
            throw new DataProcessingException("Can't get role by id: " + id, e);
        }
    }

    @Override
    public Optional<Role> findByName(String name) {
        logger.info("Find by name method of role was called. Param: name = {}", name);

        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(Role.class, name));
        } catch (Exception e) {
            logger.error("Role not find by name");
            throw new DataProcessingException("Can't get role by name: " + name, e);
        }
    }

    @Override
    public List<Role> findAll() {
        logger.info("Find all method of role was called.");

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Role> query = session.createQuery("FROM Role", Role.class);
            return query.getResultList();
        } catch (Exception e) {
            logger.error("Roles not find");
            throw new DataProcessingException("Can't get all roles", e);
        }
    }

    @Override
    public boolean save(Role role) {
        logger.info("Save method of role was called. Param: user = {}", role);

        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(role);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Roles not saved");
            throw new DataProcessingException("Can't insert role: " + role, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public boolean delete(Long id) {
        logger.info("Delete method of role was called. Param: id = {}", id);

        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.delete(id);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Roles not deleted");
            throw new DataProcessingException("Can't delete role with id: " + id, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
