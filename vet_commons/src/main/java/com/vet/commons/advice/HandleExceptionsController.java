package com.vet.commons.advice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.reactive.resource.NoResourceFoundException;
import org.springframework.web.server.ServerWebInputException;

import com.vet.commons.exceptions.Exception;
import com.vet.commons.exceptions.MongoDuplicateKeyException;
import com.vet.commons.exceptions.NotFoundException;
import com.vet.commons.exceptions.ThrowableRestExeption;

import reactor.core.publisher.Mono;

@RestControllerAdvice
public class HandleExceptionsController {

  

  @ExceptionHandler({UsernameNotFoundException.class, BadCredentialsException.class})
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public Mono<Exception> handleUsernameNotFoundException(RuntimeException e) {
    return Mono.just(Exception.fromRuntimeException(e, HttpStatus.UNAUTHORIZED));
  }

  @ExceptionHandler(NoResourceFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public Mono<Exception> handleNoResourceFoundException(NoResourceFoundException e) {
    return Mono.just(Exception.fromRuntimeException(e, HttpStatus.NOT_FOUND));
  }

  @ExceptionHandler(ThrowableRestExeption.class)
  public Mono<ResponseEntity<Exception>> handleThrowableException(ThrowableRestExeption e) {
    Exception exceptionBody = e.getExceptionBody();
    return Mono.just(ResponseEntity.status(exceptionBody.getStatus()).body(exceptionBody));
  }

  @ExceptionHandler({DuplicateKeyException.class, MongoDuplicateKeyException.class})
  @ResponseStatus(HttpStatus.CONFLICT)
  public Mono<Exception> handleDuplicateKeyException(DuplicateKeyException e) {
    return Mono.just(Exception.fromRuntimeException(e, HttpStatus.CONFLICT));
  }

  @ExceptionHandler(NotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public Mono<Exception> handleNotFoundException(NotFoundException e) {
    return Mono.just(Exception.fromRuntimeException(e, HttpStatus.NOT_FOUND));
  }

  // @ExceptionHandler({BadCredentialsException.class, DisabledException.class})
  // @ResponseStatus(HttpStatus.UNAUTHORIZED)
  // public Mono<Exception> handleBadCredentialsException(RuntimeException e) {
  //   return Mono.just(Exception.fromRuntimeException(e, HttpStatus.UNAUTHORIZED));
  // }
  
  // @ExceptionHandler(ForbiddenException.class)
  // @ResponseStatus(HttpStatus.FORBIDDEN)
  // public Mono<Exception> handleForbiddenException(ForbiddenException e) {
  //   return Mono.just(Exception.fromRuntimeException(e, HttpStatus.FORBIDDEN));
  // }

  @ExceptionHandler(WebExchangeBindException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Mono<Exception> handleValidationExceptions(WebExchangeBindException ex) {
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> 
            errors.add("(" + error.getField() + ") "+ error.getDefaultMessage())
        );
        return Mono.just(Exception.builder()
            .message(errors)
            .error("ValidationException")
            .status(HttpStatus.BAD_REQUEST.value())
            .build());
    }
    @ExceptionHandler(ServerWebInputException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Mono<Exception> handleValidationExceptions(ServerWebInputException ex) {
        return Mono.just(Exception.builder()
            .message("Invalid Body (Check the respective schema)")
            .error("ValidationException")
            .status(HttpStatus.BAD_REQUEST.value())
            .build());
    }
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Mono<Exception> handleException(RuntimeException e) {
      return Mono.just(Exception.fromRuntimeException(e));
    }
}
