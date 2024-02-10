package pl.edu.wszib.library.management.api.service;

import pl.edu.wszib.library.management.api.model.Book;

import java.util.List;

public interface ILibraryService {
    void borrowBook(int id);

    void returnBook(int id);

    List<Book> getAll();

}
