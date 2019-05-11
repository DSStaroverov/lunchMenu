package ru.dsstaroverov.lunchMenu;

import org.springframework.test.web.servlet.ResultMatcher;
import ru.dsstaroverov.lunchMenu.model.Role;
import ru.dsstaroverov.lunchMenu.model.User;
import ru.dsstaroverov.lunchMenu.web.json.JsonUtil;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.dsstaroverov.lunchMenu.TestUtil.readFromJsonMvcResult;
import static ru.dsstaroverov.lunchMenu.TestUtil.readListFromJsonMvcResult;
import static ru.dsstaroverov.lunchMenu.model.AbstractBaseEntity.START_SEQ;


public class UserTestData {
    public static final int USER_ID_1 = START_SEQ;
    public static final int USER_ID_2 = START_SEQ+1;
    public static final int USER_ID_3 = START_SEQ+2;
    public static final int USER_ID_4 = START_SEQ+3;
    public static final int ADMIN_ID = START_SEQ + 4;

    public static final User USER_1 = new User(USER_ID_1, "User", "user@yandex.ru", "password",  Role.ROLE_USER);
    public static final User USER_2 = new User(USER_ID_2, "User2", "user2@yandex.ru", "password",  Role.ROLE_USER);
    public static final User USER_3 = new User(USER_ID_3, "User3", "user3@yandex.ru", "password",  Role.ROLE_USER);
    public static final User USER_4 = new User(USER_ID_4, "User4", "user4@yandex.ru", "password",  Role.ROLE_USER);
    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@gmail.com", "admin",  Role.ROLE_ADMIN, Role.ROLE_USER);

    public static void assertMatch(User actual, User expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "registered", "password");
    }

    public static void assertMatch(Iterable<User> actual, User... expected) {
        assertMatch(actual, List.of(expected));
    }

    public static void assertMatch(Iterable<User> actual, Iterable<User> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("registered", "password").isEqualTo(expected);
    }

    public static ResultMatcher contentJson(User... expected) {
        return result -> assertMatch(readListFromJsonMvcResult(result, User.class), List.of(expected));
    }

    public static ResultMatcher contentJson(User expected) {
        return result -> assertMatch(readFromJsonMvcResult(result, User.class), expected);
    }

    public static String jsonWithPassword(User user, String passw) {
        return JsonUtil.writeAdditionProps(user, "password", passw);
    }
}
