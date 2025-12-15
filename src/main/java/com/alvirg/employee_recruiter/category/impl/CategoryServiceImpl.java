package com.alvirg.employee_recruiter.category.impl;

import com.alvirg.employee_recruiter.category.Category;
import com.alvirg.employee_recruiter.category.CategoryRepository;
import com.alvirg.employee_recruiter.category.CategoryService;
import com.alvirg.employee_recruiter.category.request.CategoryRequest;
import com.alvirg.employee_recruiter.category.request.CategoryUpdateRequest;
import com.alvirg.employee_recruiter.category.response.CategoryResponse;
import com.alvirg.employee_recruiter.exception.BusinessException;
import com.alvirg.employee_recruiter.exception.ErrorCode;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public String createCategory(CategoryRequest request, String userId) {
        // the same user cannot create 2 categories with the same name. so lets check it.
        checkCategoryNameUniquenessForUser(request.getName(), userId);
        final Category category = this.categoryMapper.toCategory(request);
        return this.categoryRepository.save(category).getId();
    }



    @Override
    public void updateCategory(final CategoryUpdateRequest request, final String catId, String userId) {
        // first check that the category you want to update exists;
        final Category categoryToUpdate = this.categoryRepository.findById(catId)
                .orElseThrow(()-> new EntityNotFoundException("No Category found with this id: " + catId));
        checkCategoryNameUniquenessForUser(request.getName(), userId);

        // merge the CategoryUpdateRequest request from the request and categoryToUpdate from the database
        this.categoryMapper.mergeCategory(categoryToUpdate, request);

        this.categoryRepository.save(categoryToUpdate);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryResponse> findAllByOwner(String userId) {
        // find all the categories created by that specific user
        return this.categoryRepository.findAllByUserId(userId)
                .stream()
                .map(this.categoryMapper::toCategoryResponse)
                .toList();
    }

    @Override
    public CategoryResponse findCategoryById(String catId) {
        return this.categoryRepository.findById(catId)
                .map(this.categoryMapper::toCategoryResponse)
                .orElseThrow(()-> new EntityNotFoundException("No Category found with the ID::\"+catId"));
    }

    @Override
    public void deleteCategoryById(String catId) {
        // todo
        // mark the category for deletion
        // the scheduler should pick up all the marked categories and perform deletion

    }

    private void checkCategoryNameUniquenessForUser(String name, String userId) {
        final boolean alreadyExistForUser = this.categoryRepository.findByNameAndUser(name, userId);
        if(alreadyExistForUser){
            throw new BusinessException(ErrorCode.CATEGORY_ALREADY_EXISTS_FOR_USER);
        }
    }
}
