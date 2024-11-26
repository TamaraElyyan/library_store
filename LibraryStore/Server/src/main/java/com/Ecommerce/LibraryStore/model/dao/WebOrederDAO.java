package com.Ecommerce.LibraryStore.model.dao;

import com.Ecommerce.LibraryStore.model.LocalUser;
import com.Ecommerce.LibraryStore.model.WebOrder;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface WebOrederDAO extends ListCrudRepository <WebOrder, Long> {
    List<WebOrder> findByUser(LocalUser user);
}
