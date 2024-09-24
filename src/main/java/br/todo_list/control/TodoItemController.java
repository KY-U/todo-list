package br.todo_list.control;

import br.todo_list.service.interfaces.ITodoItemService;
import br.todo_list.service.interfaces.ITodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import br.todo_list.model.TodoItem;

@Controller
@RequestMapping("/items")
public class TodoItemController {

    @Autowired
    private ITodoItemService ITodoItemService;

    @Autowired
    private ITodoListService ITodoListService;

    //create
    @PostMapping("/create")
    public String createTodoItem(@RequestParam("listId") Long listId, @ModelAttribute TodoItem item) {
        item.setTodoList(ITodoListService.getTodoList(listId).orElse(null));
        ITodoItemService.createTodoItem(item);
        return "redirect:/list_dashboard?id=" + listId;
    }

    @PostMapping("/edit")
    public String editTodoItem(@RequestParam("listId") Long listId, @ModelAttribute TodoItem item) {
        item.setTodoList(ITodoListService.getTodoList(listId).orElse(null));
        ITodoItemService.createTodoItem(item);
        return "redirect:/edit_item?id=" + item.getId() + "&listId=" + listId;
    }

    @GetMapping("/complete")
    public String completeItem(@RequestParam("id") Long itemId, @RequestParam("listId") Long listId) {
        ITodoItemService.completeTodoItem(itemId);
        return "redirect:/list_dashboard?id=" + listId;
    }

}
