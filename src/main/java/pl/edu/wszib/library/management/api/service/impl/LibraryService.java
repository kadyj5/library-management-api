package pl.edu.wszib.library.management.api.service.impl;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.wszib.library.management.api.dao.impl.IBookDAO;
import pl.edu.wszib.library.management.api.model.Book;
import pl.edu.wszib.library.management.api.service.ILibraryService;
import pl.edu.wszib.library.management.api.session.SessionObject;

import java.util.List;
import java.util.Optional;

@Component
public class LibraryService implements ILibraryService {

    @Autowired
    IBookDAO bookDAO;
    @Resource
    SessionObject sessionObject;

    @Override
    public void borrowBook(int id) {
        Optional<Book> bookBox = this.bookDAO.getById(id);

    }

    @Override
    public void returnBook(int id) {

    }

    @Override
    public List<Book> getAll() {
        return this.bookDAO.getAll();
    }


}
