package com.vet.auth_common.dtos;

import com.vet.commons.entities.pg.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDto {
    private String username;
    private String password;
    private String names;
    private String surnames;
    private Role role;
}
