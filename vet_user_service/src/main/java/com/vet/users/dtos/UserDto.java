package com.vet.users.dtos;

import com.vet.users.entities.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
  private Long id;

  private String username;

  private String names;

  private String surnames;

  
  private Role role;

  private Boolean enabled;
}
