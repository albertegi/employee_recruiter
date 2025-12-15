package com.alvirg.employee_recruiter.todo.impl;

import com.alvirg.employee_recruiter.category.Category;
import com.alvirg.employee_recruiter.category.CategoryRepository;
import com.alvirg.employee_recruiter.category.request.CategoryRequest;
import com.alvirg.employee_recruiter.exception.BusinessException;
import com.alvirg.employee_recruiter.exception.ErrorCode;
import com.alvirg.employee_recruiter.todo.Todo;
import com.alvirg.employee_recruiter.todo.TodoService;
import com.alvirg.employee_recruiter.todo.request.TodoRequest;
import com.alvirg.employee_recruiter.todo.request.TodoUpdateRequest;
import com.alvirg.employee_recruiter.todo.response.TodoResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TodoServiceImpl implements TodoService {

    private TodoRepository todoRepository;
    private CategoryRepository categoryRepository;
    private TodoMapper todoMapper;

    @Override
    public String createTodo(final TodoRequest request, final String userId) {
        // check and return category.
        final Category category = checkAndReturnCategory(request.getCategoryId(), userId);
            final Todo todo = this.todoMapper.toTodo(request);
            todo.setCategory(category);
            return this.todoRepository.save(todo).getId();

        }


    @Override
    public void updateTodo(final TodoUpdateRequest request, final String todoId, final String userId) {


        final Todo toDoToUpdate = this.todoRepository.findById(userId)
                .orElseThrow(()-> new EntityNotFoundException("No Todo found with this id: \" + userId"));

        final Category category = checkAndReturnCategory(request.getCategoryId(), userId);

        // merge the TodoUpdateRequest request from the request and toDoToUpdate from the database
        this.todoMapper.mergeTodo(toDoToUpdate, request);
        toDoToUpdate.setCategory(category);
        this.todoRepository.save(toDoToUpdate);

    }

    @Override
    public TodoResponse findTodoById(final String todoId) {
        return this.todoRepository.findById(todoId)
                .map(this.todoMapper::toTodoResponse)
                .orElseThrow(()-> new EntityNotFoundException("No Todo found with the ID:" + todoId));
    }

    @Override
    public List<TodoResponse> findAllTodosForToday(final String userId) {
        return  this.todoRepository.findAllByUserId(userId)
                .stream()
                .map(this.todoMapper::toTodoResponse)
                .toList();

    }

    @Override
    public List<TodoResponse> findAllTodosByCategory(String userId, String catId) {
        return  this.todoRepository.findAllByUserIdAndCategoryId(userId, catId)
                .stream()
                .map(this.todoMapper::toTodoResponse)
                .toList();
    }

    @Override
    public List<TodoResponse> findAllDueTodos(String userId) {
        return  this.todoRepository.findAllDueTodos(userId)
                .stream()
                .map(this.todoMapper::toTodoResponse)
                .toList();
    }

    @Override
    public void deleteTodoById(String todoId) {
        this.todoRepository.deleteById(todoId);

    }

private Category checkAndReturnCategory(final String categoryId, final String userId){
    return this.categoryRepository.findByIdAndUserId(categoryId, userId)
            .orElseThrow(()-> new EntityNotFoundException("No category was found for that user with id: " + categoryId));
    }


}
