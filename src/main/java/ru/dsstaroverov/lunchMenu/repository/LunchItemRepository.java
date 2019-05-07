package ru.dsstaroverov.lunchMenu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dsstaroverov.lunchMenu.model.LunchItem;


@Repository
public interface LunchItemRepository extends JpaRepository<LunchItem,Integer> {
}
