package pl.edu.wszib.library.management.api.gui;

import pl.edu.wszib.library.management.api.model.Book;
import pl.edu.wszib.library.management.api.model.User;

import java.util.List;

public interface IGUI {
    User readLoginData(User user) ;
    String menu();
    void listBooks(List<Book> books);
    String readIsbn(Book book);

}
