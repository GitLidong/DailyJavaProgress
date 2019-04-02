package com.lidong.fanxing;

public class Plate<T extends Fruit> {
    private T item;

    public Plate(T t) {
        item = t;
    }

    public void set(T t) {
        item = t;
    }

    public T get() {
        return item;
    }

}
