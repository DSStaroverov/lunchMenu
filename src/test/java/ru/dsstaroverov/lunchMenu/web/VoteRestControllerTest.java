package ru.dsstaroverov.lunchMenu.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.dsstaroverov.lunchMenu.AbstractTest;
import ru.dsstaroverov.lunchMenu.model.Vote;
import ru.dsstaroverov.lunchMenu.service.VoteService;
import ru.dsstaroverov.lunchMenu.web.json.JsonUtil;


import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static ru.dsstaroverov.lunchMenu.TestUtil.*;
import static ru.dsstaroverov.lunchMenu.UserTestData.*;


class VoteRestControllerTest extends AbstractTest {
    private static final String REST_URL = VoteRestController.REST_URL + '/';

    @Autowired
    private VoteService voteService;



    @Test
    void create() throws Exception {
        Vote vote = new Vote(USER_1.getId(),100008);
        ResultActions action = mockMvc.perform(post(REST_URL+"100008")
                .with(userHttpBasic(USER_1))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(vote)))
                .andDo(print());

        Vote returned = readFromJson(action, Vote.class);
        vote.setId(returned.getId());

        assertThat(returned).isEqualTo(vote);

    }

}