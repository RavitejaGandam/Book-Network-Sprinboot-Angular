package com.ravi.book_network.handler;

import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashSet;
import java.util.Set;

import static com.ravi.book_network.handler.BusinessErrorCodes.*;
import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(LockedException.class)
    public ResponseEntity<ExceptionResponse> handleExcpetion(LockedException exp){
      return ResponseEntity.status(UNAUTHORIZED)
              .body(
                      ExceptionResponse
                              .builder()
                              .businessErrorCodes(ACCOUNT_LOCKED.getCode())
                              .businessErrorDescription(ACCOUNT_LOCKED.getDescription())
                              .error(exp.getMessage())
                              .build()
              );
    }

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<ExceptionResponse> handleExcpetion(DisabledException exp){
        return ResponseEntity.status(UNAUTHORIZED)
                .body(
                        ExceptionResponse
                                .builder()
                                .businessErrorCodes(ACCOUNT_DISABLED.getCode())
                                .businessErrorDescription(ACCOUNT_DISABLED.getDescription())
                                .error(exp.getMessage())
                                .build()
                );
    }
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionResponse> handleException() {
        return ResponseEntity
                .status(UNAUTHORIZED)
                .body(
                        ExceptionResponse.builder()
                                .businessErrorCodes(BAD_CREDENTIALS.getCode())
                                .businessErrorDescription(BAD_CREDENTIALS.getDescription())
                                .error("Login and / or Password is incorrect")
                                .build()
                );
    }
    @ExceptionHandler(MessagingException.class)
    public ResponseEntity<ExceptionResponse> handleException(MessagingException exp) {
        return ResponseEntity
                .status(INTERNAL_SERVER_ERROR)
                .body(
                        ExceptionResponse.builder()
                                .error(exp.getMessage())
                                .build()
                );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleExcpetion(MethodArgumentNotValidException exp){
        Set<String> errors = new HashSet<>();
        exp.getBindingResult().getAllErrors()
                .forEach(error->{
                    var errorMessage = error.getDefaultMessage();
                    errors.add(errorMessage);

                });
        return ResponseEntity.status(BAD_REQUEST)
                .body(
                        ExceptionResponse
                                .builder()
                                .validationError(errors)
                                .build()
                );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleExcpetion(Exception exp){
        exp.printStackTrace();
        return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                .body(
                        ExceptionResponse
                                .builder()
                                .businessErrorDescription("Internal server error")
                                .error(exp.getMessage())
                                .build()
                );
    }
}
