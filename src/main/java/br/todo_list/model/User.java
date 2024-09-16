package br.todo_list.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "app_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @NotEmpty
    @Column(nullable = false)
    private String password;

    @Email
    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @OneToMany(mappedBy = "user")
    private List<TodoList> todoLists;
}