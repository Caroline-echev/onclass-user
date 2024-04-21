package com.onclass.user.configuration.exceptionhandler;



import com.onclass.user.configuration.Constants;
import com.onclass.user.domain.exception.DocumentAlreadyExistsException;
import com.onclass.user.domain.exception.EmailAlreadyExistsException;
import com.onclass.user.domain.exception.NoDataFoundException;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
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


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionCodeResponse> handlerArgumentInvalidException(MethodArgumentNotValidException exception) {

        FieldError firstFieldError = exception.getFieldErrors().get(0);
        return ResponseEntity.badRequest().body(new ExceptionCodeResponse(firstFieldError.getDefaultMessage(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(), LocalDateTime.now(), HttpStatus.BAD_REQUEST.value()));
    }
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionCodeResponse> handleBadCredentialsException(BadCredentialsException exception) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ExceptionCodeResponse(exception.getMessage(),
                HttpStatus.UNAUTHORIZED.getReasonPhrase(), LocalDateTime.now(), HttpStatus.UNAUTHORIZED.value()));
    }
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ExceptionCodeResponse> handleUsernameNotFoundException(UsernameNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionCodeResponse(
                exception.getMessage(), HttpStatus.NOT_FOUND.getReasonPhrase(), LocalDateTime.now(), HttpStatus.NOT_FOUND.value()));
    }


    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ExceptionCodeResponse> handleAccessDeniedException(AccessDeniedException exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ExceptionCodeResponse(
                exception.getMessage(), HttpStatus.FORBIDDEN.getReasonPhrase(), LocalDateTime.now(), HttpStatus.FORBIDDEN.value()));
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ExceptionCodeResponse> handleExpiredJwtException(ExpiredJwtException exception) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ExceptionCodeResponse(
                exception.getMessage(), HttpStatus.UNAUTHORIZED.getReasonPhrase(), LocalDateTime.now(), HttpStatus.UNAUTHORIZED.value()));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ExceptionCodeResponse> handleAuthenticationException(AuthenticationException exception) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ExceptionCodeResponse(
                exception.getMessage(), HttpStatus.UNAUTHORIZED.getReasonPhrase(), LocalDateTime.now(), HttpStatus.UNAUTHORIZED.value()));
    }
}
