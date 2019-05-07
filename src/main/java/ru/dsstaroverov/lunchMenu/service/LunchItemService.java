package ru.dsstaroverov.lunchMenu.service;

import ru.dsstaroverov.lunchMenu.model.LunchItem;


public interface LunchItemService {
    LunchItem save(LunchItem menu);
    void update(LunchItem menu, int id);
    LunchItem get(int id);
    void delete(int id);
}
