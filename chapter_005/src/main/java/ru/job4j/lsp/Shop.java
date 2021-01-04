package ru.job4j.lsp;

import java.util.ArrayList;
import java.util.List;


public class Shop implements Storage {
    private List<Food> foods = new ArrayList<>();
    private Long currentTime;

    public Shop() {
        this.currentTime = System.currentTimeMillis();
    }

    public Shop(Long currentTime) {
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
            if (expRatio < 0.75 && expRatio >= 0.25) {
                result = true;
            }
            if (expRatio < 1 && expRatio >= 0.75) {
                food.setDiscount(true);
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
