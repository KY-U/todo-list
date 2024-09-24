package br.todo_list;

import static org.assertj.core.api.Assertions.assertThat;

import br.todo_list.model.User;
import br.todo_list.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // Use seu banco de dados real, se necessário
public class UserRepositoryTestOld {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    @Rollback // Rollback após cada teste para manter o banco de dados limpo
    public void setUp() {
        // Com um teste apenas, não é necessário
    }

    @Test
    public void testFindByEmail() {
        // Dado um usuário persistido no banco de dados
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("password123");
        user.setEmail("test@example.com");
        user.setRole("USER");
        userRepository.save(user);

        // Quando busco o usuário pelo email
        User foundUser = userRepository.findByEmail("test@example.com");

        // Então o usuário deve ser encontrado
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getEmail()).isEqualTo("test@example.com");
    }

    @Test
    public void testFindByEmailNotFound() {
        // Quando busco um email que não existe
        User foundUser = userRepository.findByEmail("nonexistent@example.com");

        // Então deve retornar null
        assertThat(foundUser).isNull();
    }
}

