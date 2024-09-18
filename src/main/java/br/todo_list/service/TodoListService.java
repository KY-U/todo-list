package br.todo_list.service;

import br.todo_list.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.todo_list.model.TodoList;
import br.todo_list.repository.TodoListRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TodoListService {

    @Autowired
    private TodoListRepository todoListRepository;

    public TodoList createTodoList(TodoList todoList) {
        return todoListRepository.save(todoList);
    }

    public Optional<TodoList> getTodoList(Long id) {
        return todoListRepository.findById(id);
    }

    public List<TodoList> getTodoListsByUserId(Long userId) {
        return todoListRepository.findByUserId(userId);
    }

    public void deleteTodoList(Long id) {
        todoListRepository.deleteById(id);
    }

    public String getListOwnerName(Long id) {
        Optional<TodoList> list = todoListRepository.findById(id);
        User owner = list.get().getUser();
        return owner.getUsername();
    }

    public String getListTitle(Long id){
        Optional<TodoList> list = todoListRepository.findById(id);
        return list.get().getTitle();
    }

    public String getListDescription(Long id){
        Optional<TodoList> list = todoListRepository.findById(id);
        return list.get().getDescription();
    }
}
