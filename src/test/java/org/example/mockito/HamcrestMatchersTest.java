package org.example.mockito;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HamcrestMatchersTest {
    @Test
    void test(){
        List<Integer> scores = Arrays.asList(99, 100, 101, 105);
        assertEquals(4,scores.size());
        assertThat(scores,hasSize(4));
        assertThat(scores,hasItems(99,100));
        MatcherAssert.assertThat(scores,Matchers.everyItem(Matchers.greaterThan(90)));

        //string
        assertThat("",isEmptyString());
        assertThat("",blankString());
    }
}
