package model;

import common.Publisher;

public class Model<T> extends Publisher {
    private final T value;

    public Model(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}
