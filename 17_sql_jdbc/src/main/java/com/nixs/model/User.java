package com.nixs.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class User {
    private Long id;
    private String login;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private Long role_id;
}
