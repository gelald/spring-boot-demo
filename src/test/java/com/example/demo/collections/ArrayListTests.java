package com.example.demo.collections;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

/**
 * @author WuYingBin
 * date: 2022/3/13
 */
@SpringBootTest
public class ArrayListTests {
    @Test
    public void testRemove() {
        Integer[] array = {1, 2, 3, 4, 5};
        String s = Arrays.toString(array);
        System.out.println(s);
        System.arraycopy(array, 3, array, 2, 2);
        String modify = Arrays.toString(array);
        System.out.println(modify);
    }
}
