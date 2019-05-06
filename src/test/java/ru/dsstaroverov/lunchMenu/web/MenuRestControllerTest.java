package ru.dsstaroverov.lunchMenu.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import ru.dsstaroverov.lunchMenu.AbstractTest;

import ru.dsstaroverov.lunchMenu.service.MenuService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.dsstaroverov.lunchMenu.TestUtil.userHttpBasic;
import static ru.dsstaroverov.lunchMenu.UserTestData.USER;


class MenuRestControllerTest extends AbstractTest {
    private static final String REST_URL = MenuRestController.REST_URL + '/';

    @Autowired
    private MenuService menuService;



    @Test
    void create() {
    }



    @Test
    void getWithItems() throws Exception {
        //MenuTo menuTo = menuRestController.getWithItems(100007);

        //log.info(menuService.getWithItems(100007).toString());

        mockMvc.perform(get(REST_URL+"100007")
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print());
                //.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

    }

    @Test
    void update() {

    }

    @Test
    void delete() {

    }


    @Test
    void getAllForDayWithoutDate() throws Exception {
        mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

    }

    @Test
    void getAllForDay() throws Exception {

        mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(USER))
                .param("date","2019-04-29"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
}