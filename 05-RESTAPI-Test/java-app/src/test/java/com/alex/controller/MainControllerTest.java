package com.alex.controller;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(MainController.class)
class MainControllerTest {
    @Resource
    private MockMvc mockMvc;

    StringBuilder nameBuilder = new StringBuilder();

    @BeforeEach
    public void initString() {
        for (int i = 0; i < 5; i++) {
            nameBuilder.append((char) ('a' + Math.floor(20 * Math.random())));
        }

        System.out.println(nameBuilder.toString());
    }


    @Test
    public void testGetUser() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get(String.format("/user/%s", nameBuilder.toString()))).andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());

        mockMvc.perform(get(String.format("/user/%s", nameBuilder.toString())))
                .andExpect(status().isOk())
                .andExpect(content().string(String.format("User: %s!", nameBuilder.toString())));
    }
}