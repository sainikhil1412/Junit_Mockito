package org.example.business;

import org.example.data.api.TodoService;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.hamcrest.MatcherAssert.*;

class TodoBusinessImplMockTest {
    @Test
    public void usingMockito() {
        TodoService todoServiceMock = Mockito.mock(TodoService.class);
        List<String> allTodos = Arrays.asList("Learn Spring MVC",
                "Learn Spring", "Learn to Dance");
        when(todoServiceMock.retrieveTodos("Ranga")).thenReturn(allTodos);
        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);
        List<String> todos = todoBusinessImpl
                .retrieveTodosRelatedToSpring("Ranga");
        assertEquals(2, todos.size());
    }

    @Test
    public void usingMockito_usingBDD() {
        TodoService todoServiceMock = Mockito.mock(TodoService.class);
        List<String> allTodos = Arrays.asList("Learn Spring MVC",
                "Learn Spring", "Learn to Dance");
        given(todoServiceMock.retrieveTodos("Ranga")).willReturn(allTodos);
        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);
        List<String> todos = todoBusinessImpl
                .retrieveTodosRelatedToSpring("Ranga");
        assertEquals(2, todos.size());
    }

    @Test
    void deleteTodosNotRelatedToSpring(){
        TodoService todoServiceMock = mock(TodoService.class);
        List<String> allTodos = Arrays.asList("Learn Spring MVC",
                "Learn Spring", "Learn to Dance");
        when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(allTodos);
        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);
        todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");
        verify(todoServiceMock , times(1)).deleteTodo("Learn to Dance");
        verify(todoServiceMock,never()).deleteTodo("Learn Spring");
        verify(todoServiceMock , atLeast(1)).deleteTodo("Learn to Dance");


    }

    @Test
    void captureArgument(){
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);

        TodoService todoServiceMock = mock(TodoService.class);
        List<String> allTodos = Arrays.asList("Learn Spring MVC",
                "Learn Spring", "Learn to Dance");
        when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(allTodos);
        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);
        todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");

        verify(todoServiceMock).deleteTodo(argumentCaptor.capture());
        assertEquals("Learn to Dance",argumentCaptor.getValue());

    }

    @Test
    void captureArgumentMultipleTimes(){
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);

        TodoService todoServiceMock = mock(TodoService.class);
        List<String> allTodos = Arrays.asList("Learn Spring MVC",
                "Learn Spring", "Learn to Dance" , "Learn to Swim");
        when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(allTodos);
        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);
        todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");

        verify(todoServiceMock,times(2)).deleteTodo(argumentCaptor.capture());
        assertEquals(2,argumentCaptor.getAllValues().size());
    }

}