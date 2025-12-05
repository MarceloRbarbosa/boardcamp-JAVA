package com.boardcamp.api.Integration;

<<<<<<< HEAD
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.boardcamp.api.dtos.rentalsDTO;
=======
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
>>>>>>> c41f94755857be919b0cb40883556f41916d3e5e

import com.boardcamp.api.repositories.CustomerRepository;
import com.boardcamp.api.repositories.GamesRepository;
import com.boardcamp.api.repositories.RentalsRepository;

<<<<<<< HEAD
import tools.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("tests")
public class RentalIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private GamesRepository gamesRepository;
=======
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class RentalIntegrationTests {

    @Autowired
    private TestRestTemplate restTemplate;
>>>>>>> c41f94755857be919b0cb40883556f41916d3e5e

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
<<<<<<< HEAD
    private RentalsRepository rentalsRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void cleanUpDb() throws Exception {
        customerRepository.deleteAll();
        gamesRepository.deleteAll();
        rentalsRepository.deleteAll();

    }

    @Test
    void givenValidRental_whenPostRental_thenReturns201() throws Exception {
        rentalsDTO dto = new rentalsDTO(null, null, 0);

        mockMvc.perform(post("/rentals")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void givenInvalidRental_whenPostRental_thenReturns400() throws Exception {
        rentalsDTO dto = new rentalsDTO(null, null, 0);

        mockMvc.perform(post("/rentals")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenDeleteRental_andNotExists_thenReturns404() throws Exception {
        mockMvc.perform(delete("/rentals/99999999"))
                .andExpect(status().isNotFound());
=======
    private GamesRepository gamesRepository;

    @Autowired
    private RentalsRepository rentalsRepository;

    @BeforeEach
    void cleanUpDb() {
        customerRepository.deleteAll();
        gamesRepository.deleteAll();
        rentalsRepository.deleteAll();
>>>>>>> c41f94755857be919b0cb40883556f41916d3e5e
    }
}
