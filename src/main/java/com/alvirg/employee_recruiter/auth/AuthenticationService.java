package com.alvirg.employee_recruiter.auth;

import com.alvirg.employee_recruiter.auth.request.AuthenticationRequest;
import com.alvirg.employee_recruiter.auth.request.RefreshRequest;
import com.alvirg.employee_recruiter.auth.request.RegistrationRequest;
import com.alvirg.employee_recruiter.auth.response.AuthenticationResponse;
import org.springframework.security.core.Authentication;

public interface AuthenticationService {

    AuthenticationResponse login(AuthenticationRequest request);
    void register(RegistrationRequest request);
    AuthenticationResponse refresh(RefreshRequest request);
}
