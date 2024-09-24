package br.todo_list;

import static org.junit.jupiter.api.Assertions.*;

import br.todo_list.model.User;
import br.todo_list.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.ArrayList;

@SpringBootTest
public class UserTest {

    private Validator validator;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testUserCreation() {
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("password123");
        user.setEmail("test@example.com");
        user.setRole("USER");
        user.setTodoLists(new ArrayList<>());

        assertNotNull(user);
        assertEquals("testUser", user.getUsername());
        assertEquals("password123", user.getPassword());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("USER", user.getRole());
        assertTrue(user.getTodoLists().isEmpty());
    }

    @Test
    public void testInvalidUserCreation() {
        User user = new User();
        user.setUsername("");
        user.setPassword("password123");
        user.setEmail("invalid-email");//falta o @
        user.setRole("USER");

        //validar o usuário
        var violations = validator.validate(user);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void testToString() {
        User user = new User();
        user.setId(1L);
        user.setUsername("testUser");
        user.setPassword("password123");
        user.setEmail("test@example.com");
        user.setRole("USER");

        String expectedString = "User{id=1, username='testUser', password='password123', email='test@example.com', role='USER'}";
        assertEquals(expectedString, user.toString());
    }
}
