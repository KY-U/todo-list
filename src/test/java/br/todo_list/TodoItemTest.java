package br.todo_list;

import br.todo_list.model.TodoItem;
import br.todo_list.model.TodoList;
import br.todo_list.model.User;
import br.todo_list.repository.TodoListRepository;
import br.todo_list.repository.UserRepository;
import jakarta.validation.Validator;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TodoItemTest {

    private Validator validator;

    @Autowired
    private UserRepository userRepository; // Repositório para User
    @Autowired
    private TodoListRepository todoListRepository; // Repositório para TodoList

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testTodoItemCreation() {
        // Cria um User para associar à TodoList
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("password123");
        user.setEmail("test@example.com");
        user.setRole("USER");
        userRepository.save(user); // Salva o usuário

        // Cria uma TodoList associada ao User
        TodoList todoList = new TodoList();
        todoList.setTitle("My Todo List");
        todoList.setDescription("A list for testing");
        todoList.setUser(user); // Associa a TodoList ao usuário
        todoListRepository.save(todoList); // Salva a lista

        // Criação do TodoItem
        TodoItem todoItem = new TodoItem();
        todoItem.setTitle("Test Todo Item");
        todoItem.setDescription("This is a test item.");
        todoItem.setCompleted(false);
        todoItem.setTodoList(todoList);

        assertNotNull(todoItem);
        assertEquals("Test Todo Item", todoItem.getTitle());
        assertEquals("This is a test item.", todoItem.getDescription());
        assertFalse(todoItem.isCompleted());
        assertEquals(todoList.getId(), todoItem.getTodoList().getId());
    }

    @Test
    public void testInvalidTodoItemCreation() {
        TodoItem todoItem = new TodoItem();
        todoItem.setTitle(""); // Título inválido
        todoItem.setDescription("This is a test item.");
        todoItem.setCompleted(false);

        // Cria uma TodoList vazia para associar (pode ser um stub)
        TodoList todoList = new TodoList();
        todoList.setUser(new User()); // Referência a um usuário que não foi salvo
        todoItem.setTodoList(todoList);

        // Valida o TodoItem
        var violations = validator.validate(todoItem);
        assertFalse(violations.isEmpty()); // Deve haver violações
    }

    @Test
    public void testToString() {
        // Criação de um User para associar à TodoList
        User user = new User();
        user.setId(1L); // Definindo um ID fictício
        user.setUsername("testUser");
        user.setPassword("password123");
        user.setEmail("test@example.com");
        user.setRole("USER");

        // Criação de uma TodoList associada ao User
        TodoList todoList = new TodoList();
        todoList.setId(1L); // Definindo um ID fictício
        todoList.setTitle("My Todo List");
        todoList.setUser(user);

        TodoItem todoItem = new TodoItem();
        todoItem.setId(1L);
        todoItem.setTitle("Test Todo Item");
        todoItem.setDescription("This is a test item.");
        todoItem.setCompleted(false);
        todoItem.setTodoList(todoList);

        String expectedString = "TodoItem{id=1, title='Test Todo Item', description='This is a test item.', completed=false, todoListId=1'}";
        assertEquals(expectedString, todoItem.toString());
    }
}

