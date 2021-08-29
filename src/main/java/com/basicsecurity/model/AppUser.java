package com.basicsecurity.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotBlank(message = "Email is mandatory")
    @Email
    private String email;

    @NotBlank(message = "First Name is mandatory")
    private String firstName;

    @NotBlank(message = "Last Name is mandatory")
    private String lastName;

    @NotBlank(message = "Username is mandatory")
    private String username;

    @NotBlank(message = "Password is mandatory")
    private String password;

    private boolean enabled;

    @ManyToOne(cascade = CascadeType.ALL)
    private UserRole role;

    public AppUser(String email, String firstName,
                   String lastName, String username,
                   String password, boolean enabled, UserRole role) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.role = role;
    }
}
