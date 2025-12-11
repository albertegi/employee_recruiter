package com.alvirg.employee_recruiter.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import tools.jackson.databind.node.StringNode;

@Getter
public enum ErrorCode {

    USER_NOT_FOUND("USER_NOT_FOUND", "User not found with id %s", HttpStatus.NOT_FOUND),

    CHANGE_PASSWORD_MISMATCH("CHANGE_PASSWORD_MISMATCH","Current Password and new password are not the same", HttpStatus.BAD_REQUEST),
    INVALID_CURRENT_PASSWORD("INVALID_CURRENT_PASSWORD","Current Password is invalid", HttpStatus.BAD_REQUEST ),
    ACCOUNT_ALREADY_DEACTIVATED("ACCOUNT_ALREADY_DEACTIVATED","Account is already deactivated", HttpStatus.BAD_REQUEST),
    ACCOUNT_ALREADY_ACTIVATED("ACCOUNT_ALREADY_ACTIVATED", "Account already activated", HttpStatus.BAD_REQUEST),
    EMAIL_ALREADY_EXISTS("EMAIL_ALREADY_EXISTS","email already exists" ,HttpStatus.BAD_REQUEST ),
    PHONE_NUMBER_ALREADY_EXISTS("PHONE_NUMBER_ALREADY_EXISTS", "Phone number already exists", HttpStatus.BAD_REQUEST),
    PASSWORD_MISMATCH("PASSWORD_MISMATCH", "Passwords do not match", HttpStatus.BAD_REQUEST);

    private final  String code;
    private final String defaultMessage;
    private final HttpStatus httpStatus;

    ErrorCode(final String code,
              final String defaultMessage,
              final HttpStatus httpStatus) {
        this.code = code;
        this.defaultMessage = defaultMessage;
        this.httpStatus = httpStatus;
    }
}
