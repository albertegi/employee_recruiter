package com.alvirg.employee_recruiter.category.impl;

import com.alvirg.employee_recruiter.category.Category;
import com.alvirg.employee_recruiter.category.request.CategoryRequest;
import com.alvirg.employee_recruiter.category.request.CategoryUpdateRequest;
import com.alvirg.employee_recruiter.category.response.CategoryResponse;
import jakarta.validation.constraints.NotBlank;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;



@Service
public class CategoryMapper {
    public Category toCategory(final CategoryRequest request) {

        return Category.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public void mergeCategory(final Category categoryToUpdate, final CategoryUpdateRequest request) {
        if(StringUtils.isNotBlank(request.getName())
        && !categoryToUpdate.getName().equals(request.getName())){
            categoryToUpdate.setName(request.getName());
        }

        if(StringUtils.isNotBlank(request.getDescription())
                && !categoryToUpdate.getDescription().equals(request.getDescription())){
            categoryToUpdate.setName(request.getDescription());
        }

    }
    private String userId;
    private String name;
    private String description;
    private int todoCount;

    public CategoryResponse toCategoryResponse(final Category category) {
        return CategoryResponse.builder()
                .userId(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .todoCount(category.getTodos().size())
                .build();
    }
}
