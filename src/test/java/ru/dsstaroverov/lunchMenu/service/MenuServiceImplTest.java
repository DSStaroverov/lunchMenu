package ru.dsstaroverov.lunchMenu.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.dsstaroverov.lunchMenu.AbstractTest;
import ru.dsstaroverov.lunchMenu.model.LunchItem;
import ru.dsstaroverov.lunchMenu.model.Menu;
import ru.dsstaroverov.lunchMenu.to.MenuTo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


class MenuServiceImplTest extends AbstractTest{
    @Autowired
    private MenuService menuService;

    @Autowired
    private RestaurantService restaurantService;

    @Test
    void save() {

        List<LunchItem> items = new ArrayList<>();
        items.add(new LunchItem("new item 1",150));
        items.add(new LunchItem("new item 2",300));
        items.add(new LunchItem("new item 3",250));
        items.add(new LunchItem("new item 4",150));
        MenuTo menu = new MenuTo(null,100005, LocalDate.now(),300);

        Menu saved = menuService.save(menu);

        //log.info(menuService.getWithItems(saved.getId()).toString());
    }

    @Test
    void getMenu() {
        Menu menu = menuService.get(100007);
        //log.info(menu.toString());
    }

    @Test
    void getWithItems() {
        Menu menu = menuService.getWithItems(100007);
        //log.info(menu.toString());
    }
}