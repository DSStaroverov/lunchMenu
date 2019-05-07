package ru.dsstaroverov.lunchMenu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.dsstaroverov.lunchMenu.model.Vote;
import ru.dsstaroverov.lunchMenu.repository.VoteRepository;
import ru.dsstaroverov.lunchMenu.util.exception.EndTimeVotingException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class VoteServiceImpl implements VoteService {
    private final LocalTime endVoting = LocalTime.of(12,00);

    @Autowired
    private VoteRepository voteRepository;

    @CacheEvict(value = "votes", allEntries = true)
    @Override
    @Transactional
    public Vote save(Vote vote, LocalDateTime dateTime) {
        Assert.notNull(vote,"vote must not be null");

        Vote checked = get(vote.getUserId(),dateTime.toLocalDate());
        if(checked==null){
            return voteRepository.save(vote);
        }else{
            checkTime(dateTime.toLocalTime());
            checked.setMenuId(vote.getMenuId());
            return voteRepository.save(checked);
        }

    }

    private Vote get(int userId, LocalDate voteDate){
        return voteRepository.findByUserIdAndVoteDate(userId, voteDate);
    }

    private void checkTime(LocalTime time){
        if(time.isAfter(endVoting)){
            throw new EndTimeVotingException();
        }
    }



    @Cacheable("votes")
    @Override
    public List<Vote> getAllForDate(LocalDate date) {
        return voteRepository.findAllForDate(date);
    }

    @Cacheable("votes")
    @Override
    public List<Vote> getAllForUser(int userId) {
        return voteRepository.findAllForUser(userId);
    }

}
