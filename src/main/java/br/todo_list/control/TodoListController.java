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
        User user = userService.getUserByEmail(principal.getName());
        todoList.setUser(user);
        todoListService.createTodoList(todoList);
        return "create_list";
    }

    //editar lista
    @PostMapping("/edit")
    public String editTodoList(@ModelAttribute TodoList todoList, Principal principal) {
        User user = userService.getUserByEmail(principal.getName());
        todoList.setUser(user);
        todoListService.createTodoList(todoList);

        Long listId = todoList.getId();
        return "redirect:/edit_list/" + listId;
    }

    /*
    //get todo list
    @GetMapping("/{id}")
    public String getTodoList(@PathVariable Long id, Model model) {
        //recupera lista de items
        List<TodoItem> itemsList = todoItemService.getTodoItemsByTodoListId(id);
        model.addAttribute("todoItems", itemsList);

        return "todo_list";
    }

    //get todo lists by user id
    @GetMapping("/users/{userId}")
    public List<TodoList> getTodoListsByUserId(@PathVariable Long userId) {
        return todoListService.getTodoListsByUserId(userId);
    }

    //delete
    @DeleteMapping("/{id}")
    public void deleteTodoList(@PathVariable Long id) {
        todoListService.deleteTodoList(id);
    }

    //update
    @PutMapping("/{id}")
    public TodoList updateTodoList(@RequestBody TodoList todoList) {
        return todoListService.createTodoList(todoList);
    }

     */
}
