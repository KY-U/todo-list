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
			// Populando dados de usuários
			if (userRepository.findByEmail("user1@example.com") == null) {
				User user1 = new User();
				user1.setUsername("user1");
				user1.setPassword(passwordEncoder.encode("123"));
				//user1.setPassword("password1");
				user1.setEmail("user1@example.com");
				user1.setRole("USER");
				userRepository.save(user1);

				User user2 = new User();
				user2.setUsername("user2");
				user2.setPassword(passwordEncoder.encode("password2"));
				//user1.setPassword("password2");
				user2.setEmail("user2@example.com");
				user2.setRole("USER");
				userRepository.save(user2);
			}

			// Verifica se as listas de tarefas já existem
			if (todoListRepository.findByTitle("Groceries").isEmpty()) {
				User user1 = userRepository.findByEmail("user1@example.com");

				TodoList todoList1 = new TodoList();
				todoList1.setTitle("Groceries");
				todoList1.setDescription("comprinhas pro jantar");
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
				TodoList todoList2 = todoListRepository.findByTitle("Work Tasks").get(0);

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
				todoItem2.setTodoList(todoList2);
				todoItemRepository.save(todoItem2);

				TodoItem todoItem3 = new TodoItem();
				todoItem3.setTitle("Buy orange");
				todoItem3.setDescription("5 units");
				todoItem3.setCompleted(false);
				todoItem3.setTodoList(todoList1);
				todoItemRepository.save(todoItem3);
			}

			User user = userRepository.findByEmail("user1@example.com");
			System.out.println("o usuário é:" + user.getEmail());
			System.out.println("a senha é:" + user.getPassword());
			System.out.println("o id é:" + user.getId());
			//List<TodoItem> itemList = todoItemRepository.findByTodoListId(((Integer) 1).longValue());
			//System.out.println(itemList);
		};
	}
}
