package ru.job4j.parking;


public class Vehicle {
    private String type;
    private String numberPlate;
    private int size;

    public Vehicle(String type, String numberPlate, int size) {
        this.type = type;
        this.numberPlate = numberPlate;
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
