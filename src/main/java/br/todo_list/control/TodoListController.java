package br.todo_list.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import br.todo_list.model.TodoList;
import br.todo_list.service.TodoListService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/todo_lists")
public class TodoListController {

    @Autowired
    private TodoListService todoListService;

    //create
    @PostMapping
    public TodoList createTodoList(@RequestBody TodoList todoList) {
        return todoListService.createTodoList(todoList);
    }

    //get todo list
    @GetMapping("/{id}")
    public Optional<TodoList> getTodoList(@PathVariable Long id) {
        return todoListService.getTodoList(id);
    }

    //get todo lists by user id
    @GetMapping("/user/{userId}")
    public List<TodoList> getTodoListsByUserId(@PathVariable Long userId) {
        return todoListService.getTodoListsByUserId(userId);
    }

    //delete
    @DeleteMapping("/{id}")
    public void deleteTodoList(@PathVariable Long id) {
        todoListService.deleteTodoList(id);
    }

    //update
    @PutMapping("/{id}")
    public TodoList updateTodoList(@RequestBody TodoList todoList) {
        return todoListService.createTodoList(todoList);
    }
}
