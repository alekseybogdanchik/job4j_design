package ru.job4j.isp;

import java.util.List;


public class ExitAction implements MenuElement {
    List<MenuElement> subMenuList;

    @Override
    public List<MenuElement> getSubMenuList() {
        return null;
    }

    @Override
    public void addSubMenuElement(MenuElement menuElement) {
        subMenuList.add(menuElement);
    }

    @Override
    public String name() {
        return "--- Exit ---";
    }

    @Override
    public boolean execute(Input input, Store store) {
        System.out.println(name());
        return false;
    }
}
