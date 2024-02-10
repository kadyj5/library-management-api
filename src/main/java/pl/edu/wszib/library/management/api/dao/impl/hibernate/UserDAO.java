package pl.edu.wszib.library.management.api.dao.impl.hibernate;

import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.library.management.api.dao.impl.IUserDAO;
import pl.edu.wszib.library.management.api.model.User;

import java.util.List;
import java.util.Optional;

@Repository
@Component
public class UserDAO implements IUserDAO {
    private final String GET_BY_LOGIN = "FROM pl.edu.wszib.library.management.api.model.User WHERE login = :login";
    private final String GET_BY_ID = "FROM pl.edu.wszib.library.management.api.model.User WHERE id = :id";
    private final String GET_ALL = "FROM pl.edu.wszib.library.management.api.model.User";

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Optional<User> getUserById(int id) {
        Session session = this.sessionFactory.openSession();
        Query<User> query = session.createQuery(GET_BY_ID, User.class);
        query.setParameter("id", id);
        try {
            return Optional.of(query.getSingleResult());
        } catch (Exception e) {
            return Optional.empty();
        } finally {
            session.close();
        }
    }

    @Override
    public Optional<User> getUserByLogin(String login) {
        Session session = this.sessionFactory.openSession();
        Query<User> query = session.createQuery(GET_BY_LOGIN, User.class);
        query.setParameter("login", login);
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAll() {
        Session session = this.sessionFactory.openSession();
        Query<User> query = session.createQuery(GET_ALL, User.class);
        List<User> result = query.getResultList();
        session.close();
        return result;
    }

    @Override
    public void persist(User user) {
        Session session = this.sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.persist(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }
}
