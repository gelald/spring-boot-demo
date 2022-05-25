package com.example.demo.string;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

/**
 * @author WuYingBin
 * date: 2022/5/25
 */
@SpringBootTest
public class StringTest {

    @Test
    public void testIntern() {
        String s1 = new String("aaa");
        String s2 = "aaa";
        // System.out.println(s1 == s2);           // false
        assert s1 != s2;
        String s3 = s1.intern();
        // System.out.println(s3 == s2);  // true
        assert s3 == s2;
    }

    @Test
    public void testEquals() {
        String s1 = new String("hello");
        String s2 = new String("hello");

        System.out.println(s1.equals(s2));
        System.out.println(s1.hashCode() == s2.hashCode());
        System.out.println(s1 == s2);

        Set<String> a = new HashSet<>();
        a.add(s1);
        a.add(s2);
        System.out.println(a);
    }
}
