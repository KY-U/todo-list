package br.todo_list.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "Username cannot be blank")
    @NotEmpty
    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @NotEmpty
    @Column(nullable = false)
    private String password;

    @Email
    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private List<TodoList> todoLists;

    @NotEmpty
    @Column(nullable = false, length = 50)
    private String role;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                //", todoLists=" + todoLists +
                ", role='" + role + '\'' +
                '}';
    }
}