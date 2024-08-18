package org.example.business;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

public class ListTest {
    @Test
    void letsMockListSizeMethod(){
        List listMock = Mockito.mock(List.class);
        when(listMock.size()).thenReturn(2);
        assertEquals(2,listMock.size());
        assertEquals(2,listMock.size());
        assertEquals(2,listMock.size());
        assertEquals(2,listMock.size());

    }

    @Test
    void letsMockListSizeMethod_returnMultipleValues(){
        List listMock = Mockito.mock(List.class);
        when(listMock.size()).thenReturn(2).thenReturn(3);
        assertEquals(2,listMock.size());

    }

    @Test
    void letsMockListGet(){
        List listMock = Mockito.mock(List.class);
        when(listMock.get(anyInt())).thenReturn("hello");
        assertEquals("hello" , listMock.get(0));
        assertEquals("hello" , listMock.get(1));
        assertEquals("hello" , listMock.get(2));
    }
}
