package ru.job4j.forum.controller;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.parameters.P;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.forum.Main;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.Topic;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.AccessService;
import ru.job4j.forum.service.PostService;
import ru.job4j.forum.service.TopicService;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc(addFilters = false)
public class PostControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PostService postService;
    @MockBean
    private TopicService topicService;
    @MockBean
    private AccessService accessService;

    @Test
    @WithMockUser
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/post"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("post"));
    }

    @Test
    @WithMockUser
    public void whenCreateNewPost() throws Exception {
        this.mockMvc.perform(post("/post")
                .param("description", "Куплю ладу-грант. Дорого.")
                .param("topicId", "1"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Post> argument = ArgumentCaptor.forClass(Post.class);
        verify(postService).saveOrUpdatePost(argument.capture());
        verify(topicService).addPostToTopic(argument.capture(), anyInt());
        assertThat(argument.getValue().getDescription(), is("Куплю ладу-грант. Дорого."));
    }

    @Test
    @WithMockUser
    public void whenEditExistingPost() throws Exception {
        this.mockMvc.perform(post("/post")
                .param("description", "Куплю ладу-грант. Дорого.")
                .param("id", "1")
                .param("topicId", "1"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Post> argument = ArgumentCaptor.forClass(Post.class);
        verify(postService).saveOrUpdatePost(argument.capture());
        verify(topicService, never()).addPostToTopic(argument.capture(), anyInt());
        assertThat(argument.getValue().getDescription(), is("Куплю ладу-грант. Дорого."));
    }
}