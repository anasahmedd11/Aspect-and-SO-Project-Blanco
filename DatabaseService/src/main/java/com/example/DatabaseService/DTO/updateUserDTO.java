package com.example.DatabaseService.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class updateUserDTO {

    @Email(message = "Invalid email format")
    private String email;

    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    @Size(min = 3, max = 10, message = "First name must be 3-10 characters")
    private String firstName;

    @Size(min = 3, max = 10, message = "Last name must be 3-10 characters")
    private String lastName;
}
