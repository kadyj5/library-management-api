package pl.edu.wszib.library.management.api.dao.impl.hibernate;

import jakarta.persistence.NoResultException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.library.management.api.dao.impl.IBookDAO;
import pl.edu.wszib.library.management.api.model.Book;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@Component
@Slf4j
public class BookDAO implements IBookDAO {
    private final String GET_BY_ID = "FROM pl.edu.wszib.library.management.api.model.Book WHERE id = :id";
    private final String GET_ALL = "FROM pl.edu.wszib.library.management.api.model.Book";
    private final String GET_BY_PATTERN = "FROM pl.edu.wszib.library.management.api.model.Book WHERE author LIKE :pattern OR title LIKE :pattern OR isbn LIKE :pattern";

    @Autowired
    SessionFactory sessionFactory;

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
    public List<Book> getAll() {
        Session session = this.sessionFactory.openSession();
        Query<Book> query = session.createQuery(GET_ALL, Book.class);

        List<Book> resultList = query.getResultList();
        session.close();
        return resultList;
    }

    @Override
    public void delete(int id) {
        Session session = this.sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.remove(new Book(id));
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void update(Book book) {
        Session session = this.sessionFactory.openSession();
        try {
            session.beginTransaction();
            Book existingBook = session.get(Book.class, book.getId());
            if (Objects.nonNull(existingBook)) {
                existingBook.setAvailable(book.isAvailable());
                existingBook.setDateOfBorrow(book.getDateOfBorrow());
                existingBook.setExpectedDateOfReturn(book.getExpectedDateOfReturn());
                existingBook.setAuthor(book.getAuthor());
                existingBook.setTitle(book.getTitle());
                existingBook.setIsbn(book.getIsbn());

                log.info("Updating book with id {}.", book.getId());
                session.merge(existingBook);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            log.info("Book updated id {}.", book.getId());
            session.close();
        }
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

    @Override
    public void persist(Book book) {
        Session session = this.sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.persist(book);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }
}
