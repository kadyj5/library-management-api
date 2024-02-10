package pl.edu.wszib.library.management.api.service;

import pl.edu.wszib.library.management.api.model.Book;
import pl.edu.wszib.library.management.api.model.Client;

import java.util.List;

public interface ILibraryService {
    void borrowBook(int id , Client client);

    void returnBook(int id);

    void addBook(Book book);
    void removeBook(int id);

    List<Book> getAll();

}
