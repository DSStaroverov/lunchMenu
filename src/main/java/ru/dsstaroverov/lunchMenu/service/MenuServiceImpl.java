package ru.dsstaroverov.lunchMenu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.dsstaroverov.lunchMenu.model.Menu;
import ru.dsstaroverov.lunchMenu.repository.MenuRepository;
import ru.dsstaroverov.lunchMenu.repository.RestaurantRepository;
import ru.dsstaroverov.lunchMenu.to.MenuTo;

import java.time.LocalDate;
import java.util.List;

import static ru.dsstaroverov.lunchMenu.util.MenuUtil.fromTo;
import static ru.dsstaroverov.lunchMenu.util.ValidationUtil.checkNotFound;
import static ru.dsstaroverov.lunchMenu.util.ValidationUtil.checkNotFoundWithId;

@Service
@Transactional(readOnly = true)
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;


    @CacheEvict(value = "menus", allEntries = true)
    @Override
    @Transactional
    public Menu save(MenuTo menu){
        Assert.notNull(menu,"menu must not be null");

        return menuRepository.save(convertTo(menu));
    }


    @CacheEvict(value = "menus", allEntries = true)
    @Override
    @Transactional
    public void update(Menu menu, int id) {
        Assert.notNull(menu,"menu must not be null");
        menuRepository.save(checkNotFoundWithId(menu,id));
    }

    @Cacheable("menus")
    @Override
    public Menu get(int id) {
        return menuRepository.findById(id).orElse(null);
    }

    @Cacheable("menus")
    @Override
    public Menu getWithItems(int id) {
       return menuRepository.getWithItems(id);
    }

    @Cacheable("menus")
    @Override
    public List<Menu> getAllForDate(LocalDate date) {
        return menuRepository.findAllByCreateDate(date);
    }

    @Cacheable("menus")
    @Override
    public List<Menu> getAllForRestaurant(int id) {
        return menuRepository.findAllByRestaurant(id);
    }


    @CacheEvict(value = "menus", allEntries = true)
    @Override
    @Transactional
    public void delete(int id) {
        Menu deleted = get(id);
        checkNotFoundWithId(deleted,id);
        if(deleted!=null) {
            menuRepository.delete(get(id));
        }
    }

    private Menu convertTo(MenuTo menu){
        Menu converted = fromTo(menu);
        converted.setRestaurant(restaurantRepository.findById(menu.getRestaurant()).orElse(null));
        if(converted.getCreateDate()==null){
            converted.setCreateDate(LocalDate.now());
        }
        return converted;
    }
}
