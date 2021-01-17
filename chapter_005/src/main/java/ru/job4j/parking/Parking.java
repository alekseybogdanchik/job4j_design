package ru.job4j.parking;

import java.util.ArrayList;
import java.util.List;


public class Parking {
    private String[] carParking;
    private String[] truckParking;
    private int carCounter = 0;
    private int truckCounter = 0;

    public Parking(int carParkingSize, int truckParkingSize) {
        carParking = new String[carParkingSize];
        truckParking = new String[truckParkingSize];
    }

    public boolean add(Vehicle vehicle) {
        boolean rsl = false;
        if (vehicle.getType().equals("car")) {
            if (carCounter < carParking.length) {
                for (int i = 0; i < carParking.length; i++) {
                    if (carParking[i] == null) {
                        carParking[i] = vehicle.getNumberPlate();
                        carCounter++;
                        rsl = true;
                        break;
                    }
                }
            }
        }
        if (vehicle.getType().equals("truck")) {
            if (truckCounter < truckParking.length) {
                for (int i = 0; i < truckParking.length; i++) {
                    if (truckParking[i] == null) {
                        truckParking[i] = vehicle.getNumberPlate();
                        truckCounter++;
                        rsl = true;
                        break;
                    }
                }
            }
            if (!rsl && carParking.length - carCounter >= vehicle.getSize()) {
                boolean haveSpace = false;
                for (int i = 0; i < carParking.length; i++) {
                    if (carParking[i] == null) {
                        int tempStart = i;
                        int tempFinish = i + (vehicle.getSize() - 1);
                        if (tempFinish < carParking.length) {
                            haveSpace = true;
                            for (int j = tempStart + 1; j <= tempFinish; j++) {
                                if (carParking[j] != null) {
                                    haveSpace = false;
                                    break;
                                }
                            }
                        }
                        if (haveSpace) {
                            rsl = true;
                            for (int s = tempStart; s <= tempFinish; s++) {
                                carParking[s] = vehicle.getNumberPlate();
                                carCounter++;
                            }
                        }
                    }
                    if (rsl) {
                        break;
                    }
                }
            }
        }
        return rsl;
    }

    public boolean remove(String numberPlate) {
        boolean rsl = false;
        for (int i = 0; i < truckParking.length; i++) {
            if (truckParking[i] != null && truckParking[i].equals(numberPlate)) {
                truckParking[i] = null;
                truckCounter--;
                rsl = true;
                break;
            }
        }
        if (!rsl) {
            for (int i = 0; i < carParking.length; i++) {
                if (carParking[i] != null && carParking[i].equals(numberPlate)) {
                    carParking[i] = null;
                    carCounter--;
                    rsl = true;
                }
            }
        }
        return rsl;
    }

    public List<String> showAllVehicles() {
        List<String> vehiclesList = new ArrayList<>();
        for (String s : carParking) {
            vehiclesList.add(s);
        }
        for (String s : truckParking) {
            vehiclesList.add(s);
        }
        return vehiclesList;
    }
}
