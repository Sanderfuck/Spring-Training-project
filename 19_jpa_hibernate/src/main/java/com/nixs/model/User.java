package com.nixs.model;

//import jakarta.validation.constraints.Email;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.Past;
//import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Data
@Entity
@Table(name = "users")
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @NotBlank
    private String login;

//    @Size(min = 6, max = 30)
    private String password;

//    @Email
    private String email;
    private String firstName;
    private String lastName;

//    @Past
    private Date birthday;
    private Long roleId;

    public User() {
    }
}