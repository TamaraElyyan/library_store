package com.Ecommerce.LibraryStore.controller.auth;

import com.Ecommerce.LibraryStore.dto.LoginBody;
import com.Ecommerce.LibraryStore.dto.LoginResponse;
import com.Ecommerce.LibraryStore.dto.RegistrationBody;
import com.Ecommerce.LibraryStore.exception.UserAlreadyExistsException;
import com.Ecommerce.LibraryStore.model.LocalUser;
import com.Ecommerce.LibraryStore.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private UserService userService;

    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }



    @PostMapping("/register")
    public ResponseEntity registerUser(@Valid @RequestBody RegistrationBody registrationBody) {
        try {
            userService.registerUser(registrationBody);
            return ResponseEntity.ok().build();
        } catch (UserAlreadyExistsException ex) {
       return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PostMapping ("/login")
    public ResponseEntity <LoginResponse> loginUser(@Valid @RequestBody LoginBody loginBody) {
        String jwt = userService.loginUser(loginBody);
        if (jwt == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }else {
            LoginResponse response = new LoginResponse();
            response.setJwt(jwt);
            return ResponseEntity.ok(response);
        }
    }

    @GetMapping ("/me")
    public  LocalUser getLoggedUserProfile(@AuthenticationPrincipal LocalUser user) {
        return user;
    }
}