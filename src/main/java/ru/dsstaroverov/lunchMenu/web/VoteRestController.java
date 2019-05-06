package ru.dsstaroverov.lunchMenu.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.dsstaroverov.lunchMenu.model.Vote;
import ru.dsstaroverov.lunchMenu.service.VoteService;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;


import static ru.dsstaroverov.lunchMenu.web.SecurityUtil.authUserId;


@RestController
@RequestMapping(value = VoteRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteRestController {

    static final String REST_URL = "/rest/vote";

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private VoteService service;



    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> create(@Valid @RequestBody Vote vote) {
        log.info("create");
        Vote created = service.save(vote);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody Vote vote, @PathVariable int id){
        log.info("update");
        service.update(vote,id);
    }



    @GetMapping("/{id}")
    public Vote get(@PathVariable int id) {
        log.info("get");
        return service.get(id);
    }


    @GetMapping
    public List<Vote> getAllForDay(@RequestParam(value = "date", required = false) LocalDate date){
        log.info("getAllForDay");
        if(date==null){
            return service.getAllForDate(LocalDate.now());
        }else {
            return service.getAllForDate(date);
        }
    }

    @GetMapping("/user")
    public List<Vote> getAllForUser(){
        log.info("getAllForUser");
        return service.getAllForUser(authUserId());
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete");
        service.delete(id);
    }

}
