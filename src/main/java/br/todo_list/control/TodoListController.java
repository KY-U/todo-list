package br.todo_list.control;

import br.todo_list.service.interfaces.ITodoListService;
import br.todo_list.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import br.todo_list.model.TodoList;

import java.security.Principal;

@Controller
@RequestMapping("/lists")
public class TodoListController {

    @Autowired
    private IUserService IUserService;

    @Autowired
    private ITodoListService ITodoListService;

    //criar lista
    @PostMapping("/create")
    public String createTodoList(@ModelAttribute TodoList todoList, Principal principal) {
        todoList.setUser(IUserService.getUserByEmail(principal.getName()));
        ITodoListService.createTodoList(todoList);
        return "redirect:/home";
    }

    //editar lista
    @PostMapping("/edit")
    public String editTodoList(@ModelAttribute TodoList todoList, Principal principal) {
        todoList.setUser(IUserService.getUserByEmail(principal.getName()));
        ITodoListService.createTodoList(todoList);
        return "redirect:/edit_list?id=" + todoList.getId();
    }

    @PostMapping("/delete")
    public String deleteList(@RequestParam("id") Long listId){
        ITodoListService.deleteTodoListAndItems(listId);
        return "redirect:/home";
    }

}
