package com.nixs.dao;

import com.nixs.exception.DataProcessingException;
import com.nixs.model.Role;
import com.nixs.model.User;
import com.nixs.util.HibernateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Optional;

public class HibernateUserDao implements HibernateDao<User> {
    private static final Logger logger = LogManager.getLogger(HibernateUserDao.class);
    private SessionFactory sessionFactory;

    public HibernateUserDao() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public HibernateUserDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<User> findById(Long id) {
        logger.info("Find By id method of user was called. Param: id = {}", id);

        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(User.class, id));
        } catch (Exception e) {
            logger.error("User not find by ID");
            throw new DataProcessingException("Can't get user by id: " + id, e);
        }
    }

    @Override
    public Optional<User> findByName(String name) {
        logger.info("Find by name method of user was called. Param: name = {}", name);

        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(User.class, name));
        } catch (Exception e) {
            logger.error("User not find by name");
            throw new DataProcessingException("Can't get user by name: " + name, e);
        }
    }

    @Override
    public List<User> findAll() {
        logger.info("Find all method of user was called.");

        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery("FROM User", User.class);
            return query.getResultList();
//        try (Session session = sessionFactory.openSession()) {
//            CriteriaQuery<User> criteriaQuery = session.getCriteriaBuilder()
//                    .createQuery(User.class);
//            criteriaQuery.from(User.class);
//            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            logger.error("Users not find");
            throw new DataProcessingException("Can't get all users", e);
        }
    }

    @Override
    public boolean save(User user) {
        logger.info("Save method of user was called. Param: user = {}", user);

        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
//            transaction = session.beginTransaction();
            session.saveOrUpdate(user);
//            transaction.commit();
            return true;
        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
            logger.error("User not saved");
            throw new DataProcessingException("Can't save user: " + user, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public boolean delete(Long id) {
        logger.info("Delete method of user was called. Param: id = {}", id);

        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
//            transaction = session.beginTransaction();
            session.delete(id);
//            transaction.commit();
            return true;
        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
            logger.error("User not deleted");
            throw new DataProcessingException("Can't delete user with id: " + id, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}

