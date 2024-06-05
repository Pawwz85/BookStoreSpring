package org.example.services;

import jakarta.transaction.Transactional;
import org.example.DAO.IBookDao;
import org.example.model.Book;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class BookService implements IBookService{

    @Autowired
    private final IBookDao bookDao;

    public BookService(IBookDao bookDao){
        this.bookDao = bookDao;
    }

    @Override
    @Transactional
    public void saveOrUpdate(Book book) {
        bookDao.saveOrUpdate(book);
    }

    @Override
    @Transactional
    public Optional<Book> getById(int id) {
        return bookDao.getById(id);
    }

    @Override
    @Transactional
    public List<Book> getAll() {
        return bookDao.getAll();
    }

    @Override
    @Transactional
    public void delete(int id) {
        bookDao.delete(id);
    }

}
