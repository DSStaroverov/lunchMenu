package ru.dsstaroverov.lunchMenu.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.dsstaroverov.lunchMenu.AbstractTest;
import ru.dsstaroverov.lunchMenu.model.Vote;
import ru.dsstaroverov.lunchMenu.service.VoteService;
import ru.dsstaroverov.lunchMenu.to.VoteTo;
import ru.dsstaroverov.lunchMenu.web.json.JsonUtil;

import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.dsstaroverov.lunchMenu.TestUtil.*;
import static ru.dsstaroverov.lunchMenu.UserTestData.*;
import static ru.dsstaroverov.lunchMenu.util.VoteUtil.asTo;
import static ru.dsstaroverov.lunchMenu.util.VoteUtil.fromTo;


class VoteRestControllerTest extends AbstractTest {
    private static final String REST_URL = VoteRestController.REST_URL + '/';

    @Autowired
    private VoteService voteService;



    @Test
    void create() throws Exception {
        if(LocalTime.now().isBefore(LocalTime.of(11,00))){
            Vote vote = new Vote(null,USER_1.getId(),100008);
            ResultActions action = mockMvc.perform(post(REST_URL+"100008")
                    .with(userHttpBasic(USER_1))
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(JsonUtil.writeValue(vote)))
                    .andDo(print());

            VoteTo returned = readFromJson(action, VoteTo.class);
            vote.setId(returned.getId());

            assertThat(fromTo(returned)).isEqualTo(vote);
        }
    }

    @Test
    void getAllForDayWithoutDate() throws Exception {
        mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

    }

    @Test
    void getAllForDay() throws Exception {

        mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(ADMIN))
                .param("date","2019-04-29"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void getAllForUser() throws Exception {
        mockMvc.perform(get(REST_URL+"user")
                .with(userHttpBasic(USER_1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

    }

}
