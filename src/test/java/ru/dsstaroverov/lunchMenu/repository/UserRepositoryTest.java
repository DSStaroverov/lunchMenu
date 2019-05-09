package ru.dsstaroverov.lunchMenu.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.dsstaroverov.lunchMenu.AbstractTest;
import ru.dsstaroverov.lunchMenu.model.User;

import java.util.List;

class UserRepositoryTest extends AbstractTest{

    @Autowired
    private UserRepository repository;

    @Test
    void getAll() throws Exception {
        List<User> all = repository.findAll();
        //assertMatch(all, ADMIN, USER);
    }

}