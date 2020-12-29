package ru.job4j.lsp;

import java.util.List;


public class QualityControl {
    private Storage warehouse;
    private Storage shop;
    private Storage trash;
    private Long currentTimeForTest;

    public QualityControl(Storage warehouse, Storage shop, Storage trash) {
        this.warehouse = warehouse;
        this.shop = shop;
        this.trash = trash;
    }

    public void setCurrentTimeForTest(Long currentTimeForTest) {
        this.currentTimeForTest = currentTimeForTest;
    }

    public void toStorage(List<Food> foods) {
        if (currentTimeForTest == 0) {
            currentTimeForTest = System.currentTimeMillis();
        }
        for (Food food:foods) {
            long createDate = food.getCreateDate().getTimeInMillis();
            long expDate = food.getExpiryDate().getTimeInMillis();
            double lifeTime = (double) (expDate - createDate);
            double timePassed = (double) (currentTimeForTest - createDate);
            if (lifeTime > timePassed) {
                double expRatio = timePassed / lifeTime;
                if (expRatio < 0.25) {
                    warehouse.addFood(food);
                } else if (expRatio >= 0.25 && expRatio < 0.75) {
                    shop.addFood(food);
                } else if (expRatio >= 0.75) {
                    food.setDiscount(true);
                    shop.addFood(food);
                }
            } else {
                trash.addFood(food);
            }
        }
    }
}
