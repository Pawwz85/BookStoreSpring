package org.example.controllers;

import org.example.model.Cart;
import org.example.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CartController {
    @Autowired
    private CartService cartService;
    @GetMapping("/cart/add/{bookId}/{quantity}")
    public String addToCart(@PathVariable int bookId, @PathVariable int quantity){
        cartService.addToCart(bookId, quantity);
        return  "redirect:/cart";
    }

    @GetMapping("/cart")
    public String getCart(Model model){
        Cart cart = cartService.getCart();
        model.addAttribute("cart", cart);
        return "cart";
    }

    @GetMapping("cart/remove/{bookId}")
    public String removeFromCart(@PathVariable int bookId){
        cartService.removeFromCard(bookId);
        return  "redirect:/cart";
    }
}
