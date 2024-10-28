package com.example.exe201.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RegisterDTO {
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String username;
    private String password;
}
