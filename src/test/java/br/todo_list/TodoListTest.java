package br.todo_list;

import br.todo_list.model.TodoList;
import br.todo_list.model.TodoItem;
import br.todo_list.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class TodoListTest {

    private TodoList todoList;
    private User user;

    @BeforeEach
    public void setUp() {
        // Criando um usuário fictício para associar à TodoList
        user = new User();
        user.setId(1L);
        user.setUsername("Test User");

        // Criando uma TodoList e associando ao usuário
        todoList = new TodoList();
        todoList.setId(1L);
        todoList.setTitle("My Todo List");
        todoList.setDescription("This is a test todo list");
        todoList.setUser(user);
        todoList.setTodoItems(new ArrayList<>());
    }

    @Test
    public void testTodoListCreation() {
        // Verificando se a TodoList foi criada corretamente
        assertNotNull(todoList);
        assertEquals("My Todo List", todoList.getTitle());
        assertEquals("This is a test todo list", todoList.getDescription());
        assertEquals(user, todoList.getUser());
    }

    @Test
    public void testAddTodoItem() {
        // Criando um item de todo e adicionando à lista
        TodoItem todoItem = new TodoItem();
        todoItem.setId(1L);
        todoItem.setTitle("Test Item");
        todoItem.setDescription("Test Item Description");
        todoItem.setTodoList(todoList);

        todoList.getTodoItems().add(todoItem);

        // Verificando se o item foi adicionado corretamente
        assertEquals(1, todoList.getTodoItems().size());
        assertEquals("Test Item", todoList.getTodoItems().get(0).getTitle());
    }

    @Test
    public void testRemoveTodoItem() {
        // Criando e adicionando um item à lista
        TodoItem todoItem = new TodoItem();
        todoItem.setId(1L);
        todoItem.setTitle("Test Item");
        todoItem.setDescription("Test Item Description");
        todoItem.setTodoList(todoList);

        todoList.getTodoItems().add(todoItem);
        assertEquals(1, todoList.getTodoItems().size());

        // Removendo o item da lista
        todoList.getTodoItems().remove(todoItem);
        assertEquals(0, todoList.getTodoItems().size());
    }
}
