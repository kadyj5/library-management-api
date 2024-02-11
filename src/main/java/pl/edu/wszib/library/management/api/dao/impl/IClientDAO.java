package pl.edu.wszib.library.management.api.dao.impl;

import pl.edu.wszib.library.management.api.model.Client;

import java.util.List;
import java.util.Optional;

public interface IClientDAO {
    Optional<Client> getClientById(int id);

    List<Client> getAll();

    public void delete(int id);

    void persist(Client client);
}
