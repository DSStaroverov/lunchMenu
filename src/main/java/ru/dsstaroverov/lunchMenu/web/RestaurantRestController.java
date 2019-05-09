package ru.dsstaroverov.lunchMenu.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.dsstaroverov.lunchMenu.model.LunchItem;
import ru.dsstaroverov.lunchMenu.model.Menu;
import ru.dsstaroverov.lunchMenu.model.Restaurant;
import ru.dsstaroverov.lunchMenu.service.LunchItemService;
import ru.dsstaroverov.lunchMenu.service.RestaurantService;
import ru.dsstaroverov.lunchMenu.to.MenuTo;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static ru.dsstaroverov.lunchMenu.util.MenuUtil.getToList;

@RestController
@RequestMapping(value = RestaurantRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantRestController {
    static final String REST_URL = "/rest/restaurant";

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    RestaurantService restaurantService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> create(@Valid @RequestBody Restaurant restaurant) {
        log.info("create restaurant");
        Restaurant created = restaurantService.save(restaurant);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody Restaurant restaurant, @PathVariable int id){
        log.info("update restaurant with id: "+id);
        restaurantService.update(restaurant,id);
    }

    @GetMapping("/{id}")
    public Restaurant get(@PathVariable int id) {
        log.info("get restaurant with id: " + id);
        return restaurantService.get(id);
    }

    @GetMapping("/{id}/menus")
    public List<Menu> getRestaurantMenus(@PathVariable int id){
        log.info("get restaurant`s menus with id: " + id);
        return restaurantService.getWithMenus(id).getMenuList();
    }

    @GetMapping()
    public List<Restaurant> getAll(){
        log.info("get all restaurants");
        return restaurantService.getAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete restaurant with id: "+id);
        restaurantService.delete(id);
    }

}
