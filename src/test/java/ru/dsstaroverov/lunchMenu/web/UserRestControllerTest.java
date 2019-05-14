package ru.dsstaroverov.lunchMenu.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.dsstaroverov.lunchMenu.AbstractTest;
import ru.dsstaroverov.lunchMenu.model.User;
import ru.dsstaroverov.lunchMenu.service.UserService;
import ru.dsstaroverov.lunchMenu.to.UserTo;
import ru.dsstaroverov.lunchMenu.web.json.JsonUtil;



import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.dsstaroverov.lunchMenu.TestUtil.readFromJson;
import static ru.dsstaroverov.lunchMenu.TestUtil.userHttpBasic;
import static ru.dsstaroverov.lunchMenu.UserTestData.*;
import static ru.dsstaroverov.lunchMenu.util.UserUtil.*;

class UserRestControllerTest extends AbstractTest {

    private static final String REST_URL = UserRestController.REST_URL + '/';

    @Autowired
    private UserService userService;

    @Test
    void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + ADMIN_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                // https://jira.spring.io/browse/SPR-14472
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(ADMIN));
    }

    @Test
    void testGetNotFound() throws Exception {
        mockMvc.perform(get(REST_URL + 1)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isUnprocessableEntity())
                .andDo(print());
    }

    @Test
    void testGetByEmail() throws Exception {
        mockMvc.perform(get(REST_URL + "by?email=" + ADMIN.getEmail())
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(ADMIN));
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + USER_ID_1)
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertMatch(userService.getAll(), USER_2,USER_3,USER_4,ADMIN);
    }

    @Test
    void testEnabledFalse() throws Exception {
        User actual = new User (USER_1);
        actual.setEnabled(false);
        mockMvc.perform(delete(REST_URL + USER_ID_1)
                .with(userHttpBasic(USER_1)))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertMatch(actual, userService.get(USER_ID_1));
    }

    @Test
    void testDeleteNotFound() throws Exception {
        mockMvc.perform(delete(REST_URL + 1)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    void testGetUnAuth() throws Exception {
        mockMvc.perform(get(REST_URL))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void testGetForbidden() throws Exception {
        mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(USER_1)))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    void testUpdate() throws Exception {
        UserTo updatedTo = new UserTo(null, "newName", "newemail@ya.ru", "newPassword");
        mockMvc.perform(put(REST_URL + USER_ID_2)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(USER_1))
                .content(JsonUtil.writeValue(updatedTo)))
                .andExpect(status().isNoContent());

        assertMatch(userService.getByEmail("newemail@ya.ru"), updateFromTo(new User(USER_1), updatedTo));
    }

    @Test
    void testCreate() throws Exception {
        UserTo createdTo = new UserTo(null, "UserNew", "usernew@yandex.ru", "newPass");
        ResultActions action = mockMvc.perform(post(REST_URL+"register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(createdTo)))
                .andExpect(status().isCreated());

        User returned = readFromJson(action, User.class);

        User created = newFromTo(createdTo);
        created.setId(returned.getId());

        assertMatch(returned, created);
        assertMatch(userService.getAll(), USER_1,USER_2,USER_3,USER_4,ADMIN,created);
    }

    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(USER_1,USER_2,USER_3,USER_4,ADMIN));
    }
}