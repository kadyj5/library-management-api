package pl.edu.wszib.library.management.api.service;

import pl.edu.wszib.library.management.api.model.Book;
import pl.edu.wszib.library.management.api.model.BorrowBookHistory;
import pl.edu.wszib.library.management.api.model.Client;

import java.util.List;

public interface IBookService {
    void borrowBook(int id, Client client);

    void returnBook(int id);

    void addBook(Book book);

    List<Book> getAll();

    List<Book> searchByPhrase(String pattern);

    List<BorrowBookHistory> getRentedBooks(String searchPhrase);

    List<BorrowBookHistory> getRentedBooksAfterDate(String searchPhrase);
}
