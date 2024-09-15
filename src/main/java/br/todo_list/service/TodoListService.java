package br.todo_list.service;

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
}
