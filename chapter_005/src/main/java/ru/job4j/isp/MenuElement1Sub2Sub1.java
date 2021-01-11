package ru.job4j.isp;

import java.util.ArrayList;
import java.util.List;


public class MenuElement1Sub2Sub1 implements MenuElement {
    private List<MenuElement> subMenuList;

    @Override
    public List<MenuElement> getSubMenuList() {
        return subMenuList;
    }

    @Override
    public void addSubMenuElement(MenuElement menuElement) {
        if (subMenuList == null) {
            subMenuList = new ArrayList<>();
        }
        subMenuList.add(menuElement);
    }

    @Override
    public String name() {
        return "Menu Element 1 of Second Section in First Chapter ";
    }

    @Override
    public boolean execute(Input input, Store store) {
        System.out.println("Do something completely special!!!");
        return true;
    }
}
