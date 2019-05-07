package ru.dsstaroverov.lunchMenu.service;


import ru.dsstaroverov.lunchMenu.model.Menu;
import ru.dsstaroverov.lunchMenu.to.MenuTo;

import java.time.LocalDate;
import java.util.List;

public interface MenuService {
    Menu save(MenuTo menu);
    void update(MenuTo menu, int id);
    Menu get(int id);
    Menu getWithItems(int id);
    List<Menu> getAllForDate(LocalDate date);
    List<Menu> getAllForRestaurant(int id);
    void delete(int id);

}
