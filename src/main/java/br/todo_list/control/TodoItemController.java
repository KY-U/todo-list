package br.todo_list.control;

import br.todo_list.service.TodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    @PostMapping("/create")
    public String createTodoItem(
            @RequestParam("listId") Long listId,
            @RequestParam("title") String title,
            @RequestParam(value = "description", required = false) String description,
            Model model) {

        //criando o todoItem
        TodoItem item = new TodoItem();
        item.setTitle(title);
        item.setDescription(description);
        item.setCompleted(false);
        item.setTodoList(todoListService.getTodoList(listId).get());

        todoItemService.createTodoItem(item);

        model.addAttribute("listId", listId);

        return "create_item";
    }

    @PostMapping("/edit")
    public String editTodoItem(
            @RequestParam("listId") Long listId,
            @RequestParam("title") String title,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam("id") Long itemId,
            Model model) {

        //todoItem
        Optional<TodoItem> item = todoItemService.getTodoItem(itemId);

        item.get().setTitle(title);
        item.get().setDescription(description);
        item.get().setCompleted(false);
        item.get().setTodoList(todoListService.getTodoList(listId).get());

        todoItemService.createTodoItem(item.get());

        model.addAttribute("listId", listId);
        model.addAttribute("id", itemId);

        return "redirect:/edit_item?id=" + itemId + "&listId=" + listId;
    }

    /*
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
     */
}
