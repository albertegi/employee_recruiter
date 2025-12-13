package com.alvirg.employee_recruiter.handler;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ErrorResponse {

    private String message; // this will hold the error message
    private String code; // the error code
    private List<ValidationError> validationErrors;

    // Create a nested class for ValidationError

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ToString
    public static class ValidationError {
        private String field;
        private String code;
        private String message;
    }
}
