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
import ru.dsstaroverov.lunchMenu.service.LunchItemService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = LunchItemRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class LunchItemRestController {
    static final String REST_URL = "/rest/items";

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    LunchItemService lunchItemService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LunchItem> create(@Valid @RequestBody LunchItem item) {
        log.info("create");
        LunchItem created = lunchItemService.save(item);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody LunchItem item, @PathVariable int id){
        log.info("update");
        lunchItemService.update(item,id);
    }



    @GetMapping("/menu/{id}")
    public List<LunchItem> getAllForMenu(@PathVariable int id){
        log.info("getAllForMenu");
        return lunchItemService.getAllForMenu(id);
    }
}
