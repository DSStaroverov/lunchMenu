package ru.dsstaroverov.lunchMenu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.dsstaroverov.lunchMenu.model.LunchItem;

import java.util.List;

@Repository
public interface LunchItemRepository extends JpaRepository<LunchItem,Integer> {

    @Query("SELECT item FROM LunchItem item WHERE item.menu.id=:id")
    List<LunchItem> findAllForMenu(@Param("id") int id);
}
