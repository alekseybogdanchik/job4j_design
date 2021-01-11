package ru.job4j.isp;

import java.util.List;


public interface MenuElement {

    List<MenuElement> getSubMenuList();
    void addSubMenuElement(MenuElement menuElement);
    String name();
    boolean execute(Input input, Store store);
}
