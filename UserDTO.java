package com.example.as4.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private int age;
}

