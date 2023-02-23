package com.nixs.model.dto;

import com.nixs.model.User;
import lombok.Getter;

import java.time.LocalDate;
import java.time.Period;

public class UserDto extends User {

    private Integer age;

    @Getter
    private String roleName;

    public UserDto(User user, String roleName) {
        super(user.getId(),
                user.getLogin(),
                user.getPassword(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthday(),
                user.getRoleId());
        this.roleName = roleName;
    }

    public Integer getAge() {
        if (age == null) {
            LocalDate localDate = getBirthday().toLocalDate();
            age = Period.between(localDate, LocalDate.now()).getYears();
        }
        return age;
    }
}
