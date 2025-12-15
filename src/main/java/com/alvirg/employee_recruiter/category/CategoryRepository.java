package com.alvirg.employee_recruiter.category;

import com.alvirg.employee_recruiter.category.response.CategoryResponse;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, String> {

    /**
     *
     * This is a Spring Data JPA repository method that answers a yes / no question:
     * “Does a category with this name already exist for this user?”
     * It does NOT return a Category.
     * It returns a boolean.
     *
     * This is JPQL, not SQL:
     * You query entities (Category)
     * Not tables
     *
     * SELECT COUNT (c) > 0
     * This is the most important part.
     * What it does:
     * COUNT(c) → counts how many rows match i.e. counts how many Category rows satisfy the WHERE conditions.
     * > 0 → converts the count into a boolean
     * So:
     * If at least one row exists → true
     * If zero rows exist → false
     * ✔ This matches your method return type: boolean
     *  boolean result = true;
     */
    @Query(
            """
            SELECT COUNT (c) > 0
            FROM Category c
            WHERE LOWER(c.name) = LOWER(:name)
            AND c.createdBy = :userId OR c.createdBy = 'APP'
            """
    )
    boolean findByNameAndUser(String name, String userId);

    @Query("""
            SELECT c
            FROM Category c
            WHERE c.createdBy = :userId OR c.createdBy = 'APP'
            """)
    List<Category> findAllByUserId(String userId);

    @Query("""
            SELECT c
            FROM Category c
            WHERE c.id = :categoryId
            AND (c.createdBy = :userId OR c.createdBy = 'APP')
            """)
    Optional<Category> findByIdAndUserId(String categoryId, String userId);

}
