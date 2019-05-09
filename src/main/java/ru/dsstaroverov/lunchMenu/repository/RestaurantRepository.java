package ru.dsstaroverov.lunchMenu.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.dsstaroverov.lunchMenu.model.Menu;
import ru.dsstaroverov.lunchMenu.model.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant,Integer> {
    @EntityGraph(attributePaths = {"menuList"}, type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT res FROM Restaurant res WHERE res.id=:id")
    Restaurant getWithMenus(@Param("id") int id);
}
