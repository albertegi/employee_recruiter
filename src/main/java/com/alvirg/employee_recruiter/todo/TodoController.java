package com.alvirg.employee_recruiter.todo;

import com.alvirg.employee_recruiter.common.RestResponse;
import com.alvirg.employee_recruiter.todo.request.TodoRequest;
import com.alvirg.employee_recruiter.todo.request.TodoUpdateRequest;
import com.alvirg.employee_recruiter.todo.response.TodoResponse;
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
@RequestMapping("/api/v1/todos")
@RequiredArgsConstructor
@Tag(name = "Todos",  description = "Todo API Management")
public class TodoController {
    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<RestResponse>createTodo(
            @Valid
            @RequestBody
            final TodoRequest request,
            final Authentication authentication){

        String userId = ((User)authentication.getPrincipal()).getId();
        final String todoId = this.todoService.createTodo(request, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(new RestResponse(todoId));
    }

    @PutMapping("{/todo-id}")
    public ResponseEntity<Void> updateTodo(
            @Valid
            @RequestBody
            final TodoUpdateRequest request,
            @PathVariable("todo-id")
            final String todoId,
            final Authentication authentication){
        String userId = ((User)authentication.getPrincipal()).getId();
        this.todoService.updateTodo(request, todoId, userId);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("{/todo-id}")
    public ResponseEntity<TodoResponse> findTodoById(
            final TodoUpdateRequest request,
            @PathVariable("todo-id")
            final String todoId){
        return ResponseEntity.ok(this.todoService.findTodoById(todoId));
    }

    @GetMapping("/today")
   public ResponseEntity<List<TodoResponse>> findAllTodosForToday(
            final Authentication authentication){
        String userId = ((User)authentication.getPrincipal()).getId();
        return ResponseEntity.ok(this.todoService.findAllTodosForToday(userId));
    }

    @GetMapping("category/{category-id}")
    public ResponseEntity<List<TodoResponse>> findAllTodosByCategory(
            @PathVariable("category-id")
            final String categoryId,
            final Authentication authentication){
        String userId = ((User)authentication.getPrincipal()).getId();
        return ResponseEntity.ok(this.todoService.findAllTodosByCategory(categoryId, userId));
    }

    @GetMapping("/due")
    public ResponseEntity<List<TodoResponse>> findAllDueTodos(
            final Authentication authentication){
        String userId = ((User)authentication.getPrincipal()).getId();
        return ResponseEntity.ok(this.todoService.findAllDueTodos(userId));
    }

    @GetMapping("{/todo-id}")
    public ResponseEntity<Void> deleteTodoById(
            @PathVariable("todo-id")
            final String todoId){
        this.todoService.deleteTodoById(todoId);
        return ResponseEntity.ok().build();

    }


}
