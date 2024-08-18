package org.example.business;

import org.example.data.api.TodoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class TodoBusinessImplMockitoInjectMockTest {
    @Mock
    TodoService todoServiceMock;
    @InjectMocks
    TodoBusinessImpl todoBusinessImpl;
    @Captor
    ArgumentCaptor<String> argumentCaptor;

    @Test
    public void usingMockito() {
        List<String> allTodos = Arrays.asList("Learn Spring MVC",
                "Learn Spring", "Learn to Dance");
        when(todoServiceMock.retrieveTodos("Ranga")).thenReturn(allTodos);
        List<String> todos = todoBusinessImpl
                .retrieveTodosRelatedToSpring("Ranga");
        assertEquals(2, todos.size());
    }

    @Test
    public void usingMockito_usingBDD() {
        List<String> allTodos = Arrays.asList("Learn Spring MVC",
                "Learn Spring", "Learn to Dance");
        given(todoServiceMock.retrieveTodos("Ranga")).willReturn(allTodos);
        List<String> todos = todoBusinessImpl
                .retrieveTodosRelatedToSpring("Ranga");
        assertEquals(2, todos.size());
    }

    @Test
    void deleteTodosNotRelatedToSpring(){
        List<String> allTodos = Arrays.asList("Learn Spring MVC",
                "Learn Spring", "Learn to Dance");
        when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(allTodos);
        todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");
        verify(todoServiceMock , times(1)).deleteTodo("Learn to Dance");
        verify(todoServiceMock,never()).deleteTodo("Learn Spring");
        verify(todoServiceMock , atLeast(1)).deleteTodo("Learn to Dance");


    }

    @Test
    void captureArgument(){

        List<String> allTodos = Arrays.asList("Learn Spring MVC",
                "Learn Spring", "Learn to Dance");
        when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(allTodos);
        todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");

        verify(todoServiceMock).deleteTodo(argumentCaptor.capture());
        assertEquals("Learn to Dance",argumentCaptor.getValue());

    }

    @Test
    void captureArgumentMultipleTimes(){

        List<String> allTodos = Arrays.asList("Learn Spring MVC",
                "Learn Spring", "Learn to Dance" , "Learn to Swim");
        when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(allTodos);
        todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");

        verify(todoServiceMock,times(2)).deleteTodo(argumentCaptor.capture());
        assertEquals(2,argumentCaptor.getAllValues().size());
    }

}