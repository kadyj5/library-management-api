package pl.edu.wszib.library.management.api.dao.impl;

import pl.edu.wszib.library.management.api.model.Book;

import java.util.List;
import java.util.Optional;

public interface IBookDAO {
    public Optional<Book> getById(int id);
    public List<Book> listAll();
    public void delete(int id);
    public void update(Book book);
    List<Book> getByPattern(String pattern);
}
