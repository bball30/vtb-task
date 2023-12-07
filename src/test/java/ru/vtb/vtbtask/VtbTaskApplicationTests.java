package ru.vtb.vtbtask;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.vtb.vtbtask.controllers.FrequencyController;
import ru.vtb.vtbtask.messages.FrequencyRequestDto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThan;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class VtbTaskApplicationTests {

    @Autowired
    private FrequencyController frequencyController;

    @Autowired
    private MockMvc mockMvc;

    private FrequencyRequestDto nullFrequencyRequestDto = new FrequencyRequestDto(null);
    private FrequencyRequestDto emptyFrequencyRequestDto = new FrequencyRequestDto("");
    private FrequencyRequestDto baseFrequencyRequestDto = new FrequencyRequestDto("aaaaabcccc");
    private FrequencyRequestDto engAndRusFrequencyRequestDto =
            new FrequencyRequestDto("I'm from England. А я русский!");

    @Test
    void contextLoads() {
        assertThat(frequencyController).isNotNull();
    }

    @Test
    void onNullInputTest() throws Exception {
        this.mockMvc.perform(post("/frequency")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(nullFrequencyRequestDto)))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("""
                        {
                            "error_message":"Строка не может быть null!"
                        }
                        """)
                );
    }

    @Test
    void onEmptyInputTest() throws Exception {
        this.mockMvc.perform(post("/frequency")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(emptyFrequencyRequestDto)))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("""
                        {
                            "error_message":"Строка не может быть пустой!"
                        }
                        """)
                );
    }

    @Test
    void onBaseInputTest() throws Exception {
        String responseBody = this.mockMvc.perform(post("/frequency")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(baseFrequencyRequestDto)))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                            "a":5,
                            "c":4,
                            "b":1
                        }
                        """)
                )
                .andReturn().getResponse().getContentAsString();

        // проверяем порядок
        assertThat(responseBody.indexOf("\"a\""), lessThan(responseBody.indexOf("\"b\"")));
        assertThat(responseBody.indexOf("\"a\""), lessThan(responseBody.indexOf("\"c\"")));
        assertThat(responseBody.indexOf("\"c\""), lessThan(responseBody.indexOf("\"b\"")));
    }

    @Test
    void onEngAndRusInputTest() throws Exception {
        String responseBody = this.mockMvc.perform(post("/frequency")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(engAndRusFrequencyRequestDto)))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                            " ": 5,
                            "с": 2,
                            "m": 2,
                            "n": 2,
                            "р": 1,
                            "!": 1,
                            "a": 1,
                            "у": 1,
                            "d": 1,
                            "E": 1,
                            "f": 1,
                            "g": 1,
                            "'": 1,
                            "I": 1,
                            "l": 1,
                            ".": 1,
                            "я": 1,
                            "o": 1,
                            "А": 1,
                            "r": 1,
                            "и": 1,
                            "й": 1,
                            "к": 1
                        }
                        """)
                )
                .andReturn().getResponse().getContentAsString();

        // проверяем порядок
        assertThat(responseBody.indexOf("\" \":"), lessThan(responseBody.indexOf("\"m\"")));
        assertThat(responseBody.indexOf("\"с\""), lessThan(responseBody.indexOf("\"!\"")));
        assertThat(responseBody.indexOf("\"m\""), lessThan(responseBody.indexOf("\"!\"")));
        assertThat(responseBody.indexOf("\"n\""), lessThan(responseBody.indexOf("\"!\"")));
    }
}
