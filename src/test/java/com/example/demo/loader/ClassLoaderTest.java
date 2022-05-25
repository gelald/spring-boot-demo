package com.example.demo.loader;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author WuYingBin
 * date: 2022/3/17
 */
@SpringBootTest
public class ClassLoaderTest {

    @Test
    public void testGetClassLoader() {
        ClassLoader classLoader = this.getClass().getClassLoader();
        System.out.println(classLoader);
        System.out.println(classLoader.getParent());
        System.out.println(classLoader.getParent().getParent());
    }

    @Test
    public void testLoadClass() throws ClassNotFoundException {
        ClassLoader loader = this.getClass().getClassLoader();
        System.out.println(loader);
        // 使用ClassLoader.loadClass()来加载类，不会执行初始化块
        loader.loadClass("com.example.demo.loader.Person");
    }

    @Test
    public void testForName() throws ClassNotFoundException {
        ClassLoader loader = this.getClass().getClassLoader();
        System.out.println(loader);
        // 使用Class.forName()来加载类，默认会执行初始化块
        Class.forName("com.example.demo.loader.Person");
    }

    @Test
    public void testForNameNotInit() throws ClassNotFoundException {
        ClassLoader loader = this.getClass().getClassLoader();
        System.out.println(loader);
        // 使用Class.forName()来加载类，并指定initialize，初始化时不执行静态块
        Class.forName("com.example.demo.loader.Person", false, loader);
    }
}
