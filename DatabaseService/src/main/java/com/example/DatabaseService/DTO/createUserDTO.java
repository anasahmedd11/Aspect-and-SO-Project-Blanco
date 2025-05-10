package com.example.DatabaseService.DTO;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class createUserDTO {

    @NotBlank(message = "First Name can not be empty")
    @Size(min = 3, max = 10, message = "First name must be 3-10 characters")
    private String firstName;

    @NotBlank(message = "Last Name can not be empty")
    @Size(min = 3, max = 10, message = "Last name must be 3-10 characters")
    private String lastName;

    @Email(message = "Email format is invalid")
    @NotBlank(message = "Email can not be empty")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;


}
