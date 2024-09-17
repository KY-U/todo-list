package br.todo_list;

import br.todo_list.model.TodoItem;
import br.todo_list.model.TodoList;
import br.todo_list.model.User;
import br.todo_list.repository.TodoItemRepository;
import br.todo_list.repository.TodoListRepository;
import br.todo_list.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@SpringBootApplication
public class TodoListApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoListApplication.class, args);
	}

	//PasswordEncoder passwordEncoder
	@Bean
	public CommandLineRunner demo(UserRepository userRepository, TodoListRepository todoListRepository, TodoItemRepository todoItemRepository) {
		return (args) -> {
			// Populando dados de usuários
			if (userRepository.findByEmail("user1@example.com").isEmpty()) {
				User user1 = new User();
				user1.setUsername("user1");
				//user1.setPassword(passwordEncoder.encode("password1"));
				user1.setPassword("password1");
				user1.setEmail("user1@example.com");
				userRepository.save(user1);

				User user2 = new User();
				user2.setUsername("user2");
				//user2.setPassword(passwordEncoder.encode("password2"));
				user1.setPassword("password2");
				user2.setEmail("user2@example.com");
				userRepository.save(user2);
			}

			// Verifica se as listas de tarefas já existem
			if (todoListRepository.findByTitle("Groceries").isEmpty()) {
				User user1 = userRepository.findByEmail("user1@example.com").orElseThrow();

				TodoList todoList1 = new TodoList();
				todoList1.setTitle("Groceries");
				todoList1.setUser(user1);
				todoListRepository.save(todoList1);

				TodoList todoList2 = new TodoList();
				todoList2.setTitle("Work Tasks");
				todoList2.setUser(user1);
				todoListRepository.save(todoList2);
			}

			// Verifica se os itens de tarefas já existem
			if (todoItemRepository.findByTitle("Buy milk").isEmpty()) {
				TodoList todoList1 = todoListRepository.findByTitle("Groceries").get(0);

				TodoItem todoItem1 = new TodoItem();
				todoItem1.setTitle("Buy milk");
				todoItem1.setDescription("2 liters of milk");
				todoItem1.setCompleted(false);
				todoItem1.setTodoList(todoList1);
				todoItemRepository.save(todoItem1);

				TodoItem todoItem2 = new TodoItem();
				todoItem2.setTitle("Finish report");
				todoItem2.setDescription("Complete the annual report");
				todoItem2.setCompleted(false);
				todoItem2.setTodoList(todoList1);
				todoItemRepository.save(todoItem2);
			}

			Optional<User> user = userRepository.findByEmail("user1@example.com");
			System.out.println("o usuário é:");
			System.out.println(user.get().getEmail());
			System.out.println(user.get().getPassword());
		};
	}
}
