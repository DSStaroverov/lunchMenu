package ru.dsstaroverov.lunchMenu.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ru.dsstaroverov.lunchMenu.AbstractTest;
import ru.dsstaroverov.lunchMenu.TimingExtension;
import ru.dsstaroverov.lunchMenu.model.Menu;
import ru.dsstaroverov.lunchMenu.model.User;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class UserRepositoryTest extends AbstractTest{

    @Autowired
    private UserRepository repository;

    @Test
    void getAll() throws Exception {
        List<User> all = repository.findAll();
        //assertMatch(all, ADMIN, USER);
    }

}