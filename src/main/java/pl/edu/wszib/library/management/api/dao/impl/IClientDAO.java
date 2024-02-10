package pl.edu.wszib.library.management.api.dao.impl;

import pl.edu.wszib.library.management.api.model.Client;
import pl.edu.wszib.library.management.api.model.User;

import java.util.List;
import java.util.Optional;

public interface IClientDAO {
    Optional<Client> getClientById(int id);

    List<Client> getAll();

    void persist(Client client);
}
