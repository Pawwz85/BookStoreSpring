package org.example.controllers;

import java.util.Optional;
import org.example.model.Book;
import org.example.services.BookService;
import org.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class BooksController {

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @GetMapping(path = "/books")
    public String get_books(Model model){
        if(userService.getCurrentUser().getRoles().stream().anyMatch(role -> role.getName().equals("ADMIN"))) {
            model.addAttribute("books", bookService.getAll());
            return "books_panel_admin";
        }
        else{
            model.addAttribute("books", bookService.getAll());
            return "books_panel";
        }

    }

    @GetMapping(path = "/books/admin/hide/{bookId}")
    public String hide_book(Model model, @PathVariable int bookId){
       Optional<Book> book = bookService.getById(bookId);
        if(book.isPresent()){
            book.ifPresent(value -> value.setHidden(true));
            bookService.saveOrUpdate(book.get());
        }
        return  "redirect:/books";
    }

    @GetMapping(path = "/books/admin/reveal/{bookId}")
    public String reveal_book(Model model, @PathVariable int bookId){
        Optional<Book> book = bookService.getById(bookId);
        if(book.isPresent()){
            book.ifPresent(value -> value.setHidden(false));
            bookService.saveOrUpdate(book.get());
        }
        return  "redirect:/books";
    }
}
