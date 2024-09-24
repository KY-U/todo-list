package br.todo_list;

import br.todo_list.model.TodoItem;
import br.todo_list.repository.TodoItemRepository;
import br.todo_list.service.implementation.TodoItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TodoItemServiceTest {

	@InjectMocks
	private TodoItemService todoItemService;

	@Mock
	private TodoItemRepository todoItemRepository;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testCreateTodoItem() {
		TodoItem newItem = new TodoItem();
		newItem.setId(1L);
		newItem.setTitle("Task 1");

		when(todoItemRepository.save(any(TodoItem.class))).thenReturn(newItem);

		TodoItem createdItem = todoItemService.createTodoItem(newItem);

		assertEquals(newItem.getId(), createdItem.getId());
		assertEquals(newItem.getTitle(), createdItem.getTitle());
		verify(todoItemRepository, times(1)).save(any(TodoItem.class));
	}

	@Test
	public void testGetTodoItem() {
		TodoItem todoItem = new TodoItem();
		todoItem.setId(1L);

		when(todoItemRepository.findById(1L)).thenReturn(Optional.of(todoItem));

		Optional<TodoItem> foundItem = todoItemService.getTodoItem(1L);

		assertEquals(1L, foundItem.get().getId());
		verify(todoItemRepository, times(1)).findById(1L);
	}

	@Test
	public void testGetCompletedItemsByListId() {
		TodoItem item1 = new TodoItem();
		item1.setCompleted(true);
		TodoItem item2 = new TodoItem();
		item2.setCompleted(true);

		List<TodoItem> completedItems = Arrays.asList(item1, item2);

		when(todoItemRepository.findByTodoListIdAndCompletedTrue(1L)).thenReturn(completedItems);

		List<TodoItem> result = todoItemService.getCompletedItemsByListId(1L);

		assertEquals(2, result.size());
		assertEquals(true, result.get(0).isCompleted());
		verify(todoItemRepository, times(1)).findByTodoListIdAndCompletedTrue(1L);
	}

	@Test
	public void testGetIncompleteItemsByListId() {
		TodoItem item1 = new TodoItem();
		item1.setCompleted(false);
		TodoItem item2 = new TodoItem();
		item2.setCompleted(false);

		List<TodoItem> incompleteItems = Arrays.asList(item1, item2);

		when(todoItemRepository.findByTodoListIdAndCompletedFalse(1L)).thenReturn(incompleteItems);

		List<TodoItem> result = todoItemService.getIncompleteItemsByListId(1L);

		assertEquals(2, result.size());
		assertEquals(false, result.get(0).isCompleted());
		verify(todoItemRepository, times(1)).findByTodoListIdAndCompletedFalse(1L);
	}

	@Test
	public void testCompleteTodoItem() {
		TodoItem todoItem = new TodoItem();
		todoItem.setId(1L);
		todoItem.setCompleted(false);

		when(todoItemRepository.findById(1L)).thenReturn(Optional.of(todoItem));
		when(todoItemRepository.save(any(TodoItem.class))).thenReturn(todoItem);

		TodoItem completedItem = todoItemService.completeTodoItem(1L);

		assertEquals(true, completedItem.isCompleted());
		verify(todoItemRepository, times(1)).findById(1L);
		verify(todoItemRepository, times(1)).save(todoItem);
	}
}
