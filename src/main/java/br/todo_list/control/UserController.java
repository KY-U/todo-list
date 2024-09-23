package br.todo_list.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import br.todo_list.model.User;
import br.todo_list.service.UserService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    //criar usuário
    @PostMapping
    public String createUser(@ModelAttribute User user) {
        userService.createUser(user);
        return "redirect:/login";
    }

    /*
    //get user
    @GetMapping("/{id}")
    public Optional<User> getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    //list
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    //delete
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    //update
    @PutMapping("/{id}")
    public User updateUser(@RequestBody User user) {
        return userService.createUser(user);
    }
     */
}
