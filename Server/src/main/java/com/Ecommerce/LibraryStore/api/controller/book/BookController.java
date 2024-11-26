package com.Ecommerce.LibraryStore.api.controller.book;


import com.Ecommerce.LibraryStore.model.Book;
import com.Ecommerce.LibraryStore.service.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {


    private BookService bookService;

    @GetMapping
    public List<Book> getBooks() {
        return bookService.getBooks();
    }
}
