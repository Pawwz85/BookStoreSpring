package org.example.configuration;


import org.example.DAO.BookDAO;
import org.example.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("org.example")
@PropertySource("classpath:application.properties")
public class AppConfiguration {

    @Autowired
    private BookDAO bookDAO;
    private BookService bookService = new BookService(bookDAO);

    @Bean
    public BookService getBookService(){
        return bookService;
    }
}
