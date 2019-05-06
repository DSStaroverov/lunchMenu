package ru.dsstaroverov.lunchMenu.service;

import ru.dsstaroverov.lunchMenu.model.Vote;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


public interface VoteService {
    Vote save(Vote vote);
    void update(Vote vote, int id);
    Vote get(int id);
    //Vote getWithItems(int id);
    List<Vote> getAllForDate(LocalDate date);
    List<Vote> getAllForUser(int userId);
    void delete(int id);
}
