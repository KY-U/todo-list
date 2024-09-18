package br.todo_list.control;

import br.todo_list.model.TodoItem;
import br.todo_list.service.TodoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import br.todo_list.model.TodoList;
import br.todo_list.service.TodoListService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/lists")
public class TodoListController {

    @Autowired
    private TodoListService todoListService;

    @Autowired
    private TodoItemService todoItemService;
    //create
    @PostMapping
    public TodoList createTodoList(@RequestBody TodoList todoList) {
        return todoListService.createTodoList(todoList);
    }

    //get todo list
    @GetMapping("/{id}")
    public String getTodoList(@PathVariable Long id, Model model) {
        //recupera lista de items
        List<TodoItem> itemsList = todoItemService.getTodoItemsByTodoListId(id);
        model.addAttribute("todoItems", itemsList);

        return "todo_list";
    }

    //get todo lists by user id
    @GetMapping("/users/{userId}")
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
