package br.todo_list.control;

import br.todo_list.model.User;
import br.todo_list.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Controller
public class WebController {

    @Autowired
    private UserService userService;

    @GetMapping("/home")
    public String home(Authentication authentication, Model model) {
        String email = authentication.getName();
        User user = userService.getUserByEmail(email);
        model.addAttribute("user", user);
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

}