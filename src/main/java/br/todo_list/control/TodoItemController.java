package br.todo_list.control;

import br.todo_list.model.TodoList;
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
    public String createTodoItem(@RequestParam("listId") Long listId, @ModelAttribute TodoItem item) {
        item.setTodoList(todoListService.getTodoList(listId).orElse(null));
        todoItemService.createTodoItem(item);
        return "redirect:/list_dashboard?id=" + listId;
    }

    @PostMapping("/edit")
    public String editTodoItem(@RequestParam("listId") Long listId, @ModelAttribute TodoItem item) {
        item.setTodoList(todoListService.getTodoList(listId).orElse(null));
        todoItemService.createTodoItem(item);
        return "redirect:/edit_item?id=" + item.getId() + "&listId=" + listId;
    }

    @GetMapping("/complete")
    public String completeItem(@RequestParam("id") Long itemId, @RequestParam("listId") Long listId) {
        todoItemService.completeTodoItem(itemId);
        return "redirect:/list_dashboard?id=" + listId;
    }

}
