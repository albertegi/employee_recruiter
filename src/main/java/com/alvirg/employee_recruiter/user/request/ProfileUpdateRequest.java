package com.alvirg.employee_recruiter.user.request;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileUpdateRequest {

    private String firstName;
    private String LastName;
    private LocalDate dateOfBirth;
}
