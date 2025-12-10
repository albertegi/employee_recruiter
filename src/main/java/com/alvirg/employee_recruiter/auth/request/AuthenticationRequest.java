package com.alvirg.employee_recruiter.auth.request;


import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationRequest {
    // add validation later on
    private String email;
    private String password;
}
