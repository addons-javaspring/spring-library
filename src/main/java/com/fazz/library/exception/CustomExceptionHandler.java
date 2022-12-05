package com.fazz.library.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fazz.library.exception.custom.NotFoundException;
import com.fazz.library.exception.custom.NotProcessException;
import com.fazz.library.model.dto.response.ResponseError;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class CustomExceptionHandler {
  private ResponseError responseError;

  @ExceptionHandler(value = Exception.class)
  public ResponseEntity<ResponseError> handleException(Exception e) {
    log.warn(e.getMessage());
    responseError = new ResponseError(500, LocalDateTime.now(), e.getMessage(), null);
    return ResponseEntity.internalServerError().body(responseError);
  }

  @ExceptionHandler(value = MethodArgumentNotValidException.class)
  public ResponseEntity<ResponseError> handleValidation(MethodArgumentNotValidException e) {
    log.warn(e.getMessage());
    Map<String, Object> mapError = new HashMap<>();
    e.getFieldErrors().forEach(error -> mapError.put(error.getField(), error.getDefaultMessage()));
    responseError = new ResponseError(400, LocalDateTime.now(), "Error validation", mapError);
    ;
    return ResponseEntity.badRequest().body(responseError);
  }

  @ExceptionHandler(value = NotFoundException.class)
  public ResponseEntity<ResponseError> handleNotFound(NotFoundException e) {
    log.warn(e.getMessage());
    responseError = new ResponseError(HttpStatus.NOT_FOUND.value(), LocalDateTime.now(), e.getMessage(), null);
    return ResponseEntity.status(responseError.getStatus()).body(responseError);
  }

  @ExceptionHandler(value = NotProcessException.class)
  public ResponseEntity<ResponseError> handleNotFound(NotProcessException e) {
    log.warn(e.getMessage());
    responseError = new ResponseError(HttpStatus.UNPROCESSABLE_ENTITY.value(), LocalDateTime.now(), e.getMessage(),
        null);
    return ResponseEntity.status(responseError.getStatus()).body(responseError);
  }
}
