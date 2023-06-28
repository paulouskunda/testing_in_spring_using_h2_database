package com.paulous.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paulous.pojo.UsersPojo;
import com.paulous.repository.UsersRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class UsersControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UsersRepository usersRepository;



    @Test
    void fetchUserByUsernameEndpoint() throws Exception {

        mockMvc.perform(get("/api/v1/fetchUserByUsername/paul_pop")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("statusCode" , is(200)))
                .andExpect(jsonPath("message.firstName", is("paulous")));
        assert(usersRepository.findByUsername("kaySlow").isPresent());
    }

    @Test
    void addUserEndpoint() throws Exception {
        UsersPojo usersPojo=new UsersPojo("bidMore","Bid","More","admin");
        ObjectMapper mapper = new ObjectMapper();
        mockMvc.perform(post("/api/v1/addUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(usersPojo))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("statusCode" , is(201)))
                .andExpect(jsonPath("message.usersId", is(5)));

    }

    @Test
    void fetchAllUsersEndpoint() throws Exception{
        mockMvc.perform(get("/api/v1/fetchAllUsers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("statusCode" , is(200)))
                .andExpect(jsonPath("message.[0].username", is("paul_pop")));


    }
}