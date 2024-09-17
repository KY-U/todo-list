package br.todo_list.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import br.todo_list.model.TodoItem;
import br.todo_list.service.TodoItemService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/items")
public class TodoItemController {

    @Autowired
    private TodoItemService todoItemService;

    //create
    @PostMapping
    public TodoItem createTodoItem(@RequestBody TodoItem todoItem) {
        return todoItemService.createTodoItem(todoItem);
    }

    //get todo item
    @GetMapping("/{id}")
    public Optional<TodoItem> getTodoItem(@PathVariable Long id) {
        return todoItemService.getTodoItem(id);
    }

    //get todo items by to do list
    @GetMapping("/lists/{todoListId}")
    public List<TodoItem> getTodoItemsByTodoListId(@PathVariable Long todoListId) {
        return todoItemService.getTodoItemsByTodoListId(todoListId);
    }

    // delete item
    @DeleteMapping("/{id}")
    public void deleteTodoItem(@PathVariable Long id) {
        todoItemService.deleteTodoItem(id);
    }

    //update
    @PostMapping("/{id}")
    public TodoItem updateTodoItem(@RequestBody TodoItem todoItem) {
        return todoItemService.createTodoItem(todoItem);
    }

}
