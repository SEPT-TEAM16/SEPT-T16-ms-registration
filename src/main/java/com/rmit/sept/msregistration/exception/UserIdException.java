package com.rmit.sept.msregistration.exception;

import lombok.Getter;

@Getter
public class UserIdException extends RuntimeException {

    private  static final long serialVersionUID = 23134514;

    private Integer value;

    public UserIdException(String message, Integer value) {
        super(message);
        this.value = value;
    }
}
