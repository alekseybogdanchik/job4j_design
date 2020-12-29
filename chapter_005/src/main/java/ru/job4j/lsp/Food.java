package ru.job4j.lsp;


import java.util.Calendar;


public class Food {
    String name;
    Calendar createDate;
    Calendar expiryDate;
    boolean discount;

    public Food(String name, Calendar createDate, Calendar expiryDate) {
        this.name = name;
        this.createDate = createDate;
        this.expiryDate = expiryDate;
    }

    public String getName() {
        return name;
    }

    public Calendar getCreateDate() {
        return createDate;
    }

    public Calendar getExpiryDate() {
        return expiryDate;
    }

    public boolean isDiscount() {
        return discount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreateDate(Calendar createDate) {
        this.createDate = createDate;
    }

    public void setExpiryDate(Calendar expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setDiscount(boolean discount) {
        this.discount = discount;
    }
}
