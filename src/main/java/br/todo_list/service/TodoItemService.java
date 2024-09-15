package br.todo_list.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.todo_list.model.TodoItem;
import br.todo_list.repository.TodoItemRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TodoItemService {

    @Autowired
    private TodoItemRepository todoItemRepository;

    public TodoItem createTodoItem(TodoItem todoItem) {
        return todoItemRepository.save(todoItem);
    }

    public Optional<TodoItem> getTodoItem(Long id) {
        return todoItemRepository.findById(id);
    }

    public List<TodoItem> getTodoItemsByTodoListId(Long todoListId) {
        return todoItemRepository.findByTodoListId(todoListId);
    }

    public void deleteTodoItem(Long id) {
        todoItemRepository.deleteById(id);
    }
}
