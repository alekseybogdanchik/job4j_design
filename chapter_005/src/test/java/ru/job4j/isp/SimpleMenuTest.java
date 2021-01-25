package ru.job4j.isp;

import org.junit.Assert;
import org.junit.Test;


public class SimpleMenuTest {

    @Test
    public void whenUnOrderedOut() {
        StubAction stub = new StubAction();
        MenuStubAction menuStub = new MenuStubAction();
        SimpleMenu<String> menu = new SimpleMenu<>("A", menuStub);
        menu.add("A", "B", stub);
        menu.add("B", "C", stub);
        menu.add("B", "C1", stub);
        menu.add("A", "D", stub);
        String out = menu.unOrdered();
        System.out.println(out);
        String expect = String.format(
                "%s%n%s%n%s%n%s%n%s%n",
                "A menu:",
                "- B stub",
                "-- C stub",
                "-- C1 stub",
                "- D stub"
        );
        Assert.assertEquals(expect, out);
    }

    @Test
    public void whenOrderedOut() {
        StubAction stub = new StubAction();
        SimpleMenu<String> menu = new SimpleMenu<>("A", stub);
        menu.add("A", "B", stub);
        menu.add("B", "C", stub);
        menu.add("A", "D", stub);
        String out = menu.ordered();
        System.out.println(out);
        String expect = String.format(
                "%s%n%s%n%s%n%s%n",
                "stub",
                "1. stub",
                "1.1. stub",
                "2. stub"
        );
        Assert.assertEquals(expect, out);
    }
}
