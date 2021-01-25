package ru.job4j.isp;


public class StubAction implements Action {
    @Override
    public String name() {
        return "stub";
    }

    @Override
    public void act() {
        System.out.println("stub action");
    }
}