package com.nixs.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Data
@AllArgsConstructor
public class User {
    private Long id;
    private String login;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private Date birthday;
    private Long roleId;

    public User() {
    }

    public User(Long id, String login, String password, Long roleId, Date birthday) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.roleId = roleId;
        this.birthday = birthday;
    }
}