package ru.dsstaroverov.lunchMenu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.dsstaroverov.lunchMenu.model.LunchItem;
import ru.dsstaroverov.lunchMenu.repository.LunchItemRepository;

import java.util.List;

import static ru.dsstaroverov.lunchMenu.util.ValidationUtil.checkNotFoundWithId;

@Service
@Transactional(readOnly = true)
public class LunchItemServiceImpl implements LunchItemService {
    @Autowired
    private LunchItemRepository lunchItemRepository;

    @CacheEvict(value = "lunchItems", allEntries = true)
    @Override
    @Transactional
    public LunchItem save(LunchItem item) {
        Assert.notNull(item,"item must not be null");
        return lunchItemRepository.save(item);
    }

    @CacheEvict(value = "lunchItems", allEntries = true)
    @Override
    @Transactional
    public void update(LunchItem item, int id) {
        Assert.notNull(item,"item must not be null");
        lunchItemRepository.save(checkNotFoundWithId(item,id));
    }

    @Override
    public LunchItem get(int id) {
        return lunchItemRepository.findById(id).orElse(null);
    }

    @Cacheable("lunchItems")
    @Override
    public List<LunchItem> getAllForMenu(int id) {
        return lunchItemRepository.findAllForMenu(id);
    }

    @CacheEvict(value = "lunchItems", allEntries = true)
    @Override
    @Transactional
    public void delete(int id) {
        LunchItem deleted = get(id);
        checkNotFoundWithId(deleted,id);
        if(deleted!=null) {
            lunchItemRepository.delete(get(id));
        }
    }
}
