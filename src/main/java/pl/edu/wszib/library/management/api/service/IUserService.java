package pl.edu.wszib.library.management.api.service;

import pl.edu.wszib.library.management.api.model.Book;
import pl.edu.wszib.library.management.api.model.User;

import java.util.List;

public interface IUserService {

    List<User> getAll();
    void addUser(User user);
}
