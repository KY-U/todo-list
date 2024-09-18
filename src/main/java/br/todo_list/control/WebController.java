package br.todo_list.control;

import br.todo_list.model.TodoItem;
import br.todo_list.model.TodoList;
import br.todo_list.model.User;
import br.todo_list.service.TodoItemService;
import br.todo_list.service.TodoListService;
import br.todo_list.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/home")
    public String home(Authentication authentication, Model model) {
        //email do usuário logado
        String email = authentication.getName();

        //recuperando o usuário e suas listas
        User user = userService.getUserByEmail(email);
        List<TodoList> userTodoLists = todoListService.getTodoListsByUserId(user.getId());

        //adicionando aos modelos
        model.addAttribute("user", user);
        model.addAttribute("todoLists", userTodoLists);

        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    @GetMapping("/list_dashboard/{id}")
    public String listDashboard(@PathVariable("id") Long listId, Model model){
        //System.out.println("Received listId: " + listId);
        List<TodoItem> itemList = todoItemService.getTodoItemsByTodoListId(listId);
        String listTitle = todoListService.getListTitle(listId);
        String description = todoListService.getListDescription(listId);

        if(itemList == null){
            System.out.println("nao achou nenhum item");
        }
        else{
            System.out.println("Received itemList: " + itemList);
        }
        model.addAttribute("itemList", itemList);
        model.addAttribute("title", listTitle);
        model.addAttribute("description", description);
        model.addAttribute("listId", listId);

        return "list_dashboard";
    }

    @GetMapping("/create_list")
    public String createList(){
        return "create_list";
    }

    @PostMapping("/items/{id}/complete")
    public String completeItem(@PathVariable("id") Long itemId, @RequestParam("listId") Long listId){
        //recuperando o item
        Optional<TodoItem> item = todoItemService.getTodoItem(itemId);

        //recuperando a lista ao qual pertence
        TodoList List = item.get().getTodoList();

        item.get().setCompleted(true);
        //função "save" do repositório cria e edita
        todoItemService.createTodoItem(item.get());

        return "redirect:/list_dashboard/" + listId;
    }

    @GetMapping("/create_item")
    public String createItem(@RequestParam("listId") Long listId, Model model){
        model.addAttribute("listId", listId);
        return "create_item";
    }

}