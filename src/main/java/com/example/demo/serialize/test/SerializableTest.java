package com.example.demo.serialize.test;

import com.example.demo.serialize.entity.User;

import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;

/**
 * @author WuYingBin
 * Date 2022/6/29
 */
public class SerializableTest {
    public static void main(String[] args) {
        User user = new User();
        user.setName("tyshawn");
        user.setAge(18);
        System.out.println("序列化前的结果: " + user);

        try {
            serialize(user);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        try {
            User dUser = deserialize();
            System.out.println("反序列化后的结果: " + dUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void serialize(User user) throws Exception {
        ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(new File("D:\\WorkSpace\\User.txt").toPath()));
        oos.writeObject(user);
        oos.close();
    }

    private static User deserialize() throws Exception {
        ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(new File("D:\\WorkSpace\\User.txt").toPath()));
        return (User) ois.readObject();
    }

}
