package ru.dsstaroverov.lunchMenu.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.dsstaroverov.lunchMenu.AbstractTest;
import ru.dsstaroverov.lunchMenu.model.Restaurant;
import ru.dsstaroverov.lunchMenu.service.RestaurantService;
import ru.dsstaroverov.lunchMenu.web.json.JsonUtil;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.dsstaroverov.lunchMenu.TestUtil.readFromJson;
import static ru.dsstaroverov.lunchMenu.TestUtil.userHttpBasic;
import static ru.dsstaroverov.lunchMenu.UserTestData.ADMIN;
import static ru.dsstaroverov.lunchMenu.UserTestData.USER_1;


class RestaurantRestControllerTest extends AbstractTest {
    private static final String REST_URL = RestaurantRestController.REST_URL + '/';

    @Autowired
    private RestaurantService restaurantService;



    @Test
    void createRestaurant() throws Exception {
        Restaurant restaurant = new Restaurant("new Rest","У черта на куличках");
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(restaurant))
                .with(userHttpBasic(ADMIN)));

        Restaurant returned = readFromJson(action, Restaurant.class);
        restaurant.setId(returned.getId());

        assertThat(returned).isEqualTo(restaurant);

    }

    @Test
    void getAllRestaurant() throws Exception{
        mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }


    @Test
    void getRestaurant() throws Exception {
        mockMvc.perform(get(REST_URL+"100005")
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

    }

    @Test
    void getRestaurantMenus() throws Exception {
        mockMvc.perform(get(REST_URL+"100005/menus")
                .with(userHttpBasic(USER_1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

    }

    @Test
    void updateRestaurant() throws Exception {
        Restaurant restaurant = new Restaurant(100005,"new Rest","У черта на куличках");

        mockMvc.perform(put(REST_URL + "100005")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(restaurant))
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isNoContent());


        assertThat(restaurantService.get(100005)).isEqualTo(restaurant);
    }

    @Test
    void deleteRestaurant() throws Exception{
        mockMvc.perform(delete(REST_URL+"100005")
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

}