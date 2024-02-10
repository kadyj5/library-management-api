package pl.edu.wszib.library.management.api.dao.impl;

import pl.edu.wszib.library.management.api.model.BorrowBookHistory;

import java.util.List;
import java.util.Optional;

public interface IBorrowBookHistoryDAO {
    public Optional<BorrowBookHistory> getById(int id);

    public List<BorrowBookHistory> getAll();

    public void delete(int id);

    public void update(BorrowBookHistory borrowBookHistory);

    void persist(BorrowBookHistory borrowBookHistory);
}
