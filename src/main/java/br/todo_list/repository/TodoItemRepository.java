package br.todo_list.repository;

import br.todo_list.model.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TodoItemRepository extends JpaRepository<TodoItem, Long> {
    //encontrar os items de uma lista
    List<TodoItem> findByTodoListId(Long todoListId);

    //encontrar os items completos de uma lista
    List<TodoItem> findByTodoListIdAndCompletedTrue(Long todoListId);

    //encontrar os items não completos de uma lista
    List<TodoItem> findByTodoListIdAndCompletedFalse(Long todoListId);

}