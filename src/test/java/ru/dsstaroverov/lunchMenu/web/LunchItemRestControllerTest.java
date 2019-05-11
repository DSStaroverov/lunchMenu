package ru.dsstaroverov.lunchMenu.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.dsstaroverov.lunchMenu.AbstractTest;
import ru.dsstaroverov.lunchMenu.model.LunchItem;
import ru.dsstaroverov.lunchMenu.service.LunchItemService;
import ru.dsstaroverov.lunchMenu.to.LunchItemTo;
import ru.dsstaroverov.lunchMenu.web.json.JsonUtil;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.dsstaroverov.lunchMenu.TestUtil.readFromJson;
import static ru.dsstaroverov.lunchMenu.TestUtil.userHttpBasic;
import static ru.dsstaroverov.lunchMenu.UserTestData.ADMIN;
import static ru.dsstaroverov.lunchMenu.util.LunchItemUtil.fromTo;


class LunchItemRestControllerTest extends AbstractTest {
    private static final String REST_URL = LunchItemRestController.REST_URL + '/';

    @Autowired
    private LunchItemService lunchItemService;


    @Test
    void createLunchItem() throws Exception {

        LunchItemTo item = new LunchItemTo(null,"Add Item",100,100007);

        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(item))
                .with(userHttpBasic(ADMIN)));

        LunchItem returned = readFromJson(action, LunchItem.class);
        item.setId(returned.getId());

        System.out.println(item.toString());
        assertThat(returned).isEqualTo(fromTo(item));

    }

    @Test
    void getLunchItem() throws Exception {
        mockMvc.perform(get(REST_URL+"100009")
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

    }

    @Test
    void updateLunchItem() throws Exception {
        LunchItemTo item = new LunchItemTo(100009,"update Item",100,100007);

        mockMvc.perform(put(REST_URL + "100009")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(item))
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isNoContent());


        assertThat(lunchItemService.get(100009)).isEqualTo(fromTo(item));
    }

    @Test
    void deleteLunchItem() throws Exception{
        mockMvc.perform(delete(REST_URL+"100009")
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

}