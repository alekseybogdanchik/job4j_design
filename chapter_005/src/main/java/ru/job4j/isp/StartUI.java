package ru.job4j.isp;

import java.util.ArrayList;
import java.util.List;


public class StartUI {

    public void init(Input input, Store store, ArrayList<MenuElement> actions) {
        boolean run = true;
        while (run) {
            this.showMenu(actions);
            int select = input.askInt("Select: ", actions.size());
            MenuElement action = actions.get(select);
            run = action.execute(input, store);
        }
    }

    private void showMenu(ArrayList<MenuElement> menuList) {
        boolean haveSub = false;
        List<MenuElement> actions = new ArrayList<>();
        System.out.println("Menu:");
        int index = 1;
        for (MenuElement element : menuList) {
            String space = "   ";
            String addSpace = "   ";
            System.out.println(index + " - " + element.name());
            List<MenuElement> subMenu = element.getSubMenuList();
            if (subMenu != null) {
                haveSub = subMenu.size() > 0;
            }
            index++;
            while (haveSub) {
                for (MenuElement subMenuElement : subMenu) {
                    System.out.println(index + space + " - " + subMenuElement.name());
                    List<MenuElement> nextSubMenu = subMenuElement.getSubMenuList();
                    haveSub = nextSubMenu != null;
                    if (haveSub) {
                        subMenu = nextSubMenu;
                    }
                    index++;
                }
                space = space + addSpace;
            }
        }
    }

    public static void main(String[] args) {
        Input input = new ConsoleInput();
        Input validate = new ValidateInput(input);
        Store store = new Store();
        ArrayList<MenuElement> menuList = new ArrayList<>();
        MenuElement1 menuElement1 = new MenuElement1();
        menuList.add(menuElement1);
        MenuElement1Sub1 menuElement1Sub1 = new MenuElement1Sub1();
        MenuElement1Sub2 menuElement1Sub2 = new MenuElement1Sub2();
        menuElement1.addSubMenuElement(menuElement1Sub1);
        menuElement1.addSubMenuElement(menuElement1Sub2);
        MenuElement1Sub2Sub1 menuElement1Sub2Sub1 = new MenuElement1Sub2Sub1();
        menuElement1Sub2.addSubMenuElement(menuElement1Sub2Sub1);
        MenuElement2 menuElement2 = new MenuElement2();
        menuList.add(menuElement2);
        menuList.add(new ExitAction());
        new StartUI().init(validate, store, menuList);
    }
}
