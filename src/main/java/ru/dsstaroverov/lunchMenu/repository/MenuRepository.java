package ru.dsstaroverov.lunchMenu.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.dsstaroverov.lunchMenu.model.Menu;


import java.time.LocalDate;
import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu,Integer> {


    @EntityGraph(attributePaths = {"lunchItems"}, type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT menu FROM Menu menu where menu.id=:id")
    Menu getWithItems(@Param("id") int id);

    @Query("SELECT menu FROM Menu menu WHERE menu.createDate=:date")
    List<Menu> findAllByCreateDate(@Param("date") LocalDate date);

    @Query("SELECT menu FROM Menu menu WHERE menu.restaurant.id=:id ORDER BY menu.createDate DESC")
    List<Menu> findAllByRestaurant(@Param("id") int id);

}

