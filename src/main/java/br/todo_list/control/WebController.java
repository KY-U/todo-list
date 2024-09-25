package br.todo_list.control;

import br.todo_list.model.User;
import br.todo_list.service.interfaces.ITodoItemService;
import br.todo_list.service.interfaces.ITodoListService;
import br.todo_list.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WebController {

    @Autowired
    private IUserService IUserService;

    @Autowired
    private ITodoListService ITodoListService;

    @Autowired
    private ITodoItemService ITodoItemService;

    //root
    @GetMapping("/")
    public String goHome(){
        return "redirect:/home";
    }

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
        User user = IUserService.getUserByEmail(authentication.getName());

        //adicionando o usuário e suas listas à página home
        model.addAttribute("user", user);
        model.addAttribute("todoLists", ITodoListService.getTodoListsByUserId(user.getId()));

        return "home";
    }

    //página de lista específica
    @GetMapping("/list_dashboard")
    public String listDashboard(@RequestParam("id") Long listId, Model model){
        model.addAttribute("itemList", ITodoItemService.getIncompleteItemsByListId(listId));
        model.addAttribute("title", ITodoListService.getListTitle(listId));
        model.addAttribute("description", ITodoListService.getListDescription(listId));
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
        model.addAttribute("list", ITodoListService.getTodoList(listId).get());

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
        model.addAttribute("item", ITodoItemService.getTodoItem(itemId).get());
        model.addAttribute("listId", listId);

        return "create_item";
    }

    //página de histórico de items da lista
    @PostMapping("/history")
    public String getHistory(@RequestParam("listId") Long listId, Model model){
        model.addAttribute("itemList", ITodoItemService.getCompletedItemsByListId(listId));
        model.addAttribute("listId", listId);

        return "history";
    }
}