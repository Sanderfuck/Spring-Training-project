package com.nixs.client;

import lombok.Data;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

@Data

public class UserDto {
    private Long id;

    private String login;

    private String password;

    private String email;

    private String firstName;

    private String lastName;

    private Date birthday;

    private String roleName;

    private Integer age;

    public Integer getAge() {
        if (age == null) {
            if (getBirthday() != null) {
                LocalDate localDate = getBirthday().toLocalDate();
                age = Period.between(localDate, LocalDate.now()).getYears();
            }
        }
        return age;
    }

    UserDto() {

    }

    UserDto(Long id,
            String login,
            String password,
            String email,
            String firstName,
            String lastName,
            Date birthday,
            String roleName) {

        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.roleName = roleName;
    }
}
