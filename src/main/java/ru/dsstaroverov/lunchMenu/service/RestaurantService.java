package ru.dsstaroverov.lunchMenu.service;

import ru.dsstaroverov.lunchMenu.model.Restaurant;

import java.util.List;

public interface RestaurantService {
    Restaurant save(Restaurant restaurant);
    void update(Restaurant restaurant, int id);
    Restaurant get(int id);
    List<Restaurant> getAll();
    void delete (int id);
}
