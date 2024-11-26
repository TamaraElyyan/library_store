package com.Ecommerce.LibraryStore.service;


import com.Ecommerce.LibraryStore.model.Book;
import com.Ecommerce.LibraryStore.model.dao.BookDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private BookDAO bookDAO;
    public BookService(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    public List<Book> getBooks() {
        return bookDAO.findAll();

    }

}
