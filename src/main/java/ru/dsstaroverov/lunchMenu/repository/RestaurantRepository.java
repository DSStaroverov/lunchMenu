package ru.dsstaroverov.lunchMenu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dsstaroverov.lunchMenu.model.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant,Integer> {
}
