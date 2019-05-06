package ru.dsstaroverov.lunchMenu.util;

import ru.dsstaroverov.lunchMenu.model.Menu;
import ru.dsstaroverov.lunchMenu.model.Restaurant;
import ru.dsstaroverov.lunchMenu.to.MenuTo;

public class MenuUtil {
    public static Menu fromTo(MenuTo to) {
        return new Menu(to.getId(), to.getCreateDate(),to.getPrice(),null);

    }

    public static MenuTo asTo(Menu menu) {
        return new MenuTo(menu.getId(),menu.getRestaurant().getId(),menu.getLunchItems(),menu.getCreateDate(),menu.getPrice());

    }
}
