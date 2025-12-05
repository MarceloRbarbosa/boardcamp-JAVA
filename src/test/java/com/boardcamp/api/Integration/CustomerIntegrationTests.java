package com.boardcamp.api.Integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.boardcamp.api.dtos.customersDTO;
import com.boardcamp.api.repositories.CustomerRepository;
import com.boardcamp.api.repositories.RentalsRepository;

import tools.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("tests")
public class CustomerIntegrationTests {

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private CustomerRepository customerRepository;

        @Autowired
        private RentalsRepository rentalsRepository;

        @Autowired
        private ObjectMapper objectMapper;

        @BeforeEach
        void setupClient() {
                rentalsRepository.deleteAll();
                customerRepository.deleteAll();

        }

        @Test
        void givenValidCustomer_whenPostCustomer_thenReturns201() throws Exception {
                customersDTO dto = new customersDTO("Name", "12345678999", "11111111111");

                mockMvc.perform(post("/customers")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(dto)))
                                .andExpect(status().isCreated())
                                .andExpect(jsonPath("$.id").exists())
                                .andExpect(jsonPath("$.name").value("Name"));
        }

        @Test
        void givenInvalidCustomer_whenPostCustomer_thenReturns400() throws Exception {
                customersDTO dto = new customersDTO("", "123", "999");

                mockMvc.perform(post("/customers")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(dto)))
                                .andExpect(status().isBadRequest());
        }

        @Test
        void givenRepeatedCpf_whenPostCustomer_thenReturns409() throws Exception {
                customersDTO dto = new customersDTO("Name", "12345678999", "11111111111");

                mockMvc.perform(post("/customers")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(dto)))
                                .andExpect(status().isCreated());

                mockMvc.perform(post("/customers")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(dto)))
                                .andExpect(status().isConflict());
        }

        @Test
        void whenGetAllCustomers_andHasData_thenReturnsList() throws Exception {
                customersDTO dto = new customersDTO("Name", "12345678999", "11111111111");

                mockMvc.perform(post("/customers")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(dto)))
                                .andExpect(status().isCreated());

                mockMvc.perform(get("/customers"))
                                .andExpect(status().isOk());

        }

        @Test
        void whenGetCustomerById_andExists_thenReturns200() throws Exception {
                customersDTO dto = new customersDTO("Name", "12345678999", "11111111111");

                String response = mockMvc.perform(post("/customers")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(dto)))
                                .andExpect(status().isCreated())
                                .andReturn()
                                .getResponse()
                                .getContentAsString();

                Long id = objectMapper.readTree(response).get("id").asLong();

                mockMvc.perform(get("/customers/" + id))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.name").value("Name"));
        }

        @Test
        void whenGetCustomerById_andNotExists_thenReturns404() throws Exception {
                mockMvc.perform(get("/customers/999"))
                                .andExpect(status().isNotFound());
        }
}
