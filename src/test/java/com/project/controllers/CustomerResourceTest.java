package com.project.controllers;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class CustomerResourceTest {

    private MockMvc mockMvc;

    @InjectMocks
    private CustomerResource customerResource;

    @Before
    public void setUp() throws Exception
    {
        mockMvc = MockMvcBuilders.standaloneSetup(customerResource).build();
    }
    @Test
    public void test1GetCustomers() throws Exception
    {
        ResultActions resultActions;
        resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/customers/id")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers",Matchers.is("abc")))
                .andExpect(jsonPath("$.id",Matchers.is("123")));
    }
    @Test
    public void test2GetCustomers() throws Exception
    {
        ResultActions resultActions;
        resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("//id")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
    @Test
    public void test3GetCustomers() throws Exception
    {
        ResultActions resultActions;
        resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/customers/id")
                        .accept(MediaType.APPLICATION_XML))
                .andExpect(status().isNotAcceptable());
    }

}