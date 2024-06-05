package org.example.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.example.model.Book;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BookDAO implements IBookDao{
    @PersistenceContext
    private EntityManager entityManager;
    private final String GET_ALL_JPQL = "FROM org.example.model.Book";

    public BookDAO(EntityManager entityManager){
        this.entityManager = entityManager;
    }


    @Override
    public void saveOrUpdate(Book book) {
        if (getById(book.getId()).isEmpty())
            entityManager.persist(book);
        else
            entityManager.merge(book);
    }

    @Override
    public Optional<Book> getById(int id) {
        Book result = entityManager.find(Book.class, id);
        return Optional.ofNullable(result);
    }


    @Override
    public List<Book> getAll() {
        TypedQuery<Book> query = entityManager.createQuery(GET_ALL_JPQL, Book.class);
        return query.getResultList();
    }

    @Override
    public void delete(int id) {
        Book toDelete = entityManager.find(Book.class, id);
        if (toDelete != null)
            entityManager.remove(toDelete);
    }
}
