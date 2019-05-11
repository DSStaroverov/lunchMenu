package ru.dsstaroverov.lunchMenu.service;

import ru.dsstaroverov.lunchMenu.model.Vote;
import ru.dsstaroverov.lunchMenu.to.VoteTo;

import java.time.LocalDate;
import java.util.List;


public interface VoteService {
    VoteTo save(Vote vote);
    List<Vote> getAllForDate(LocalDate date);
    List<Vote> getAllForUser(int userId);
    Integer getMenuVoteCount(int id);
}
