package br.todo_list.control;

import br.todo_list.service.TodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import br.todo_list.model.TodoItem;
import br.todo_list.service.TodoItemService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/items")
public class TodoItemController {

    @Autowired
    private TodoItemService todoItemService;

    @Autowired
    private TodoListService todoListService;
    //create
    @PostMapping
    public String createTodoItem(
            @RequestParam("listId") Long listId,
            @RequestParam("title") String title,
            @RequestParam(value = "description", required = false) String description) {

        //criando o todoItem
        TodoItem item = new TodoItem();
        item.setTitle(title);
        item.setDescription(description);
        item.setCompleted(false);
        item.setTodoList(todoListService.getTodoList(listId).get());

        todoItemService.createTodoItem(item);

        return "redirect:/list_dashboard/" + listId;
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
