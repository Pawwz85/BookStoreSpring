package org.example.controllers;

import org.example.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.example.model.Book;

@Controller
public class BooksController {

    @Autowired
    private BookService bookService;

    @GetMapping(path = "/books")
    public String get_books(Model model){
        model.addAttribute("books", bookService.getAll());
        return "books_panel";
    }

}
