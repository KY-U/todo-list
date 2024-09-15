package br.todo_list.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.todo_list.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}