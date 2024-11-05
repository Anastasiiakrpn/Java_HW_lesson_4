package org.anastasiiapanchenko.lesson5.repository;

import org.anastasiiapanchenko.lesson5.model.Role;
import org.anastasiiapanchenko.lesson5.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Optional;

import static com.opencsv.bean.util.OpencsvUtils.handleException;

public class RoleRepositoryImpl implements RoleRepository {
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public Role create(Role role) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(role);
            transaction.commit();
            return role;
        } catch (Exception e) {
            handleException(transaction, e);
            return null;
        }
    }

    @Override
    public Optional<Role> findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Role role = session.get(Role.class, id);
            return Optional.ofNullable(role);
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
