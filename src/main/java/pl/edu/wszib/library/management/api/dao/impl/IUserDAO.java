package pl.edu.wszib.library.management.api.dao.impl;

import pl.edu.wszib.library.management.api.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserDAO {
    Optional<User> getUserById(int id);

    Optional<User> getUserByLogin(String login);

    List<User> getAll();

    void persist(User user);
}
