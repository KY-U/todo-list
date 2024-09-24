package br.todo_list.control;

import br.todo_list.model.TodoItem;
import br.todo_list.model.User;
import br.todo_list.service.TodoItemService;
import br.todo_list.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import br.todo_list.model.TodoList;
import br.todo_list.service.TodoListService;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/lists")
public class TodoListController {

    @Autowired
    private UserService userService;

    @Autowired
    private TodoListService todoListService;

    //criar lista
    @PostMapping("/create")
    public String createTodoList(@ModelAttribute TodoList todoList, Principal principal) {
        todoList.setUser(userService.getUserByEmail(principal.getName()));
        todoListService.createTodoList(todoList);
        return "redirect:/home";
    }

    //editar lista
    @PostMapping("/edit")
    public String editTodoList(@ModelAttribute TodoList todoList, Principal principal) {
        todoList.setUser(userService.getUserByEmail(principal.getName()));
        todoListService.createTodoList(todoList);
        return "redirect:/edit_list?id=" + todoList.getId();
    }

    @PostMapping("/delete")
    public String deleteList(@RequestParam("id") Long listId){
        //todoListService.deleteTodoListAndItems(listId);
        todoListService.deleteTodoList(listId);
        return "redirect:/home";
    }

}
