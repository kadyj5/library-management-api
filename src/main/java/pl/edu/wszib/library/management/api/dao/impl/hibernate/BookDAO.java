package pl.edu.wszib.library.management.api.dao.impl.hibernate;

import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.library.management.api.dao.impl.IBookDAO;
import pl.edu.wszib.library.management.api.model.Book;

import java.util.List;
import java.util.Optional;

@Repository
@Component
public class BookDAO implements IBookDAO {

    private final String GET_BY_ID = "FROM pl.edu.wszib.library.management.api.model.Book WHERE id = :id";
    private final String GET_ALL = "FROM pl.edu.wszib.library.management.api.model.Book";
    private final String GET_BY_PATTERN = "FROM pl.edu.wszib.library.management.api.model.Book WHERE author LIKE :pattern OR title LIKE :pattern";

    final SessionFactory sessionFactory;

    public BookDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<Book> getById(int id) {
        Session session = this.sessionFactory.openSession();


        Query<Book> query = session
                .createQuery(GET_BY_ID,
                        Book.class);
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
    public List<Book> listAll() {
        Session session = this.sessionFactory.openSession();
        Query<Book> query = session.createQuery(GET_ALL, Book.class);

        List<Book> resultList = query.getResultList();
        session.close();
        return resultList;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(Book book) {

    }

    @Override
    public List<Book> getByPattern(String pattern) {
        Session session = this.sessionFactory.openSession();
        Query<Book> query = session.createQuery(GET_BY_PATTERN, Book.class);
        query.setParameter("pattern", "%" + pattern + "%");

        List<Book> resultList = query.getResultList();
        session.close();
        return resultList;
    }

}
