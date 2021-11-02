package ru.job4j.forum.controller;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.forum.Main;
import ru.job4j.forum.model.Topic;
import ru.job4j.forum.service.AccessService;
import ru.job4j.forum.service.TopicService;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc(addFilters = false)
public class TopicControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TopicService topicService;
    @MockBean
    private AccessService accessService;

    @Test
    @WithMockUser
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/topic"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("topic"));
    }

    @Test
    @WithMockUser
    public void whenCreateNewTopic() throws Exception {
        this.mockMvc.perform(post("/topic")
                .param("name", "topicName")
                .param("description", "topicDescription"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Topic> argument = ArgumentCaptor.forClass(Topic.class);
        verify(topicService).saveOrUpdateTopic(argument.capture());
        assertThat(argument.getValue().getName(), is("topicName"));
        assertThat(argument.getValue().getDescription(), is("topicDescription"));
    }
}