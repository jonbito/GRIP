package com.bishopsoft.grip.api.account;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.flywaydb.core.internal.util.StringUtils;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(AccountController.class)
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @Test
    void signUp_ShouldErrorOnBlankRole() throws Exception {
        // GIVEN
        AccountSignupBindingModel accountSignupBindingModel = new AccountSignupBindingModel();
        accountSignupBindingModel.setWhosUsing("asdf");
        String json = new ObjectMapper().writeValueAsString(accountSignupBindingModel);

        // WHEN
        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/account")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
        // THEN
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("must not be blank")));
    }

    @Test
    void signUp_ShouldErrorOnBlankWhosUsing() throws Exception {
        // GIVEN
        AccountSignupBindingModel accountSignupBindingModel = new AccountSignupBindingModel();
        accountSignupBindingModel.setRole("asdf");
        String json = new ObjectMapper().writeValueAsString(accountSignupBindingModel);

        // WHEN
        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/account")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                // THEN
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("must not be blank")));
    }

    @Test
    void signUp_ShouldReturnOk() throws Exception {
        // GIVEN
        AccountSignupBindingModel accountSignupBindingModel = new AccountSignupBindingModel();
        accountSignupBindingModel.setWhosUsing("asdf");
        accountSignupBindingModel.setRole("asdf");
        String json = new ObjectMapper().writeValueAsString(accountSignupBindingModel);

        // WHEN
        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/account")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
        // THEN
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
