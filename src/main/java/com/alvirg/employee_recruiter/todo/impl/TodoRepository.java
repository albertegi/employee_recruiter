package com.alvirg.employee_recruiter.todo.impl;

import com.alvirg.employee_recruiter.todo.Todo;
import com.alvirg.employee_recruiter.todo.request.TodoRequest;
import com.alvirg.employee_recruiter.todo.response.TodoResponse;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, String> {

    @Query("""
            SELECT COUNT (t) > 0
            FROM Todo t
            WHERE lower(t.title) = lower(:title)
            AND t.createdBy = :userId OR t.createdBy = 'APP'
            """)
    boolean findByTitleAndUser(String title, String userId);

    List<Todo> findAllByUserId(String userId);

    List<Todo> findAllByUserIdAndCategoryId(String userId, String categoryId);

    @Query("""
            SELECT t
            FROM Todo t
            WHERE t.endDate >= current_date AND t.endTime >= current_time
            AND t.createdBy = :userId OR t.createdBy = 'APP'
           """)
    List<Todo> findAllDueTodos(String userId);
}
