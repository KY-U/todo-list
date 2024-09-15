package br.todo_list;

import br.todo_list.model.TodoItem;
import br.todo_list.repository.TodoItemRepository;
import br.todo_list.service.TodoItemService;
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

	@InjectMocks
	private TodoItemService todoItemService;

	public TodoItemServiceTest() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testSave() {
		TodoItem todoItem = new TodoItem();
		todoItem.setTitle("Test Title");
		todoItem.setDescription("Test Description");
		todoItem.setCompleted(false);

		when(todoItemRepository.save(todoItem)).thenReturn(todoItem);

		TodoItem savedTodoItem = todoItemService.createTodoItem(todoItem);

		assertNotNull(savedTodoItem);
		assertEquals("Test Title", savedTodoItem.getTitle());
		verify(todoItemRepository, times(1)).save(todoItem);
	}
}
