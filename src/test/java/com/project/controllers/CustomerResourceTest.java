package com.project.controllers;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class CustomerResourceTest {

    private MockMvc mockMvc;
    @Mock
    private CustomerService customerService;
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
         mockMvc.perform(
                get("/customers/id")
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
                get("//id")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
    @Test
    public void test3GetCustomers() throws Exception
    {
         mockMvc.perform(
                get("/customers/id")
                        .accept(MediaType.APPLICATION_XML))
                .andExpect(status().isNotAcceptable());
    }
    @Test
    public void test4PostCustomers() throws Exception
    {
        String json= "{\n" +
                "  \"customers\": \"abc\",\n" +
                "  \"id\": \"123\"\n" +
                "}";
        mockMvc.perform( post( "/customers/add" )
                .contentType( MediaType.APPLICATION_JSON_VALUE )
                .content( json ))
                .andExpect( status().isOk())
                .andExpect(jsonPath("$.customers",Matchers.is("abc")))
                .andExpect(jsonPath("$.id",Matchers.is("123")));
    }
    @Test
    public void test5PostCustomersIncorrectValue() throws Exception
    {
        String json= "{\n" +
                "  \"customers\": \"abc\",\n" +
                "  \"id\": \"123\"\n" +
                "}";
        mockMvc.perform( post( "/customers/add" )
                .contentType( MediaType.APPLICATION_JSON_VALUE )
                .content( json ))
                .andExpect( status().isOk())
                .andExpect(jsonPath("$.customers",Matchers.not("ac")))
                .andExpect(jsonPath("$.id",Matchers.is("123")));
    }
    @Test
    public void test6PostCustomersFailureWrongEndPoint() throws Exception
    {
        String json= "{\n" +
                "  \"customers\": \"abc\",\n" +
                "  \"id\": \"123\"\n" +
                "}";

        mockMvc.perform( post( "/customers/ad" )
                .contentType( MediaType.APPLICATION_JSON_VALUE )
                .content( json ))
                .andExpect( status().isNotFound());
    }

}