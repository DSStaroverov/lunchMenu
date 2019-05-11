package ru.dsstaroverov.lunchMenu.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.dsstaroverov.lunchMenu.model.Vote;
import ru.dsstaroverov.lunchMenu.service.VoteService;
import ru.dsstaroverov.lunchMenu.to.VoteTo;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;


import static ru.dsstaroverov.lunchMenu.util.VoteUtil.asTo;
import static ru.dsstaroverov.lunchMenu.web.SecurityUtil.authUserId;


@RestController
@RequestMapping(value = VoteRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteRestController {

    static final String REST_URL = "/rest/vote";

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private VoteService service;



    @PostMapping(value ="/{menuId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VoteTo> create(@PathVariable int menuId) {
        log.info("create vote");

        VoteTo created = service.save(new Vote(null,authUserId(),menuId));
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @GetMapping
    public List<Vote> getAllForDay(@RequestParam(value = "date", required = false) LocalDate date){
        log.info("getAllForDay votes");
        if(date==null){
            return service.getAllForDate(LocalDate.now());
        }else {
            return service.getAllForDate(date);
        }
    }

    @GetMapping("/user")
    public List<Vote> getAllForUser(){
        log.info("getAllForUser votes");
        return service.getAllForUser(authUserId());
    }


}
