package ru.job4j.lsp;

import java.util.ArrayList;
import java.util.List;


public class ControlQuality {
    private final List<Storage> storages = new ArrayList<>();

    public void addStorage(Storage storage) {
        storages.add(storage);
    }

    public void distribute(List<Food> foods) {
        for (Food food : foods) {
            for (Storage storage : storages) {
                if (storage.accept(food)) {
                    storage.addFood(food);
                    break;
                }
            }
        }
    }
}
