package pl.edu.wszib.library.management.api.service.impl;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.wszib.library.management.api.dao.impl.IBookDAO;
import pl.edu.wszib.library.management.api.dao.impl.IBorrowBookHistoryDAO;
import pl.edu.wszib.library.management.api.dao.impl.IClientDAO;
import pl.edu.wszib.library.management.api.model.Book;
import pl.edu.wszib.library.management.api.model.BorrowBookHistory;
import pl.edu.wszib.library.management.api.model.Client;
import pl.edu.wszib.library.management.api.service.ILibraryService;
import pl.edu.wszib.library.management.api.session.SessionObject;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Component
public class LibraryService implements ILibraryService {
    @Autowired
    IBookDAO bookDAO;
    @Autowired
    IBorrowBookHistoryDAO borrowBookHistoryDAO;
    @Autowired
    IClientDAO clientDAO;
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
    }

    @Override
    public void addBook(Book book) {
        book.setAvailable(true);
        book.setExpectedDateOfReturn(null);
        book.setDateOfBorrow(null);
        bookDAO.persist(book);
    }

    @Override
    public List<Book> getAll() {
        return this.bookDAO.getAll();
    }

    @Override
    public List<Book> searchByPhrase(String pattern) {

        return bookDAO.getByPattern(pattern);
    }

}
