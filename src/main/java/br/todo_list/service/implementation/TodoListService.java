package br.todo_list.service.implementation;

import br.todo_list.repository.TodoItemRepository;
import br.todo_list.service.interfaces.ITodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.todo_list.model.TodoList;
import br.todo_list.repository.TodoListRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TodoListService implements ITodoListService {

    @Autowired
    private TodoListRepository todoListRepository;

    @Autowired
    private TodoItemRepository todoItemRepository;

    @Override
    public TodoList createTodoList(TodoList todoList) {
        return todoListRepository.save(todoList);
    }

    @Override
    public Optional<TodoList> getTodoList(Long id) {
        return todoListRepository.findById(id);
    }

    @Override
    public List<TodoList> getTodoListsByUserId(Long userId) {
        return todoListRepository.findByUserId(userId);
    }

    @Override
    public String getListTitle(Long id){
        Optional<TodoList> list = todoListRepository.findById(id);
        return list.get().getTitle();
    }

    @Override
    public String getListDescription(Long id){
        Optional<TodoList> list = todoListRepository.findById(id);
        return list.get().getDescription();
    }

    @Override
    public void deleteTodoList(Long id){
        todoListRepository.deleteById(id);
    }

    /* Não é necessário deletar os items individualmente devido à
    configuração de cascade

    public void deleteTodoListAndItems(long listId){
        List<TodoItem> items = todoItemRepository.findByTodoListId(listId);
        for(TodoItem item: items){
            todoItemRepository.delete(item);
        }
        todoListRepository.deleteById(listId);
    }
     */

    /*
    public String getListOwnerName(Long id) {
        Optional<TodoList> list = todoListRepository.findById(id);
        User owner = list.get().getUser();
        return owner.getUsername();
    }
     */
}
