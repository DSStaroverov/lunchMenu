package ru.dsstaroverov.lunchMenu.util;

import ru.dsstaroverov.lunchMenu.model.Role;
import ru.dsstaroverov.lunchMenu.model.User;
import ru.dsstaroverov.lunchMenu.to.UserTo;


public class UserUtil {
    public static User newFromTo(UserTo user){
        return new User(null,user.getName(),user.getEmail().toLowerCase(),user.getPassword(),Role.ROLE_USER);
    }

    public static UserTo asTo(User user) {
        return new UserTo(user.getId(), user.getName(), user.getEmail(), user.getPassword());
    }

    public static User updateFromTo(User user, UserTo userTo) {
        user.setName(userTo.getName());
        user.setEmail(userTo.getEmail().toLowerCase());
        user.setPassword(userTo.getPassword());
        return user;
    }
}
