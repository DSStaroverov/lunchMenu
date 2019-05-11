package ru.dsstaroverov.lunchMenu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.dsstaroverov.lunchMenu.model.LunchItem;
import ru.dsstaroverov.lunchMenu.repository.LunchItemRepository;
import ru.dsstaroverov.lunchMenu.repository.MenuRepository;
import ru.dsstaroverov.lunchMenu.to.LunchItemTo;

import static ru.dsstaroverov.lunchMenu.util.LunchItemUtil.fromTo;
import static ru.dsstaroverov.lunchMenu.util.ValidationUtil.checkNotFoundWithId;

@Service
@Transactional(readOnly = true)
public class LunchItemServiceImpl implements LunchItemService {
    @Autowired
    private LunchItemRepository lunchItemRepository;

    @Autowired
    private MenuRepository menuRepository;

    @CacheEvict(value = "lunchItems", allEntries = true)
    @Override
    @Transactional
    public LunchItem save(LunchItemTo item) {
        Assert.notNull(item,"item must not be null");
        return lunchItemRepository.save(convertTo(item));
    }

    @CacheEvict(value = "lunchItems", allEntries = true)
    @Override
    @Transactional
    public void update(LunchItemTo item, int id) {
        Assert.notNull(item,"item must not be null");
        lunchItemRepository.save(checkNotFoundWithId(convertTo(item),id));
    }

    @Cacheable("lunchItems")
    @Override
    public LunchItem get(int id) {
        return lunchItemRepository.findById(id).orElse(null);
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

    private LunchItem convertTo(LunchItemTo item){
        LunchItem converted = fromTo(item);
        converted.setMenu(menuRepository.findById(item.getMenuId()).orElse(null));
        return converted;
    }
}
