package org.example.controllers;

import org.example.Exceptions.TooBigOrderExceptions;
import org.example.model.Order;
import org.example.model.OrderStatus;
import org.example.services.OrderService;
import org.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class orderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @PostMapping(path = "/order/submit")
    public String submitOrder(){
        Order order = null;
        try {
            order = orderService.submitOrder();
            return "redirect:/order/" + order.getId();
        } catch (TooBigOrderExceptions e) {
            return "redirect:/order/too_big";
        }

    }

    @GetMapping(path = "/order/{orderId}")
    public String getOrder(Model model, @PathVariable long orderId){
        Order ord = orderService.getOrder(orderId);
        model.addAttribute("order", ord);
        if(userService.getCurrentUser().getRoles().stream().anyMatch(role -> role.getName().equals("ADMIN")))
            return "order_admin.html";
        else
            return "order.html";
    }

    @GetMapping(path="/order/too_big")
    public String tooBigOrder(){
        return "error_too_big_order.html";
    }

    @GetMapping(path = "/orders/{userId}")
    public String getUserOrders(Model model, @PathVariable long userId){
            if (userId == -1)
                userId = userService.getCurrentUser().getId();

            model.addAttribute("orders", orderService.getAllOrdersOfUser(userId));
            return "orders.html";
    }

    @GetMapping(path = "/orders/all")
    public String getAllOrders(Model model){
        model.addAttribute("orders", orderService.getAll());
        return "orders.html";
    }

    @GetMapping(path = "/order/admin/{orderId}")
    public String getOrderAdmin(Model model, @PathVariable long orderId){
        Order ord = orderService.getOrder(orderId);
        model.addAttribute("order", ord);
        return "order_admin.html";
    }

    @PostMapping(path = "/order/admin/{orderId}")
    public String changeOrderStatus(Model model, @PathVariable long orderId, @RequestParam OrderStatus status){
        Order ord = orderService.getOrder(orderId);
        ord.setStatus(status);
        model.addAttribute("order", ord);
        return "order_admin.html";
    }
}
