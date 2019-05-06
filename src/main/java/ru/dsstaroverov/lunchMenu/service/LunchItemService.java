package ru.dsstaroverov.lunchMenu.service;

import ru.dsstaroverov.lunchMenu.model.LunchItem;
import ru.dsstaroverov.lunchMenu.model.Menu;

import java.util.List;

public interface LunchItemService {
    LunchItem save(LunchItem menu);
    void update(LunchItem menu, int id);
    LunchItem get(int id);
    List<LunchItem> getAllForMenu(int id);
    void delete(int id);
}
