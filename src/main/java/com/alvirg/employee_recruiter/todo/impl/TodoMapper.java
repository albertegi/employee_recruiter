package com.alvirg.employee_recruiter.todo.impl;

import com.alvirg.employee_recruiter.category.response.CategoryResponse;
import com.alvirg.employee_recruiter.todo.Todo;
import com.alvirg.employee_recruiter.todo.request.TodoRequest;
import com.alvirg.employee_recruiter.todo.request.TodoUpdateRequest;
import com.alvirg.employee_recruiter.todo.response.TodoResponse;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class TodoMapper {

    public Todo toTodo(TodoRequest request) {
        return Todo.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .done(false)
                .build();
    }

    // merge the TodoUpdateRequest request from the request and toDoToUpdate from the database
    public void mergeTodo(Todo toDoToUpdate, TodoUpdateRequest request) {
        if(StringUtils.isNotBlank(request.getTitle())
                && !toDoToUpdate.getTitle().equals(request.getTitle())
        ){
            toDoToUpdate.setTitle(request.getTitle()); // if the title coming from the request is different from the one we already have in the database. then replace the one in the database with the one coming from the request
        }

        if(StringUtils.isNotBlank(request.getDescription())
                && !toDoToUpdate.getDescription().equals(request.getDescription())
        ){
            toDoToUpdate.setDescription(request.getDescription());
        }
    }


    public TodoResponse toTodoResponse(final Todo todo) {
        return TodoResponse.builder()
                .id(todo.getId())
                .title(todo.getTitle())
                .description(todo.getDescription())
                .startDate(todo.getStartDate())
                .endDate(todo.getEndDate())
                .startTime(todo.getStartTime())
                .endTime(todo.getEndTime())
                .done(todo.isDone())
                .categoryResponse(
                        CategoryResponse.builder()
                                .name(todo.getCategory().getName())
                                .description(todo.getCategory().getDescription())
                                .build()
                )
                .build();
    }
}
