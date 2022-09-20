package com.rmit.sept.msregistration.exception;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ErrorModel {

    private LocalDateTime timeStamp;
    private HttpStatus errorId;
    private String errorDetails;
    private String exception;

    public ErrorModel(HttpStatus errorId, String errorDetails, String exception) {
        this.timeStamp = LocalDateTime.now();
        this.errorId = errorId;
        this.errorDetails = errorDetails;
        this.exception = exception;

    }
}