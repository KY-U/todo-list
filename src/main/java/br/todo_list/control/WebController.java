package br.todo_list.control;

import br.todo_list.model.TodoItem;
import br.todo_list.model.TodoList;
import br.todo_list.model.User;
import br.todo_list.service.TodoItemService;
import br.todo_list.service.TodoListService;
import br.todo_list.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchTransactionManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class WebController {

    @Autowired
    private UserService userService;

    @Autowired
    private TodoListService todoListService;

    @Autowired
    private TodoItemService todoItemService;

    //página de login
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    //página de registrar novo usuário
    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    //página principal "home"
    @GetMapping("/home")
    public String home(Authentication authentication, Model model) {
        //recuperando o usuário
        User user = userService.getUserByEmail(authentication.getName());

        //adicionando o usuário e suas listas à página home
        model.addAttribute("user", user);
        model.addAttribute("todoLists", todoListService.getTodoListsByUserId(user.getId()));

        return "home";
    }

    //página de lista específica
    @GetMapping("/list_dashboard")
    public String listDashboard(@RequestParam("id") Long listId, Model model){
        model.addAttribute("itemList", todoItemService.getIncompleteItemsByListId(listId));
        model.addAttribute("title", todoListService.getListTitle(listId));
        model.addAttribute("description", todoListService.getListDescription(listId));
        model.addAttribute("listId", listId);

        return "list_dashboard";
    }

    //página de criar lista
    @GetMapping("/create_list")
    public String createList(){
        return "create_list";
    }

    //página de editar list
    @GetMapping("/edit_list")
    public String editList(@RequestParam("id") Long listId, Model model){
        model.addAttribute("list", todoListService.getTodoList(listId).get());

        return "create_list";
    }

    //página de criar item
    @GetMapping("/create_item")
    public String createItem(@RequestParam("listId") Long listId, Model model){
        model.addAttribute("listId", listId);
        return "create_item";
    }

    //página de editar item
    @GetMapping("/edit_item")
    public String editItem(@RequestParam("id") Long itemId, @RequestParam("listId") Long listId, Model model){
        model.addAttribute("item", todoItemService.getTodoItem(itemId).get());
        model.addAttribute("listId", listId);

        return "create_item";
    }

    //página de histórico de items da lista
    @PostMapping("/history")
    public String getHistory(@RequestParam("listId") Long listId, Model model){
        model.addAttribute("itemList", todoItemService.getCompletedItemsByListId(listId));
        model.addAttribute("listId", listId);

        return "history";
    }
}