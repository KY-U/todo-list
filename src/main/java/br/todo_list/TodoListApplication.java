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

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class TodoListApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoListApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(UserRepository userRepository, TodoListRepository todoListRepository, TodoItemRepository todoItemRepository, PasswordEncoder passwordEncoder) {
		return (args) -> {

			// Apaga todas as instâncias existentes antes de popular novamente
			todoItemRepository.deleteAll();
			todoListRepository.deleteAll();
			userRepository.deleteAll();


			// Populando dados de usuários
			User user1 = new User();
			user1.setUsername("user1");

			String rawPassword = "123";
			String encryptedPassword = passwordEncoder.encode(rawPassword);
			System.out.println("Senha antes:" + rawPassword);
			System.out.println("Senha depois:" + encryptedPassword);
			user1.setPassword(encryptedPassword);

			//user1.setPassword(passwordEncoder.encode("123"));
			user1.setEmail("user1@example.com");
			user1.setRole("USER");
			userRepository.save(user1);

			User user2 = new User();
			user2.setUsername("user2");
			user2.setPassword(passwordEncoder.encode("123"));
			//user2.setPassword("123");
			user2.setEmail("user2@example.com");
			user2.setRole("USER");
			userRepository.save(user2);

			User user3 = new User();
			user3.setUsername("user3");
			user3.setPassword(passwordEncoder.encode("123"));
			//user3.setPassword("123");
			user3.setEmail("user3@example.com");
			user3.setRole("USER");
			userRepository.save(user3);

			// Populando listas de tarefas para o usuário 1
			TodoList todoList1 = new TodoList();
			todoList1.setTitle("Groceries");
			todoList1.setDescription("comprinhas pro jantar");
			todoList1.setUser(user1);
			todoListRepository.save(todoList1);

			TodoList todoList2 = new TodoList();
			todoList2.setTitle("Work Tasks");
			todoList2.setUser(user1);
			todoListRepository.save(todoList2);

			TodoList todoList3 = new TodoList();
			todoList3.setTitle("Weekend Plans");
			todoList3.setUser(user1);
			todoListRepository.save(todoList3);

			TodoList todoList4 = new TodoList();
			todoList4.setTitle("Home Improvement");
			todoList4.setUser(user1);
			todoListRepository.save(todoList4);

			// Populando itens de tarefas para as listas de tarefas
			// Itens para a lista "Groceries"
			TodoItem todoItem1 = new TodoItem();
			todoItem1.setTitle("Buy milk");
			todoItem1.setDescription("2 liters of milk");
			todoItem1.setCompleted(false);
			todoItem1.setTodoList(todoList1);
			todoItemRepository.save(todoItem1);

			TodoItem todoItem2 = new TodoItem();
			todoItem2.setTitle("Buy oranges");
			todoItem2.setDescription("5 units of oranges");
			todoItem2.setCompleted(false);
			todoItem2.setTodoList(todoList1);
			todoItemRepository.save(todoItem2);

			// Itens para a lista "Work Tasks"
			TodoItem todoItem3 = new TodoItem();
			todoItem3.setTitle("Finish report");
			todoItem3.setDescription("Complete the annual report");
			todoItem3.setCompleted(false);
			todoItem3.setTodoList(todoList2);
			todoItemRepository.save(todoItem3);

			TodoItem todoItem4 = new TodoItem();
			todoItem4.setTitle("Prepare presentation");
			todoItem4.setDescription("Prepare slides for Monday's meeting");
			todoItem4.setCompleted(false);
			todoItem4.setTodoList(todoList2);
			todoItemRepository.save(todoItem4);

			// Itens para a lista "Weekend Plans"
			TodoItem todoItem5 = new TodoItem();
			todoItem5.setTitle("Go hiking");
			todoItem5.setDescription("Hiking trip with friends");
			todoItem5.setCompleted(false);
			todoItem5.setTodoList(todoList3);
			todoItemRepository.save(todoItem5);

			TodoItem todoItem6 = new TodoItem();
			todoItem6.setTitle("Watch a movie");
			todoItem6.setDescription("Movie night at home");
			todoItem6.setCompleted(false);
			todoItem6.setTodoList(todoList3);
			todoItemRepository.save(todoItem6);

			// Itens para a lista "Home Improvement"
			TodoItem todoItem7 = new TodoItem();
			todoItem7.setTitle("Paint living room");
			todoItem7.setDescription("Buy paint and repaint the living room");
			todoItem7.setCompleted(false);
			todoItem7.setTodoList(todoList4);
			todoItemRepository.save(todoItem7);

			TodoItem todoItem8 = new TodoItem();
			todoItem8.setTitle("Fix kitchen faucet");
			todoItem8.setDescription("Replace the old kitchen faucet");
			todoItem8.setCompleted(false);
			todoItem8.setTodoList(todoList4);
			todoItemRepository.save(todoItem8);

			// Verificação dos dados do usuário

			//User user = userRepository.findByEmail("user1@example.com");
			//System.out.println("O usuário é: " + user.getEmail());
			//System.out.println("A senha é: " + user.getPassword());
			//System.out.println("O id é: " + user.getId());
		};
	}
}

