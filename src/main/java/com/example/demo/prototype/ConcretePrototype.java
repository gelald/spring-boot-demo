package com.example.demo.prototype;

import lombok.Data;

/**
 * @author: WuYingBin
 * date: 2021/12/18
 */
@Data
public class ConcretePrototype implements Cloneable {
    private static ConcretePrototype instance = new ConcretePrototype();

    private ConcretePrototype() {
    }

    public static ConcretePrototype getInstance() {
        return instance;
    }

    @Override
    public ConcretePrototype clone() {
        try {
            return (ConcretePrototype) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}