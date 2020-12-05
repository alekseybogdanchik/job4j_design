package ru.job4j.gc;

public interface Cache {

    String get(String key);

    String load(String key);
}
