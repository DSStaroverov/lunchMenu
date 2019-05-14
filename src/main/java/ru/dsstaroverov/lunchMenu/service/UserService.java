package ru.dsstaroverov.lunchMenu.service;




import ru.dsstaroverov.lunchMenu.model.User;
import ru.dsstaroverov.lunchMenu.to.UserTo;
import ru.dsstaroverov.lunchMenu.util.exception.NotFoundException;

import java.util.List;

public interface UserService {

    User create(User user);

    void delete(int id) throws NotFoundException;

    User get(int id) throws NotFoundException;

    User getByEmail(String email) throws NotFoundException;

    void update(User user);

    void update(UserTo user);

    List<User> getAll();

    void enable(int id, boolean enable);

}