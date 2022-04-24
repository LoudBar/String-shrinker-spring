package ru.loudbar.controllers;

import com.github.fppt.jedismock.RedisServer;
import lombok.RequiredArgsConstructor;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;
import redis.clients.jedis.JedisPooled;
import ru.loudbar.dao.StringRepo;
import ru.loudbar.services.StringService;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class StringControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private StringController stringController;


    @Test
    void getMainPage() throws Exception {
        this.mockMvc.perform(get("/")).andExpect(status().isOk());
    }

    @Test
    void shrinkStringSuccess() throws Exception {

        this.mockMvc.perform(post("/shrink")
                                     .param("string", "qwerty"))
                .andExpect(status().isOk());

    }

    @Test
    void unshrinkString() throws Exception {

        this.mockMvc.perform(post("/unshrink")
                                     .param("string", "b"))
                .andExpect(status().isOk());

    }


}