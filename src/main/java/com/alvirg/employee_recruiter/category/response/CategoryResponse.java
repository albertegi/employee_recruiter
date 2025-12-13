package com.alvirg.employee_recruiter.category.response;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryResponse {

    private String userId;
    private String name;
    private String description;
    private int todoCount; // how many dotos we have for a category for a specific user
}
