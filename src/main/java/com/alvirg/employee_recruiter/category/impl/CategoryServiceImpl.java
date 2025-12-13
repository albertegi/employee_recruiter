package com.alvirg.employee_recruiter.category.impl;

import com.alvirg.employee_recruiter.category.CategoryService;
import com.alvirg.employee_recruiter.category.request.CategoryRequest;
import com.alvirg.employee_recruiter.category.request.CategoryUpdateRequest;
import com.alvirg.employee_recruiter.category.response.CategoryResponse;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    @Override
    public String createCategory(CategoryRequest request, String userId) {
        return "";
    }

    @Override
    public void updateCategory(CategoryUpdateRequest request, String userId) {

    }

    @Override
    public List<CategoryResponse> findAllByOwner(String userId) {
        return List.of();
    }

    @Override
    public CategoryResponse findCategoryById(String catId) {
        return null;
    }

    @Override
    public void deleteCategoryById(String catId) {

    }
}
