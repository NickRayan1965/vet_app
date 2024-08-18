package com.vet.commons.exceptions;
import java.util.Date;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Exception {
  public static Exception fromRuntimeException(RuntimeException e) {
    return Exception.fromRuntimeException(e, HttpStatus.INTERNAL_SERVER_ERROR);
  }
  public static Exception fromRuntimeException(RuntimeException e, HttpStatus status) {
    return Exception.builder()
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
