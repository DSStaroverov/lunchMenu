package ru.dsstaroverov.lunchMenu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.dsstaroverov.lunchMenu.model.Vote;
import ru.dsstaroverov.lunchMenu.repository.VoteRepository;
import ru.dsstaroverov.lunchMenu.to.VoteTo;
import ru.dsstaroverov.lunchMenu.util.exception.EndTimeVotingException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.dsstaroverov.lunchMenu.util.VoteUtil.asTo;
import static ru.dsstaroverov.lunchMenu.web.SecurityUtil.authUserId;

@Service
@Transactional(readOnly = true)
public class VoteServiceImpl implements VoteService {
    private final LocalTime endVoting = LocalTime.of(11,00);

    @Autowired
    private VoteRepository voteRepository;

    @CacheEvict(value = "votes", allEntries = true)
    @Override
    @Transactional
    public VoteTo save(Vote vote) {
        Assert.notNull(vote,"vote must not be null");

        checkMenuDate(vote.getMenuId());
        checkTime(LocalTime.now());
        Vote checked = get(authUserId(),LocalDate.now());
        VoteTo returned;
        if(checked==null){
            returned = asTo(voteRepository.save(vote));

        }else{
            checked.setMenuId(vote.getMenuId());
            returned = asTo(voteRepository.save(checked));
        }
        returned.setCount(getMenuVoteCount(returned.getMenuId()));
        return returned;
    }

    private void checkMenuDate(int menuId) {
        LocalDate checked =voteRepository.getMenuDate(menuId);
        System.out.println(checked.toString());
        if(!checked.isEqual(LocalDate.now())){
            throw new EndTimeVotingException();
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


    public Integer getMenuVoteCount(int menuId){return voteRepository.voteCount(menuId);}
}
