package br.todo_list;

import br.todo_list.model.TodoList;
import br.todo_list.repository.TodoListRepository;
import br.todo_list.repository.TodoItemRepository;
import br.todo_list.service.implementation.TodoListService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;

class TodoListServiceTest {

    @InjectMocks
    private TodoListService todoListService;

    @Mock
    private TodoListRepository todoListRepository;

    @Mock
    private TodoItemRepository todoItemRepository;

    private TodoList todoList;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        todoList = new TodoList();
        todoList.setId(1L);
        todoList.setTitle("Test List");
        todoList.setDescription("Test Description");
    }

    @Test
    void testCreateTodoList() {
        when(todoListRepository.save(any(TodoList.class))).thenReturn(todoList);

        TodoList createdList = todoListService.createTodoList(todoList);

        assertNotNull(createdList);
        assertEquals("Test List", createdList.getTitle());
        verify(todoListRepository, times(1)).save(todoList);
    }

    @Test
    void testGetTodoList() {
        when(todoListRepository.findById(1L)).thenReturn(Optional.of(todoList));

        Optional<TodoList> foundList = todoListService.getTodoList(1L);

        assertTrue(foundList.isPresent());
        assertEquals("Test List", foundList.get().getTitle());
        verify(todoListRepository, times(1)).findById(1L);
    }

    @Test
    void testGetTodoListsByUserId() {
        when(todoListRepository.findByUserId(1L)).thenReturn(Collections.singletonList(todoList));

        List<TodoList> lists = todoListService.getTodoListsByUserId(1L);

        assertFalse(lists.isEmpty());
        assertEquals(1, lists.size());
        assertEquals("Test List", lists.get(0).getTitle());
        verify(todoListRepository, times(1)).findByUserId(1L);
    }

    @Test
    void testGetListTitle() {
        when(todoListRepository.findById(1L)).thenReturn(Optional.of(todoList));

        String title = todoListService.getListTitle(1L);

        assertEquals("Test List", title);
        verify(todoListRepository, times(1)).findById(1L);
    }

    @Test
    void testGetListDescription() {
        when(todoListRepository.findById(1L)).thenReturn(Optional.of(todoList));

        String description = todoListService.getListDescription(1L);

        assertEquals("Test Description", description);
        verify(todoListRepository, times(1)).findById(1L);
    }

    @Test
    void testDeleteTodoList() {
        doNothing().when(todoListRepository).deleteById(1L);

        todoListService.deleteTodoList(1L);

        verify(todoListRepository, times(1)).deleteById(1L);
    }
}
