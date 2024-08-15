package com.vet.users.models;

import java.util.Date;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Error {
  public static Error fromRuntimeException(RuntimeException e) {
    return Error.fromRuntimeException(e, HttpStatus.INTERNAL_SERVER_ERROR);
  }
  public static Error fromRuntimeException(RuntimeException e, HttpStatus status) {
    return Error.builder()
      .message(e.getMessage())
      .error(e.getClass().getSimpleName())
      .status(status.value())
      .build();
  }

  private Object message;
  private String error;
  private Integer status;

  @Builder.Default
  private Date date = new Date();
}
