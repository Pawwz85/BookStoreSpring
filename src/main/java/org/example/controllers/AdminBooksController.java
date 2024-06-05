package org.example.controllers;

import org.example.model.Book;
import org.example.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller()
public class AdminBooksController {
    @Autowired
    BookService bookService;

    @GetMapping(path = "/admin/add_book")
    public String get_add_book_form(Model model){
        model.addAttribute("book", new Book());
        return "book_add_form.html";
    }

    @PostMapping(path="/admin/add_book")
    public  String add(@ModelAttribute Book book){
        this.bookService.saveOrUpdate(book);
        return "redirect:/admin/adminpanel";
    }
}
