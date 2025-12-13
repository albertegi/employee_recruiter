package com.alvirg.employee_recruiter.user.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileUpdateRequest {

    @NotBlank(message = "VALIDATION.PROFILE_UPDATE.FIRSTNAME.NOT_BLANK")
    @Size(
            min = 5,
            max = 50,
            message = "VALIDATION.REGISTRATION.PROFILE_UPDATE.SIZE"
    )
    @Pattern(
            regexp = "^[\\p{L} '-]+$",
            message = "VALIDATION.REGISTRATION.PROFILE_UPDATE.PATTERN"
    )
    @Schema(example = "Albert")
    private String firstName;

    @NotBlank(message = "VALIDATION.PROFILE_UPDATE.FIRSTNAME.NOT_BLANK")
    @Size(
            min = 5,
            max = 50,
            message = "VALIDATION.REGISTRATION.PROFILE_UPDATE.SIZE"
    )
    @Pattern(
            regexp = "^[\\p{L} '-]+$",
            message = "VALIDATION.REGISTRATION.PROFILE_UPDATE.PATTERN"
    )
    @Schema(example = "Albert")
    private String LastName;


    private LocalDate dateOfBirth;
}
