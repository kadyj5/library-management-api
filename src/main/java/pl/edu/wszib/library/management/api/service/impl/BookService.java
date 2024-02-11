package pl.edu.wszib.library.management.api.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import pl.edu.wszib.library.management.api.dao.impl.IBookDAO;
import pl.edu.wszib.library.management.api.dao.impl.IBorrowBookHistoryDAO;
import pl.edu.wszib.library.management.api.dao.impl.IClientDAO;
import pl.edu.wszib.library.management.api.model.Book;
import pl.edu.wszib.library.management.api.model.BorrowBookHistory;
import pl.edu.wszib.library.management.api.model.Client;
import pl.edu.wszib.library.management.api.service.IBookService;
import pl.edu.wszib.library.management.api.session.SessionObject;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class BookService implements IBookService {
    @Autowired
    IBookDAO bookDAO;
    @Autowired
    IBorrowBookHistoryDAO borrowBookHistoryDAO;
    @Autowired
    IClientDAO clientDAO;
    @Autowired
    ObjectMapper objectMapper;
    @Resource
    SessionObject sessionObject;

    @Override
    public void borrowBook(int id, Client client) {
        Optional<Book> bookBox = this.bookDAO.getById(id);
        clientDAO.persist(client);
        Calendar calendar = Calendar.getInstance();
        BorrowBookHistory borrowBookHistory = BorrowBookHistory.builder()
                .book(bookBox.orElse(null))
                .client(client)
                .build();
        borrowBookHistoryDAO.persist(borrowBookHistory);

        bookBox.get().setDateOfBorrow(calendar.getTime());
        calendar.add(Calendar.DAY_OF_MONTH, 14);
        bookBox.get().setExpectedDateOfReturn(calendar.getTime());
        bookBox.get().setAvailable(false);

        bookDAO.update(bookBox.get());
    }

    @Override
    public void returnBook(int id) {
        Optional<Book> bookBox = this.bookDAO.getById(id);
        bookBox.get().setDateOfBorrow(null);
        bookBox.get().setExpectedDateOfReturn(null);
        bookBox.get().setAvailable(true);

        bookDAO.update(bookBox.get());
        borrowBookHistoryDAO.delete(borrowBookHistoryDAO.getAll().stream()
                .filter(borrowBookHistory -> (borrowBookHistory.getBook().getId() == id))
                .findFirst()
                .get()
                .getId());
    }

    @Override
    public void addBook(Book book) {
        book.setAvailable(true);
        book.setExpectedDateOfReturn(null);
        book.setDateOfBorrow(null);
        bookDAO.persist(Book.builder()
                .title(book.getTitle())
                .author(book.getAuthor())
                .isbn(book.getIsbn())
                .available(true)
                .expectedDateOfReturn(null)
                .dateOfBorrow(null)
                .build());
    }

    @Override
    public List<Book> getAll() {
        return this.bookDAO.getAll();
    }

    @Override
    public List<Book> searchByPhrase(String pattern) {

        return bookDAO.getByPattern(pattern);
    }

    @Override
    public List<BorrowBookHistory> getRentedBooks(String searchPhrase) {
        List<Book> matchingBooks;

        if (Objects.nonNull(searchPhrase)) {
            matchingBooks = this.bookDAO.getByPattern(searchPhrase);
        } else {
            matchingBooks = this.bookDAO.getAll();
        }

        return borrowBookHistoryDAO.getAll().stream()
                .filter(borrowHistory -> !borrowHistory.getBook().isAvailable())
                .filter(borrowHistory -> matchingBooks.contains(borrowHistory.getBook()))
                .toList();
    }

    @Override
    public List<BorrowBookHistory> getRentedBooksAfterDate(String searchPhrase) {
        List<Book> matchingBooks;

        if (Objects.nonNull(searchPhrase)) {
            matchingBooks = this.bookDAO.getByPattern(searchPhrase);
        } else {
            matchingBooks = this.bookDAO.getAll();
        }

        return borrowBookHistoryDAO.getAll().stream()
                .filter(borrowHistory -> !borrowHistory.getBook().isAvailable())
                .filter(borrowHistory -> borrowHistory.getBook().getExpectedDateOfReturn().before(Calendar.getInstance().getTime()))
                .filter(borrowHistory -> matchingBooks.contains(borrowHistory.getBook()))
                .toList();
    }

    @Override
    public List<Book> defaultInitialize() throws Exception {
        ClassPathResource resource = new ClassPathResource("jsons/books.json");
        List<Book> books = objectMapper.readValue(resource.getInputStream(), new TypeReference<List<Book>>() {
        });
        books.forEach(this::addBook);
        return books;
    }
}
