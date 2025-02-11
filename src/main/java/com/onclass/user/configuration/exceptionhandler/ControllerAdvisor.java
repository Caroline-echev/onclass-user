package com.onclass.user.configuration.exceptionhandler;



import com.onclass.user.configuration.Constants;
import com.onclass.user.configuration.exception.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@RequiredArgsConstructor
public class ControllerAdvisor {

    @ExceptionHandler(DocumentAlreadyExistsException.class)
    public ResponseEntity<ExceptionCodeResponse> handleDocAlreadyExistsException() {

        return ResponseEntity.badRequest().body(new ExceptionCodeResponse(Constants.USER_DOCUMENT_ALREADY_EXISTS_EXCEPTION_MESSAGE,
                HttpStatus.BAD_REQUEST.getReasonPhrase(), LocalDateTime.now(), HttpStatus.BAD_REQUEST.value()));

    }
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ExceptionCodeResponse> handleEmailAlreadyExistsException() {

        return ResponseEntity.badRequest().body(new ExceptionCodeResponse(Constants.USER_EMAIL_ALREADY_EXISTS_EXCEPTION_MESSAGE,
                HttpStatus.BAD_REQUEST.getReasonPhrase(), LocalDateTime.now(), HttpStatus.BAD_REQUEST.value()));

    }
    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<ExceptionCodeResponse> handleNoDataFoundException() {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionCodeResponse(
                Constants.NO_DATA_FOUND_EXCEPTION_MESSAGE,HttpStatus.BAD_REQUEST.getReasonPhrase(), LocalDateTime.now(), HttpStatus.BAD_REQUEST.value()));

    }
    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<ExceptionCodeResponse> handleInvalidPasswordException() {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionCodeResponse(
                Constants.INVALID_PASSWORD_EXCEPTION_MESSAGE,HttpStatus.BAD_REQUEST.getReasonPhrase(), LocalDateTime.now(), HttpStatus.BAD_REQUEST.value()));

    }
    @ExceptionHandler(AdminAlreadyExistsException.class)
    public ResponseEntity<ExceptionCodeResponse> handleAdminAlreadyExistsException() {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionCodeResponse(
                Constants.ADMIN_ALREADY_EXISTS_EXCEPTION_MESSAGE ,HttpStatus.BAD_REQUEST.getReasonPhrase(), LocalDateTime.now(), HttpStatus.BAD_REQUEST.value()));

    }



    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionCodeResponse> handlerArgumentInvalidException(MethodArgumentNotValidException exception) {

        FieldError firstFieldError = exception.getFieldErrors().get(0);
        return ResponseEntity.badRequest().body(new ExceptionCodeResponse(firstFieldError.getDefaultMessage(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(), LocalDateTime.now(), HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ExceptionCodeResponse> handleUsernameNotFoundException(UsernameNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionCodeResponse(
                Constants.USER_NOT_FOUND_EMAIL_EXCEPTION_MESSAGE,
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value()));
    }
}
