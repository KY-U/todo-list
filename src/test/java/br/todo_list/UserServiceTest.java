package br.todo_list;

import br.todo_list.model.User;
import br.todo_list.repository.UserRepository;
import br.todo_list.service.implementation.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private User user;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");
    }

    @Test
    public void testCreateUser() {
        // configuração
        String encodedPassword = "encodedPassword";
        user.setPassword(encodedPassword);
        when(passwordEncoder.encode(user.getPassword())).thenReturn(encodedPassword);
        when(userRepository.save(user)).thenReturn(user);

        // ação
        User createdUser = userService.createUser(user);

        // validação
        assertEquals(user.getEmail(), createdUser.getEmail());
        assertEquals(encodedPassword, createdUser.getPassword());
    }

    @Test
    public void testGetUserByEmail() {
        // configuração
        when(userRepository.findByEmail("test@example.com")).thenReturn(user);

        // ação
        User foundUser = userService.getUserByEmail("test@example.com");

        // validação
        assertEquals(user, foundUser);
    }
}
