package ru.dsstaroverov.lunchMenu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.dsstaroverov.lunchMenu.model.Restaurant;
import ru.dsstaroverov.lunchMenu.repository.RestaurantRepository;

import java.util.List;

import static ru.dsstaroverov.lunchMenu.util.ValidationUtil.checkNotFoundWithId;

@Service
@Transactional(readOnly = true)
public class RestaurantServiceImpl implements RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;

    @CacheEvict(value = "restaurants", allEntries = true)
    @Override
    @Transactional
    public Restaurant save(Restaurant restaurant) {
        Assert.notNull(restaurant,"restaurant must not be null");
        return restaurantRepository.save(restaurant);
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    @Override
    @Transactional
    public void update(Restaurant restaurant, int id) {
        Assert.notNull(restaurant,"restaurant must not be null");
        restaurantRepository.save(checkNotFoundWithId(restaurant,id));
    }

    @Override
    public Restaurant get(int id) {
        return restaurantRepository.findById(id).orElse(null);
    }

    @Cacheable("restaurants")
    @Override
    public List<Restaurant> getAll() {
        return restaurantRepository.findAll(Sort.by("name"));
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    @Override
    @Transactional
    public void delete(int id) {
        Restaurant deleted = get(id);
        checkNotFoundWithId(deleted,id);
        if(deleted!=null) {
            restaurantRepository.delete(get(id));
        }
    }
}
