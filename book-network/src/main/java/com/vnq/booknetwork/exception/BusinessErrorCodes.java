package com.vnq.booknetwork.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum BusinessErrorCodes {
    NO_CODE(0, HttpStatus.NOT_IMPLEMENTED, "No code"),
    ACCOUNT_LOCKED(1001, HttpStatus.FORBIDDEN, "Account is locked"),
    INCORRECT_CURRENT_PASSWORD(1002, HttpStatus.BAD_REQUEST, "Incorrect current password"),
    NEW_PASSWORD_DOES_NOT_MATCH(1003, HttpStatus.BAD_REQUEST, "New password does not match"),
    ACCOUNT_DISABLED(1004, HttpStatus.FORBIDDEN, "Account is disabled"),
    BAD_CREDENTIALS(1005, HttpStatus.UNAUTHORIZED, "Login and / or password is incorrect"),
    ;


    private final int code;
    private final HttpStatus httpStatus;
    private final String description;

    BusinessErrorCodes(int code, HttpStatus httpStatus, String description) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.description = description;
    }
}
