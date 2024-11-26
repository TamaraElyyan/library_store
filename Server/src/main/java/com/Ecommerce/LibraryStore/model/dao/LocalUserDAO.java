package com.Ecommerce.LibraryStore.model.dao;


import com.Ecommerce.LibraryStore.model.LocalUser;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocalUserDAO extends ListCrudRepository<LocalUser, Long> {

    Optional<LocalUser> findByUsernameIgnoreCase(String username);

    Optional<LocalUser> findByEmailIgnoreCase(String email);
}
