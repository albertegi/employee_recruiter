package com.alvirg.employee_recruiter.todo.response;

import com.alvirg.employee_recruiter.category.response.CategoryResponse;
import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TodoResponse {
    private String id;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean done;

    // we can add the category information
    // for one single To do we can display the category in a page or home
    private CategoryResponse categoryResponse;
}
