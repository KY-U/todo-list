package br.todo_list.service.interfaces;

import br.todo_list.model.TodoItem;

import java.util.List;
import java.util.Optional;

public interface ITodoItemService {
    TodoItem createTodoItem(TodoItem todoItem);

    Optional<TodoItem> getTodoItem(Long id);

    List<TodoItem> getCompletedItemsByListId(Long listId);

    List<TodoItem> getIncompleteItemsByListId(Long listId);

    TodoItem completeTodoItem(Long itemId);
}
