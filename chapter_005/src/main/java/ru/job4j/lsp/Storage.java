package ru.job4j.lsp;

import java.util.ArrayList;
import java.util.List;


public class Storage {
    List<Food> storage = new ArrayList<>();

    public void addFood(Food food) {
        storage.add(food);
    }

    public List<Food> showFood() {
        return storage;
    }
}
