package ru.job4j.gc;

public interface Cache {

    void get(String key);

    String load(String key);
}
