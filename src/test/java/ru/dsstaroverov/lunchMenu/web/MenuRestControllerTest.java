package ru.dsstaroverov.lunchMenu.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.dsstaroverov.lunchMenu.AbstractTest;

import ru.dsstaroverov.lunchMenu.model.Menu;
import ru.dsstaroverov.lunchMenu.service.MenuService;
import ru.dsstaroverov.lunchMenu.to.MenuTo;
import ru.dsstaroverov.lunchMenu.web.json.JsonUtil;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.dsstaroverov.lunchMenu.TestUtil.readFromJson;
import static ru.dsstaroverov.lunchMenu.TestUtil.userHttpBasic;
import static ru.dsstaroverov.lunchMenu.UserTestData.*;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.dsstaroverov.lunchMenu.util.MenuUtil.fromTo;


class MenuRestControllerTest extends AbstractTest {
    private static final String REST_URL = MenuRestController.REST_URL + '/';

    @Autowired
    private MenuService menuService;



    @Test
    void create() throws Exception {
        MenuTo menu = new MenuTo(null,100005, LocalDate.now(),300);
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(menu))
                .with(userHttpBasic(ADMIN)));

        Menu returned = readFromJson(action, Menu.class);
        menu.setId(returned.getId());

        assertThat(returned).isEqualTo(fromTo(menu));

    }



    @Test
    void getMenu() throws Exception {
        mockMvc.perform(get(REST_URL+"100007")
                .with(userHttpBasic(USER_1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

    }

    @Test
    void update() throws Exception {
        MenuTo menu = new MenuTo(100007,100006, LocalDate.now(),300);

        mockMvc.perform(put(REST_URL + "100007")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(menu))
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isNoContent());


        assertThat(menuService.get(100007)).isEqualTo(fromTo(menu));
    }

    @Test
    void delete() {

    }


    @Test
    void getMenuItems() throws Exception {
        mockMvc.perform(get(REST_URL+"100007/items")
                .with(userHttpBasic(USER_1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

    }

    @Test
    void getAllForDayWithoutDate() throws Exception {
        mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(USER_1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

    }

    @Test
    void getAllForDay() throws Exception {

        mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(USER_1))
                .param("date","2019-04-29"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

}