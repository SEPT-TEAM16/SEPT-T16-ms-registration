package com.rmit.sept.msregistration.exception;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.RollbackException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

@RestControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RollbackException.class)
    public ResponseEntity<ErrorModel> handleMissingParameter(RollbackException exception, HttpServletRequest request) {

        ErrorModel error = new ErrorModel(HttpStatus.BAD_REQUEST, "Request has failed due to invalid method argument, please check your json response body", exception.getLocalizedMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorModel> handleConstraintViolation(ConstraintViolationException exception, HttpServletRequest request) {

        ErrorModel error = new ErrorModel(HttpStatus.BAD_REQUEST, "Request has failed due to duplicate attribute, please check your json response body", exception.getLocalizedMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorModel> handleOtherExceptions(Exception exception, HttpServletRequest request) {

        ErrorModel error = new ErrorModel(HttpStatus.INTERNAL_SERVER_ERROR, "Request has failed due to internal error, please contact the admin", exception.getLocalizedMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserIdException.class)
    public ResponseEntity<ErrorModel> handleInvalidUserId(UserIdException exception, HttpServletRequest request) {

        ErrorModel error = new ErrorModel(HttpStatus.NOT_FOUND, "Request has failed due to invalid request, please check your json input", exception.getLocalizedMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
