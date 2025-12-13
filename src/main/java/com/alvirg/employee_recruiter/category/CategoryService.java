package com.alvirg.employee_recruiter.category;

import com.alvirg.employee_recruiter.category.request.CategoryRequest;
import com.alvirg.employee_recruiter.category.request.CategoryUpdateRequest;
import com.alvirg.employee_recruiter.category.response.CategoryResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    String createCategory(CategoryRequest request, String userId);
    void updateCategory(CategoryUpdateRequest request, String userId);
    List<CategoryResponse> findAllByOwner(String userId);
    CategoryResponse findCategoryById(String catId);
    void deleteCategoryById(String catId);
}
