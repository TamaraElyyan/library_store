package com.Ecommerce.LibraryStore.model.dao;

import com.Ecommerce.LibraryStore.model.Book;
import org.springframework.data.repository.ListCrudRepository;

public interface BookDAO extends ListCrudRepository<Book, Long> {
}
