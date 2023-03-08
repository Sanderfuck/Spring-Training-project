package com.nixs.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
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
}
