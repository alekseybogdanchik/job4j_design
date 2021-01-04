package ru.job4j.lsp;

import java.util.ArrayList;
import java.util.List;


public class Trash implements Storage {
    private List<Food> foods = new ArrayList<>();
    private Long currentTime;

    public Trash() {
        this.currentTime = System.currentTimeMillis();
    }

    public Trash(Long currentTime) {
        this.currentTime = currentTime;
    }

    @Override
    public void addFood(Food food) {
        foods.add(food);
    }

    @Override
    public boolean accept(Food food) {
        boolean result = false;
        ExpirationRatio expirationRatio = new ExpirationRatio(currentTime);
        double expRatio = expirationRatio.getExpRatio(food);
        if (expRatio >= 1) {
            result = true;
        }
        return result;
    }

    @Override
    public List<Food> clear() {
        List<Food> result = new ArrayList<>(foods);
        foods.clear();
        return result;
    }
}
