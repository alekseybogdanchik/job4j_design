package ru.job4j.parking;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;


public class ParkingTest {
    Vehicle car1 = new Car("car", "a001aa");
    Vehicle car2 = new Car("car", "m002mm");
    Vehicle car3 = new Car("car", "k003kk");
    Vehicle truck1 = new Truck("truck", "t777aa", 3);
    Vehicle truck2 = new Truck("truck", "e999ee", 3);
    Vehicle bus1 = new Truck("bus", "c100to", 3);

    @Test
    public void whenOneCarAndOneTruckIsOnParking() {
        Parking parking = new Parking(1, 1);
        parking.add(car1);
        parking.add(truck1);
        List<String> rsl = parking.showAllVehicles();
        assertThat(rsl.contains(car1.getNumberPlate()), is(true));
        assertThat(rsl.contains(truck1.getNumberPlate()), is(true));
    }

    @Test
    public void whenCarsAndOneTruckIsOnParkingForCars() {
        Parking parking = new Parking(5, 1);
        parking.add(car1);
        parking.add(car2);
        parking.add(truck1);
        parking.add(bus1);
        List<String> rsl = parking.showAllVehicles();
        assertThat(rsl.contains(bus1.getNumberPlate()), is(true));
    }

    @Test
    public void whenOnlyTrucksIsOnBothParking() {
        Parking parking = new Parking(3, 1);
        parking.add(truck1);
        parking.add(truck2);
        parking.add(car1);
        parking.add(car2);
        List<String> rsl = parking.showAllVehicles();
        assertThat(rsl.contains(truck1.getNumberPlate()), is(true));
        assertThat(rsl.contains(truck2.getNumberPlate()), is(true));
        assertThat(rsl.contains(car1.getNumberPlate()), is(false));
        assertThat(rsl.contains(car2.getNumberPlate()), is(false));
    }

    @Test
    public void whenNoSpaceForTruck2OnParkingForCars() {
        Parking parking = new Parking(5, 1);
        parking.add(truck1);
        parking.add(car1);
        parking.add(car2);
        parking.add(car3);
        parking.remove(car2.getNumberPlate());
        parking.add(truck2);
        List<String> rsl = parking.showAllVehicles();
        assertThat(rsl.contains(truck1.getNumberPlate()), is(true));
        assertThat(rsl.contains(truck2.getNumberPlate()), is(false));
        assertThat(rsl.contains(car1.getNumberPlate()), is(true));
        assertThat(rsl.contains(car2.getNumberPlate()), is(false));
        assertThat(rsl.contains(car3.getNumberPlate()), is(true));
    }

    @Test
    public void whenRemoveTruck2FromCarParking() {
        Parking parking = new Parking(5, 1);
        parking.add(truck1);
        parking.add(car1);
        parking.add(car2);
        parking.add(truck2);
        List<String> rsl = parking.showAllVehicles();
        assertThat(rsl.contains(truck1.getNumberPlate()), is(true));
        assertThat(rsl.contains(truck2.getNumberPlate()), is(true));
        assertThat(rsl.contains(car1.getNumberPlate()), is(true));
        assertThat(rsl.contains(car2.getNumberPlate()), is(true));
        boolean removed = parking.remove(truck2.getNumberPlate());
        assertThat(removed, is(true));
        rsl = parking.showAllVehicles();
        assertThat(rsl.contains(truck2.getNumberPlate()), is(false));
    }

    @Test
    public void whenBothTrucksOnCarParking() {
        Parking parking = new Parking(6, 0);
        parking.add(truck1);
        parking.add(truck2);
        parking.add(car1);
        List<String> rsl = parking.showAllVehicles();
        assertThat(rsl.contains(truck1.getNumberPlate()), is(true));
        assertThat(rsl.contains(truck2.getNumberPlate()), is(true));
        assertThat(rsl.contains(car1.getNumberPlate()), is(false));
    }
}
