package br.todo_list;

import br.todo_list.model.TodoItem;
import br.todo_list.repository.TodoItemRepository;
import br.todo_list.service.implementation.TodoItemService; // Supondo que este seja o nome da implementação
import br.todo_list.service.interfaces.ITodoItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
class TodoItemServiceTest {

	@Mock
	private TodoItemRepository todoItemRepository;

	// Injetar a implementação concreta, não a interface
	@InjectMocks
	private TodoItemService todoItemService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testSave() {
		TodoItem todoItem = new TodoItem();
		todoItem.setTitle("Test Title");
		todoItem.setDescription("Test Description");
		todoItem.setCompleted(false);

		// Configurar o mock para retornar o item quando salvar
		when(todoItemRepository.save(todoItem)).thenReturn(todoItem);

		// Chamar o método que será testado
		TodoItem savedTodoItem = todoItemService.createTodoItem(todoItem);

		// Verificações do teste
		assertNotNull(savedTodoItem);
		assertEquals("Test Title", savedTodoItem.getTitle());
		verify(todoItemRepository, times(1)).save(todoItem);
	}
}
