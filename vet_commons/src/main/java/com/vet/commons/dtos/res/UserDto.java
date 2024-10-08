package com.vet.commons.dtos.res;


import com.vet.commons.entities.pg.Role;

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
