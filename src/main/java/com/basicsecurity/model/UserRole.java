package com.basicsecurity.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    private List<AppUser> users;

    public UserRole(String name) {
        this.name = name;
        this.users = new ArrayList<>();
    }
}
