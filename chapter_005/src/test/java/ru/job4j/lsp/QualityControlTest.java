package ru.job4j.lsp;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;


public class QualityControlTest {
    Storage warehouse = new Warehouse("Central st., 51");
    Storage shop = new Shop("Zavodskaya st.. 15");
    Storage trash = new Trash("Faraway st., 1b");
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
        List<Food> foods = new ArrayList<>();
        foods.add(noodle);
        QualityControl qc = new QualityControl(warehouse, shop, trash);
        qc.setCurrentTimeForTest(new GregorianCalendar(2020, Calendar.DECEMBER, 28).getTimeInMillis());
        qc.toStorage(foods);
        List<Food> wrhsList = warehouse.showFood();
        boolean wrhsRsl = wrhsList.contains(noodle);
        assertThat(wrhsRsl, is(true));
    }

    @Test
    public void whenFoodIsBetween25and75PercentExpiredThenToShop() {
        List<Food> foods = new ArrayList<>();
        foods.add(bread);
        QualityControl qc = new QualityControl(warehouse, shop, trash);
        qc.setCurrentTimeForTest(new GregorianCalendar(2020, Calendar.DECEMBER, 28).getTimeInMillis());
        qc.toStorage(foods);
        List<Food> shopList = shop.showFood();
        boolean shopRsl = shopList.contains(bread);
        boolean breadDiscount = bread.isDiscount();
        assertThat(shopRsl, is(true));
        assertThat(breadDiscount, is(false));
    }

    @Test
    public void whenFoodIsMore75PercentExpiredThenDiscount() {
        List<Food> foods = new ArrayList<>();
        foods.add(meat);
        QualityControl qc = new QualityControl(warehouse, shop, trash);
        qc.setCurrentTimeForTest(new GregorianCalendar(2020, Calendar.DECEMBER, 28).getTimeInMillis());
        qc.toStorage(foods);
        List<Food> shopList = shop.showFood();
        boolean shopRsl = shopList.contains(meat);
        boolean meatDiscount = meat.isDiscount();
        assertThat(shopRsl, is(true));
        assertThat(meatDiscount, is(true));
    }

    @Test
    public void whenFoodIsExpiredThenSendToTrash() {
        List<Food> foods = new ArrayList<>();
        foods.add(milk);
        QualityControl qc = new QualityControl(warehouse, shop, trash);
        qc.setCurrentTimeForTest(new GregorianCalendar(2020, Calendar.DECEMBER, 28).getTimeInMillis());
        qc.toStorage(foods);
        List<Food> trashList = trash.showFood();
        boolean trashRsl = trashList.contains(milk);
        assertThat(trashRsl, is(true));
    }
}
