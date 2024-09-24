package br.todo_list.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.todo_list.model.TodoList;

import java.util.List;
public interface TodoListRepository extends JpaRepository<TodoList, Long> {
    //encontrar listas do usuário
    List<TodoList> findByUserId(Long userId);
}
