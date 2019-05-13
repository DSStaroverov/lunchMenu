package ru.dsstaroverov.lunchMenu.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.dsstaroverov.lunchMenu.model.User;
import ru.dsstaroverov.lunchMenu.service.UserService;
import ru.dsstaroverov.lunchMenu.util.exception.IllegalRequestDataException;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static ru.dsstaroverov.lunchMenu.util.ValidationUtil.assureIdConsistent;
import static ru.dsstaroverov.lunchMenu.web.SecurityUtil.*;

@RestController
@RequestMapping(value = UserRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestController {

    public static final String REST_URL = "/rest/user";

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;


    @GetMapping
    public List<User> getAll() {
        if (isAdmin()) {
            log.info("getAll users");
            return userService.getAll();
        }
        throw new AccessDeniedException("");
    }


    @GetMapping("/{id}")
    public User get(@PathVariable int id) {
        if (isAdmin()) {
            log.info("get with id={}", id);
            return userService.get(id);
        }else {
            log.info("get with id={}", authUserId());
            return userService.get(authUserId());
        }

    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> create(@Valid @RequestBody User user) {
        if (isAdmin()) {
            log.info("create user");
            User created = userService.create(user);
            URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path(REST_URL + "/{id}")
                    .buildAndExpand(created.getId()).toUri();
            return ResponseEntity.created(uriOfNewResource).body(created);
        }
        throw new AccessDeniedException("");
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        if (isAdmin()) {
            log.info("delete user with id={}", id);
            userService.delete(id);
        }else {
            SecurityUtil.get().getUser().setEnabled(false);
            log.info("user with id={} set enabled false", authUserId());
            userService.update(SecurityUtil.get().getUser());
        }
    }


    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody User user, @PathVariable int id) {
        try {
            if (isAdmin()) {
                log.info("update {} with id={}", user, id);
                assureIdConsistent(user, id);
                userService.update(user);
            }else {
                assureIdConsistent(user, authUserId());
                log.info("update {} with id={}", user, authUserId());
                userService.update(user);
            }
        }catch (DataIntegrityViolationException e){
            throw new IllegalRequestDataException("email dublicate update");
        }
    }


    @GetMapping("/by")
    public User getByMail(@RequestParam String email) {
        if (isAdmin()) {
            return userService.getByEmail(email);
        }
        throw new AccessDeniedException("");
    }
}