package com.vet.users.controllers;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.resource.NoResourceFoundException;

import reactor.core.publisher.Mono;
import com.vet.users.models.Error;
import com.vet.users.models.NotFoundException;

@RestControllerAdvice
public class HandleExceptionsController {

  @ExceptionHandler(RuntimeException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public Mono<Error> handleException(RuntimeException e) {
    return Mono.just(Error.fromRuntimeException(e));
  }

  @ExceptionHandler(UsernameNotFoundException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public Mono<Error> handleUsernameNotFoundException(UsernameNotFoundException e) {
    return Mono.just(Error.fromRuntimeException(e, HttpStatus.UNAUTHORIZED));
  }

  @ExceptionHandler(NoResourceFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public Mono<Error> handleNoResourceFoundException(NoResourceFoundException e) {
    return Mono.just(Error.fromRuntimeException(e, HttpStatus.NOT_FOUND));
  }

  @ExceptionHandler(DuplicateKeyException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  public Mono<Error> handleDuplicateKeyException(DuplicateKeyException e) {
    return Mono.just(Error.fromRuntimeException(e, HttpStatus.CONFLICT));
  }

  @ExceptionHandler(NotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public Mono<Error> handleNotFoundException(NotFoundException e) {
    return Mono.just(Error.fromRuntimeException(e, HttpStatus.NOT_FOUND));
  }

  @ExceptionHandler({BadCredentialsException.class, DisabledException.class})
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public Mono<Error> handleBadCredentialsException(RuntimeException e) {
    return Mono.just(Error.fromRuntimeException(e, HttpStatus.UNAUTHORIZED));
  }
}
