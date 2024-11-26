package com.Ecommerce.LibraryStore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.Ecommerce.LibraryStore.model.enums.Role;

@Entity
@Table(name = "local_user")
public class LocalUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @JsonIgnore
    @Column(name = "password", nullable = false, length = 1000)
    private String password;

    @Column(name = "email", nullable = false, unique = true, length = 320)
    private String email;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    // Use Enum for role
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, columnDefinition = "VARCHAR(20) DEFAULT 'CUSTOMER'")
    private Role role = Role.CUSTOMER;  // Default role

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Address> addresses = new ArrayList<>();

    // New fields for timestamps
//    @Column(name = "created_at", nullable = false, updatable = false)
//    private LocalDateTime createdAt;
//
//    @Column(name = "updated_at", nullable = false)
//    private LocalDateTime updatedAt;
//
//    @Column(name = "last_login")
//    private LocalDateTime lastLogin;

    // Lifecycle methods to automatically set timestamps
//    @PrePersist
//    public void prePersist() {
//        LocalDateTime now = LocalDateTime.now();
//        createdAt = now;
//        updatedAt = now;
//    }
//
//    @PreUpdate
//    public void preUpdate() {
//        updatedAt = LocalDateTime.now();
//    }
//
//    // Getter and setter for new fields
//    public LocalDateTime getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(LocalDateTime createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    public LocalDateTime getUpdatedAt() {
//        return updatedAt;
//    }
//
//    public void setUpdatedAt(LocalDateTime updatedAt) {
//        this.updatedAt = updatedAt;
//    }
//
//    public LocalDateTime getLastLogin() {
//        return lastLogin;
//    }
//
//    public void setLastLogin(LocalDateTime lastLogin) {
//        this.lastLogin = lastLogin;
//    }

    // Getter and setter for role
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    // Other getters and setters
    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
