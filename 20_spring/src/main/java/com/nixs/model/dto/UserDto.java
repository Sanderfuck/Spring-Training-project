package com.nixs.model.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

@Data
public class UserDto {
    private Long id;

    @NotBlank(message = "Login can`t be empty")
    private String login;

    private String password;

    @Email(message = "Email format not correct")
    private String email;

    @NotBlank(message = "First name can`t be empty")
    private String firstName;

    @NotBlank(message = "Last name can`t be empty")
    private String lastName;

    @Past
    private LocalDate birthdayParse;

    private String birthday;

    private String roleName;

    private Integer age;

    private LocalDate getBirthdayParse() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(getBirthday(), formatter);
    }

    public Integer getAge() {
        if (age == null) {
            if (getBirthday() != null) {
                age = Period.between(getBirthdayParse(), LocalDate.now()).getYears();
            }
        }
        return age;
    }
}
