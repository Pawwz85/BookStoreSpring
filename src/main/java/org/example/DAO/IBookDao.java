package org.example.DAO;

import org.example.model.Book;

import java.util.List;
import java.util.Optional;

public interface IBookDao {
    void saveOrUpdate(Book book);
    Optional<Book> getById(int id);
    List<Book> getAll();
    void delete(int id);
}
