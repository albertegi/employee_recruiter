package com.alvirg.employee_recruiter.category;

import com.alvirg.employee_recruiter.category.request.CategoryRequest;
import com.alvirg.employee_recruiter.category.request.CategoryUpdateRequest;
import com.alvirg.employee_recruiter.category.response.CategoryResponse;
import com.alvirg.employee_recruiter.common.RestResponse;
import com.alvirg.employee_recruiter.user.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
@Tag(name = "Categories", description = "Category Management API")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/category")
    public ResponseEntity<RestResponse> createCategory(
            @Valid
            @RequestBody
            final CategoryRequest request,
            final Authentication authentication // the userId will be gotten from the token - String userId
            ){
        final String userId = ((User)authentication.getPrincipal()).getId();
        final String catId = this.categoryService.createCategory(request, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(new RestResponse(catId));
    }

    @PutMapping("/{category-id}")
    public ResponseEntity<Void> updateCategory(
            @Valid
            @RequestBody
            final CategoryUpdateRequest request,
            @PathVariable("category-id")
            final String categoryId,
            final Authentication authentication
            ){
        final String userId = ((User)authentication.getPrincipal()).getId();
        this.categoryService.updateCategory(request, categoryId, userId);
        return ResponseEntity.accepted().build(); // we just need to inform the user that the request has been accepted or updated.

    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> findAllByOwner(
            final Authentication authentication){
        final String userId = ((User)authentication.getPrincipal()).getId();
        return ResponseEntity.ok(this.categoryService.findAllByOwner(userId));
    }

    @GetMapping("{/category-id}")
    public ResponseEntity<CategoryResponse> findCategoryById(
            @PathVariable("category-id")
            final String categoryId){
        return ResponseEntity.ok(this.categoryService.findCategoryById(categoryId));

    }

    @DeleteMapping("{/category-id}")
    public ResponseEntity<Void> deleteCategoryById(
            @PathVariable("category-id")
            final String categoryId){
        this.categoryService.deleteCategoryById(categoryId);
        return ResponseEntity.ok().build();
    }
}
