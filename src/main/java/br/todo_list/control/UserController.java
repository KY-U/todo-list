package br.todo_list.control;

import br.todo_list.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import br.todo_list.model.User;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService IUserService;

    //criar usuário
    @PostMapping
    public String createUser(@ModelAttribute User user) {
        IUserService.createUser(user);
        return "redirect:/login";
    }
}
