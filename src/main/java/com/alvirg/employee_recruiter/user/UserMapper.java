package com.alvirg.employee_recruiter.user;

import com.alvirg.employee_recruiter.auth.request.RegistrationRequest;
import com.alvirg.employee_recruiter.user.request.ProfileUpdateRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public void mergeUserInfo(final User savedUser, final ProfileUpdateRequest request) {
        if(StringUtils.isNotBlank(request.getFirstName())
                && !savedUser.getFirstName().equals(request.getFirstName())){
            savedUser.setFirstName(request.getFirstName());
        }

        if(StringUtils.isNotBlank(request.getLastName())
                && !savedUser.getLastName().equals(request.getLastName())){
            savedUser.setLastName(request.getLastName());
        }

        if(request.getDateOfBirth() != null
                && !request.getDateOfBirth().equals(savedUser.getDateOfBirth()))
            savedUser.setDateOfBirth(request.getDateOfBirth());
        }

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
    private String confirmPassword;

    public User toUser(RegistrationRequest request) {
        return User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .password(request.getPassword())
                .enabled(true)
                .locked(false)
                .credentialExpired(false)
                .emailVerified(false)
                .phoneVerified(false)
                .build();
    }
}
