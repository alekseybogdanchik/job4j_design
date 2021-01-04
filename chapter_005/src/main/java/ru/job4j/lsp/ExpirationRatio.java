package ru.job4j.lsp;


public class ExpirationRatio {
    private Long currentTime;

    public ExpirationRatio(Long currentTime) {
        this.currentTime = currentTime;
    }

    public double getExpRatio(Food food) {
        long createDate = food.getCreateDate().getTimeInMillis();
        long expDate = food.getExpiryDate().getTimeInMillis();
        double lifeTime = (double) (expDate - createDate);
        double timePassed = (double) (currentTime - createDate);
        return timePassed / lifeTime;
    }
}
