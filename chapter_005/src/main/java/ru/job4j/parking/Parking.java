package ru.job4j.parking;


import java.util.List;

public class Parking {
    private int[] carParking;
    private int[] truckParking;

    public Parking(int carParkingSize, int truckParkingSize) {
        carParking = new int[carParkingSize];
        truckParking = new int[truckParkingSize];
    }

    public boolean add(Vehicle vehicle) {
        return false;
    }

    public Vehicle remove(String numberPlate) {
        return null;
    }

    public List<String> showAllVehicles() {
        return null;
    }
}
