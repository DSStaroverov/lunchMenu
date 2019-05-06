package ru.dsstaroverov.lunchMenu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.dsstaroverov.lunchMenu.model.Vote;
import ru.dsstaroverov.lunchMenu.repository.VoteRepository;

import java.time.LocalDate;
import java.util.List;

import static ru.dsstaroverov.lunchMenu.util.ValidationUtil.checkNotFoundWithId;

@Service
@Transactional(readOnly = true)
public class VoteServiceImpl implements VoteService {
    @Autowired
    private VoteRepository voteRepository;

    @CacheEvict(value = "votes", allEntries = true)
    @Override
    @Transactional
    public Vote save(Vote vote) {
        Assert.notNull(vote,"vote must not be null");
        return voteRepository.save(vote);
    }

    @CacheEvict(value = "votes", allEntries = true)
    @Override
    @Transactional
    public void update(Vote vote, int id) {
        Assert.notNull(vote,"vote must not be null");
        voteRepository.save(checkNotFoundWithId(vote,id));
    }

    @Cacheable("votes")
    @Override
    public Vote get(int id) {
        return voteRepository.findById(id).orElse(null);
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

    @CacheEvict(value = "votes", allEntries = true)
    @Override
    @Transactional
    public void delete(int id) {
        Vote deleted = get(id);
        checkNotFoundWithId(deleted,id);
        if(deleted!=null) {
            voteRepository.delete(get(id));
        }
    }
}
