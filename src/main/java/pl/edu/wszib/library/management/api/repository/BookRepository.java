package pl.edu.wszib.library.management.api.repository;


import lombok.extern.slf4j.Slf4j;
import pl.edu.wszib.library.management.api.exception.BookNotFoundException;
import pl.edu.wszib.library.management.api.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class BookRepository {
    private List<Book> books = new ArrayList<>();

    public void borrowBook(String isbn) {
        Optional<Book> book = this.books.stream()
                .filter(copy ->  copy.getIsbn().equals(isbn))
                .findAny();

        if (book.isEmpty()) {
            // TODO: Add log information
            throw new BookNotFoundException();
        }

    }
}
