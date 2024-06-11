package org.example.services;

import jakarta.transaction.Transactional;
import org.example.DAO.IBookDao;
import org.example.JPARepositories.CartRepository;
import org.example.model.Cart;
import org.example.model.User;
import org.example.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




@Service
public class CartService {
    @Autowired
    private IBookDao bookRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public Cart getCart(){
      User user = userService.getCurrentUser();
      return user.getCart();
    }
    @Transactional
    public void addToCart(int bookId, int quantity){
        Cart cart = getCart();
        Book book = bookRepository.getById(bookId).orElseThrow(
                () -> new RuntimeException("Book not found")
        );
        cart.addItem(book, quantity);
        saveCart(cart);
    }
    @Transactional
    public void removeFromCard(int bookID){
        Cart cart = getCart();
        cart.removeBookByID(bookID);
        saveCart(cart);
    }

    @Transactional
    public void saveCart(Cart cart){
        cartRepository.save(cart);
    }
}
