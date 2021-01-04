package ru.job4j.lsp;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;


public class ControlQualityTest {
    Long timeForTest = new GregorianCalendar(2020, Calendar.DECEMBER, 28).getTimeInMillis();
    Storage warehouse = new Warehouse(timeForTest);
    Storage shop = new Shop(timeForTest);
    Storage trash = new Trash(timeForTest);
    Food meat = new Meat("Beef",
            new GregorianCalendar(2020, Calendar.DECEMBER, 24),
            new GregorianCalendar(2020, Calendar.DECEMBER, 29));
    Food milk = new Milk("Domik v derevne",
            new GregorianCalendar(2020, Calendar.DECEMBER, 15),
            new GregorianCalendar(2020, Calendar.DECEMBER, 27));
    Food bread = new Bread("Rzhanoy",
            new GregorianCalendar(2020, Calendar.DECEMBER, 27),
            new GregorianCalendar(2020, Calendar.DECEMBER, 31));
    Food noodle = new Noodle("Makfa",
            new GregorianCalendar(2020, Calendar.DECEMBER, 27),
            new GregorianCalendar(2021, Calendar.JULY, 27));

    @Test
    public void whenFoodIsLessThan25PercentExpiredThenToWrhs() {
        ControlQuality cq = new ControlQuality();
        cq.addStorage(warehouse);
        cq.addStorage(shop);
        cq.addStorage(trash);
        List<Food> foods = new ArrayList<>();
        foods.add(noodle);
        cq.distribute(foods);
        List<Food> expected = warehouse.clear();
        boolean result = expected.contains(noodle);
        assertThat(result, is(true));
    }

    @Test
    public void whenFoodIsBetween25and75PercentExpiredThenToShop() {
        ControlQuality cq = new ControlQuality();
        cq.addStorage(warehouse);
        cq.addStorage(shop);
        cq.addStorage(trash);
        List<Food> foods = new ArrayList<>();
        foods.add(bread);
        cq.distribute(foods);
        List<Food> expected = shop.clear();
        boolean shopRsl = expected.contains(bread);
        boolean breadDiscount = bread.isDiscount();
        assertThat(shopRsl, is(true));
        assertThat(breadDiscount, is(false));
    }

    @Test
    public void whenFoodIsMore75PercentExpiredThenDiscount() {
        ControlQuality cq = new ControlQuality();
        cq.addStorage(warehouse);
        cq.addStorage(shop);
        cq.addStorage(trash);
        List<Food> foods = new ArrayList<>();
        foods.add(meat);
        cq.distribute(foods);
        List<Food> expected = shop.clear();
        boolean shopRsl = expected.contains(meat);
        boolean meatDiscount = meat.isDiscount();
        assertThat(shopRsl, is(true));
        assertThat(meatDiscount, is(true));
    }

    @Test
    public void whenFoodIsExpiredThenSendToTrash() {
        ControlQuality cq = new ControlQuality();
        cq.addStorage(warehouse);
        cq.addStorage(shop);
        cq.addStorage(trash);
        List<Food> foods = new ArrayList<>();
        foods.add(milk);
        cq.distribute(foods);
        List<Food> expected = trash.clear();
        boolean trashRsl = expected.contains(milk);
        assertThat(trashRsl, is(true));
    }
}
