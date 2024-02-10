package pl.edu.wszib.library.management.api.dao.impl.hibernate;

import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.library.management.api.dao.impl.IBorrowBookHistoryDAO;
import pl.edu.wszib.library.management.api.model.BorrowBookHistory;

import java.util.List;
import java.util.Optional;
@Repository
@Component
public class BorrowBookHistoryDAO implements IBorrowBookHistoryDAO {
    private final String GET_BY_ID = "FROM pl.edu.wszib.library.management.api.model.BorrowBookHistory WHERE id = :id";
    private final String GET_ALL = "FROM pl.edu.wszib.library.management.api.model.BorrowBookHistory";

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Optional<BorrowBookHistory> getById(int id) {
        Session session = this.sessionFactory.openSession();

        Query<BorrowBookHistory> query = session
                .createQuery(GET_BY_ID,
                        BorrowBookHistory.class);
        query.setParameter("id", id);

        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        } finally {
            session.close();
        }
    }

    @Override
    public List<BorrowBookHistory> getAll() {
        Session session = this.sessionFactory.openSession();
        Query<BorrowBookHistory> query = session.createQuery(GET_ALL, BorrowBookHistory.class);

        List<BorrowBookHistory> resultList = query.getResultList();
        session.close();
        return resultList;
    }

    @Override
    public void delete(int id) {
        Session session = this.sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.remove(new BorrowBookHistory(id));
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void update(BorrowBookHistory borrowBookHistory) {
        Session session = this.sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.merge(borrowBookHistory);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void persist(BorrowBookHistory borrowBookHistory) {
        Session session = this.sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.persist(borrowBookHistory);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }
}
