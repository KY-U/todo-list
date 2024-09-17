package br.todo_list.repository;

import br.todo_list.model.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TodoItemRepository extends JpaRepository<TodoItem, Long> {
    List<TodoItem> findByTodoListId(Long todoListId);

    List<TodoItem> findByTitle(String title);
}