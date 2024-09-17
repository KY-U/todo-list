package br.todo_list.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

    @GetMapping("/home")
    public String home() {
        return "this is home page";
    }

    @GetMapping("/login")
    public String login() {
        return "this is login page";
    }
}