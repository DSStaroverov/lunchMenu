package ru.dsstaroverov.lunchMenu.util;

import ru.dsstaroverov.lunchMenu.model.LunchItem;
import ru.dsstaroverov.lunchMenu.model.Menu;
import ru.dsstaroverov.lunchMenu.to.MenuTo;

import java.util.ArrayList;
import java.util.List;

public class MenuUtil {
    public static Menu fromTo(MenuTo to) {
        return new Menu(to.getId(), to.getCreateDate(),to.getPrice(),null);

    }

    public static MenuTo asTo(Menu menu) {
        double totalCalories = menu.getLunchItems().stream().mapToDouble(LunchItem::getCalories).sum();
        return new MenuTo(menu.getId(),menu.getRestaurant().getId(),menu.getCreateDate(),menu.getPrice(),totalCalories);

    }

    public static List<MenuTo> getToList(List<Menu> list){
        List<MenuTo> returned = new ArrayList<>();
        list.forEach(menu -> returned.add(asTo(menu)));
        return returned;
    }
}
