package com.example.APIManagerService.DTO.Authentication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterResponseDTO {
    Long id;
    String email;
    String password;
    String firstName;
    String lastName;
}
