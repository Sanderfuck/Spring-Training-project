package com.nixs.dao;

import com.nixs.exception.DataProcessingException;
import com.nixs.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Repository
public class HibernateUserDao implements HibernateDao<User> {
    private static final Logger logger = LogManager.getLogger(HibernateUserDao.class);
    private final SessionFactory sessionFactory;

    @Autowired
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
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);
            criteriaQuery.select(root).where(builder.equal(root.get("login"), name));

            Query<User> query = session.createQuery(criteriaQuery);
            User user = query.getSingleResult();
            return Optional.ofNullable(user);
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
        } catch (Exception e) {
            logger.error("Users not find");
            throw new DataProcessingException("Can't get all users", e);
        }
    }

    @Override
    @Transactional
    public boolean save(User user) {
        logger.info("Save method of user was called. Param: user = {}", user);

        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(user);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("User not saved");
            throw new DataProcessingException("Can't save user: " + user, e);
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        logger.info("Delete method of user was called. Param: id = {}", id);

        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            User reference = session.getReference(User.class, id);
            session.delete(reference);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("User not deleted");
            throw new DataProcessingException("Can't delete user with id: " + id, e);
        }
    }
}

