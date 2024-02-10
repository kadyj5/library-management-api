package pl.edu.wszib.library.management.api.gui;

import org.springframework.stereotype.Component;
import pl.edu.wszib.library.management.api.model.Book;
import pl.edu.wszib.library.management.api.model.User;

import java.util.List;

@Component
public class GUI implements IGUI{

    @Override
    public User readLoginData(User user) {
        return null;
    }

    @Override
    public String menu() {
        return null;
    }

    @Override
    public void listBooks(List<Book> books) {
        books.forEach(System.out::println);
    }

    @Override
    public String readIsbn(Book book) {
        return book.getIsbn();
    }
}
