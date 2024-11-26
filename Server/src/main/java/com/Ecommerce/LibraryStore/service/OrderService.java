package com.Ecommerce.LibraryStore.service;


import com.Ecommerce.LibraryStore.model.LocalUser;
import com.Ecommerce.LibraryStore.model.WebOrder;
import com.Ecommerce.LibraryStore.model.dao.WebOrederDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private WebOrederDAO webOrederDAO;

    public OrderService(WebOrederDAO webOrederDAO) {
        this.webOrederDAO = webOrederDAO;
    }


    public List<WebOrder> getOrders(LocalUser user) {
        return webOrederDAO.findByUser(user);
    }
}
