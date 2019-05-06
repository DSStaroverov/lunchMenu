package ru.dsstaroverov.lunchMenu.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.dsstaroverov.lunchMenu.model.LunchItem;
import ru.dsstaroverov.lunchMenu.model.Menu;
import ru.dsstaroverov.lunchMenu.service.LunchItemService;
import ru.dsstaroverov.lunchMenu.service.MenuService;
import ru.dsstaroverov.lunchMenu.to.MenuTo;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE;
import static ru.dsstaroverov.lunchMenu.util.MenuUtil.asTo;
import static ru.dsstaroverov.lunchMenu.util.MenuUtil.fromTo;


@RestController
@RequestMapping(value = MenuRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuRestController {

    static final String REST_URL = "/rest/menu";

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    MenuService menuService;


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Menu> create(@Valid @RequestBody MenuTo menu) {
        log.info("create");
        Menu created = menuService.save(menu);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody Menu menu, @PathVariable int id){
        log.info("update");
        menuService.update(menu,id);
    }



    @GetMapping("/{id}")
    public MenuTo get(@PathVariable int id) {
        log.info("get ");
        Menu menu = menuService.get(id);
        Integer voteCount=0;
        double totalCalories = menu.getLunchItems().stream().mapToDouble(LunchItem::getCalories).sum();
        MenuTo menuTo = asTo(menu);
        menuTo.setTotalCalories(totalCalories);
        menuTo.setVoteCount(voteCount);
        return menuTo;
    }


    @GetMapping("/{id}/lunchItems")
    public List<LunchItem> getLunchItems(@PathVariable int id) {
        log.info("getLunchItems for menu id: " + id);
        return menuService.get(id).getLunchItems();
    }

    @GetMapping
    public List<Menu> getAllForDay(@RequestParam(required = false) LocalDate date){
        log.info("getAllForDay");
        if(date==null){
            return menuService.getAllForDate(LocalDate.now());
        }else {
            return menuService.getAllForDate(date);
        }

    }

    @GetMapping("/restaurant/{id}")
    public List<Menu> getAllForRestaurant(@PathVariable int id){
        return menuService.getAllForRestaurant(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete");
        menuService.delete(id);
    }

}
