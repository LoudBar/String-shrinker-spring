package ru.loudbar.controllers;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.loudbar.StringShrinkerSpringApplication;
import ru.loudbar.dao.StringRepo;
import ru.loudbar.services.StringService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = StringShrinkerSpringApplication.class)
@AutoConfigureMockMvc
class StringControllerTest {

    @Autowired private MockMvc mockMvc;
    @MockBean private StringService stringService;


    @Test
    void getMainPage() throws Exception {
        this.mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("main_page"));
    }

    @Test
    void shrinkStringSuccess() throws Exception {

        String out = "b";

        BDDMockito.given(stringService.encode(anyString())).willReturn(out);

        this.mockMvc.perform(post("/shrink")
                                     .param("string", "qwerty")
                                     .flashAttr("out", out))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("main_page"));

    }

    @Test
    void unshrinkString() throws Exception {

        String out = "any";

        this.mockMvc.perform(post("/unshrink")
                                     .param("string", "qwerty")
                                     .flashAttr("out", out))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("main_page"));

    }


}