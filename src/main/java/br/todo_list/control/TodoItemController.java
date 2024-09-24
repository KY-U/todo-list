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

    @GetMapping("/complete")
    public String completeItem(@RequestParam("id") Long itemId, @RequestParam("listId") Long listId, Model model){
        //recuperando o item
        Optional<TodoItem> item = todoItemService.getTodoItem(itemId);

        //marcando como completado
        item.get().setCompleted(true);

        //função "save" do repositório cria e edita
        todoItemService.createTodoItem(item.get());

        //model.addAttribute("id", listId);

        return "redirect:/list_dashboard?id=" + listId;
    }

}
