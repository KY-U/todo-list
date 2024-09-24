package br.todo_list.service.interfaces;

import br.todo_list.model.TodoList;

import java.util.List;
import java.util.Optional;

public interface ITodoListService {
    TodoList createTodoList(TodoList todoList);

    Optional<TodoList> getTodoList(Long id);

    List<TodoList> getTodoListsByUserId(Long userId);

    String getListTitle(Long id);

    String getListDescription(Long id);

    void deleteTodoList(Long id);
}
