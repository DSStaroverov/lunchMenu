package ru.dsstaroverov.lunchMenu.service;

import ru.dsstaroverov.lunchMenu.model.Vote;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


public interface VoteService {
    Vote save(Vote vote, LocalDateTime dateTime);
    List<Vote> getAllForDate(LocalDate date);
    List<Vote> getAllForUser(int userId);
    //Integer getMenuVoteCount(int id);
}
