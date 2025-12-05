package com.boardcamp.api.Integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.boardcamp.api.dtos.gamesDTO;
import com.boardcamp.api.repositories.CustomerRepository;
import com.boardcamp.api.repositories.GamesRepository;
import com.boardcamp.api.repositories.RentalsRepository;

import tools.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("tests")
public class GameIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private GamesRepository gamesRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RentalsRepository rentalsRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void cleanUpDb() {
        customerRepository.deleteAll();
        gamesRepository.deleteAll();
        rentalsRepository.deleteAll();
    }

    @Test
    void givenValidGame_whenPostGame_thenReturns201() throws Exception {
        gamesDTO dto = new gamesDTO("Name", "img.jpg", 3, 100);

        mockMvc.perform(post("/games")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("Name"));
    }

    @Test
    void givenInvalidGame_whenPostGame_thenReturns400() throws Exception {
        gamesDTO dto = new gamesDTO("", "img.jpg", 0, -10);

        mockMvc.perform(post("/games")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void givenDuplicatedName_whenPostGame_thenReturns409() throws Exception {
        gamesDTO dto = new gamesDTO("Name", "img.jpg", 3, 100);

        mockMvc.perform(post("/games")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());

        mockMvc.perform(post("/games")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isConflict());
    }

    @Test
    void whenGetGames_andHasData_thenReturnsList() throws Exception {
        gamesDTO dto = new gamesDTO("Name", "img.jpg", 3, 100);

        mockMvc.perform(post("/games")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/games"))
                .andExpect(status().isOk());
    }
}
