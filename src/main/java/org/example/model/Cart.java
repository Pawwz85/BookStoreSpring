package org.example.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    private Long id;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<CartItem> items = new ArrayList<>();

    @OneToOne(mappedBy = "cart")
    @Setter
    private User user;

    public void addItem(Book book, int quantity) {
        CartItem item = items.stream()
                .filter(cartItem -> cartItem.getBook().getId() == book.getId())
                .findFirst().orElse(null);
        if (item == null){
            item = new CartItem();
            item.setCart(this);
            item.setQuantity(0);
            item.setBook(book);
            items.add(item);
        }
        item.setQuantity(quantity + item.getQuantity());
    }

    public void removeBookByID(int bookId) {
        CartItem item = items.stream()
                .filter(cartItem -> cartItem.getBook().getId() == bookId)
                .findFirst().orElse(null);
        if (item != null){
            if (item.getQuantity() == 1){
                items.remove(item);
            } else
                item.setQuantity(item.getQuantity() - 1);
        }
    }
}
