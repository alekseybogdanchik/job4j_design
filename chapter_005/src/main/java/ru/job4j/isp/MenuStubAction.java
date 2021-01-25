package ru.job4j.isp;


public class MenuStubAction implements Action {
    @Override
    public String name() {
        return "menu:";
    }

    @Override
    public void act() {
        System.out.println("menu stub action");
    }
}