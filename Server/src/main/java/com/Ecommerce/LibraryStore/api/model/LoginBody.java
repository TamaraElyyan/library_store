package com.Ecommerce.LibraryStore.api.model;

import jakarta.validation.constraints.NotBlank;

public class LoginBody {
    @NotBlank
    private String username;
    @NotBlank
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


}
