package org.anastasiiapanchenko.lesson5.repository;

import org.anastasiiapanchenko.lesson5.model.User;
import org.anastasiiapanchenko.lesson5.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public User create(User user) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            return user;
        } catch (Exception e) {
           handleException(transaction, e);
           return null;
        }
    }

    @Override
    public User update(User user) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
            return user;
        } catch (Exception e) {
            handleException(transaction, e);
            return null;
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            User user = session.get(User.class, id);
            return Optional.ofNullable(user);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private void handleException(Transaction transaction, Exception e) {
        if (transaction != null) {
            transaction.rollback();
        }
    }
}


