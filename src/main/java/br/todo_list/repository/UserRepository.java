package br.todo_list.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.todo_list.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);
}