package ru.dsstaroverov.lunchMenu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.dsstaroverov.lunchMenu.model.Vote;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<Vote,Integer> {
    @Query("SELECT vote FROM Vote vote WHERE vote.voteDate=:date ORDER BY vote.menu.id DESC")
    List<Vote> findAllForDate(@Param("date") LocalDate date);

    @Query("SELECT vote FROM Vote vote LEFT JOIN FETCH vote.menu WHERE vote.user.id=:userId ORDER BY vote.voteDate DESC")
    List<Vote> findAllForUser(@Param("userId") int userId);
}
