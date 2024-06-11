package org.example.services;

import jakarta.transaction.Transactional;
import org.example.Exceptions.TooBigOrderExceptions;
import org.example.JPARepositories.OrderRepository;
import org.example.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @Autowired
    private BookService bookService;

    @Autowired
    private OrderRepository orderRepository;


    @Transactional(rollbackOn = {TooBigOrderExceptions.class})
    public Order submitOrder() throws TooBigOrderExceptions{
        Order order = new Order();
        order.setDate(new Date());
        User user = userService.getCurrentUser();
        order.setUser(user);
        order.setStatus(OrderStatus.SUBMITTED);
        for (CartItem item : user.getCart().getItems()){
            OrderItem orderItem = new OrderItem();
            orderItem.setBook(item.getBook());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setOrder(order);
            order.getItems().add(orderItem);

            Book book = item.getBook();
            if (book.getQuantity() < item.getQuantity())
                throw new TooBigOrderExceptions();
            book.setQuantity(book.getQuantity() - item.getQuantity());
        }
        user.getCart().getItems().clear();
        user.getOrders().add(order);
        cartService.saveCart(user.getCart());
        return orderRepository.save(order);
    }
    @Transactional
    public Order getOrder(long orderId){
        return orderRepository.findById(orderId).orElseThrow(() ->new RuntimeException("Order not found"));
    }

    @Transactional
    public List<Order> getAllOrdersOfUser(long userID)
    {
        List<Order> result = orderRepository.findAll();
        return result.stream().filter(order -> order.getUser().getId() == userID).collect(Collectors.toList());
    }

    @Transactional
    public List<Order> getAll(){
        return orderRepository.findAll();
    }

    @Transactional
    public void save(Order o){
        orderRepository.save(o);
    }
}
