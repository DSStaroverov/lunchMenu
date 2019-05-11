package ru.dsstaroverov.lunchMenu.util;

import ru.dsstaroverov.lunchMenu.model.LunchItem;
import ru.dsstaroverov.lunchMenu.to.LunchItemTo;

public class LunchItemUtil {
    public static LunchItem fromTo(LunchItemTo item){
        return new LunchItem(item.getId(),item.getName(),null,item.getCalories());
    }

    public static LunchItemTo asTo(LunchItem item){
        return new LunchItemTo(item.getId(), item.getName(), item.getCalories(),item.getMenu().getId());
    }
}
