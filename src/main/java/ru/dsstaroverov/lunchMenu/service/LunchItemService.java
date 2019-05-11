package ru.dsstaroverov.lunchMenu.service;

import ru.dsstaroverov.lunchMenu.model.LunchItem;
import ru.dsstaroverov.lunchMenu.to.LunchItemTo;


public interface LunchItemService {
    LunchItem save(LunchItemTo item);
    void update(LunchItemTo item, int id);
    LunchItem get(int id);
    void delete(int id);
}
