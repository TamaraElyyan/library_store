package com.Ecommerce.LibraryStore.service;
import com.Ecommerce.LibraryStore.dto.LoginBody;
import com.Ecommerce.LibraryStore.dto.RegistrationBody;
import com.Ecommerce.LibraryStore.exception.UserAlreadyExistsException;
import com.Ecommerce.LibraryStore.model.LocalUser;
import com.Ecommerce.LibraryStore.model.dao.LocalUserDAO;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService {

    private LocalUserDAO localUserDAO;
    private EncryptionService encryptionService;
    private JWTService jwtService;

    public UserService(LocalUserDAO localUserDAO, EncryptionService encryptionService, JWTService jwtService) {
        this.localUserDAO = localUserDAO;
        this.encryptionService = encryptionService;
        this.jwtService = jwtService;
    }



    public LocalUser registerUser( RegistrationBody registrationBody) throws UserAlreadyExistsException {

        if (localUserDAO.findByEmailIgnoreCase(registrationBody.getEmail()).isPresent()
                || localUserDAO.findByUsernameIgnoreCase(registrationBody.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException();

        }
        LocalUser user = new LocalUser();
        user.setEmail(registrationBody.getEmail());
        user.setUsername(registrationBody.getUsername());
        user.setFirstName(registrationBody.getFirstName());
        user.setLastName(registrationBody.getLastName());

        user.setPassword(encryptionService.encryptPassword(registrationBody.getPassword()));
        return localUserDAO.save(user);

    }

    public String loginUser(LoginBody loginBody)  {
        Optional<LocalUser> opUser = localUserDAO.findByUsernameIgnoreCase(loginBody.getUsername());
         if (opUser.isPresent()) {
             LocalUser user = opUser.get();
             if (encryptionService.verifyPassword(loginBody.getPassword(), user.getPassword())) {
                 return jwtService.generateJWT(user);
             }
         }
         return null;
    }
}
