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

    Vote findByUserIdAndVoteDate(@Param("userId") int userId, @Param("voteDate") LocalDate voteDate);

    @Query("SELECT vote FROM Vote vote WHERE vote.voteDate=:date ORDER BY vote.menuId DESC")
    List<Vote> findAllForDate(@Param("date") LocalDate date);

    @Query("SELECT vote FROM Vote vote WHERE vote.userId=:userId ORDER BY vote.voteDate DESC")
    List<Vote> findAllForUser(@Param("userId") int userId);

    @Query("SELECT COUNT (vote) FROM Vote vote WHERE vote.menuId=:menuId")
    Integer voteCount(@Param("menuId") int menuId);

    @Query("SELECT menu.createDate FROM Menu menu WHERE  menu.id=:menuId")
    LocalDate getMenuDate(@Param("menuId") int menuId);
}
