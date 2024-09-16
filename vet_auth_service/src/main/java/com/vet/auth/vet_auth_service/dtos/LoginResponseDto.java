package com.vet.auth.vet_auth_service.dtos;
import com.vet.commons.dtos.res.UserDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDto {
  private UserDto user;
  private String token;
  private String refreshToken;
}
