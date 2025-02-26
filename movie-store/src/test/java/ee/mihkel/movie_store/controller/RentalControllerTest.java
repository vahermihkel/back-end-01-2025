package ee.mihkel.movie_store.controller;

import ee.mihkel.movie_store.entity.Film;
import ee.mihkel.movie_store.entity.Person;
import ee.mihkel.movie_store.entity.Rental;
import ee.mihkel.movie_store.repository.FilmRepository;
import ee.mihkel.movie_store.repository.PersonRepository;
import ee.mihkel.movie_store.repository.RentalRepository;
import ee.mihkel.movie_store.service.RentalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RentalControllerTest {

    private MockMvc mvc;

    @Mock
    RentalRepository rentalRepository;

    @Mock
    PersonRepository personRepository;

    @Mock
    FilmRepository filmRepository;

    @InjectMocks
    RentalController rentalController;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(rentalController).build();
        when(filmRepository.findById(any())).thenReturn(Optional.of(new Film()));
        when(personRepository.findById(any())).thenReturn(Optional.of(new Person()));
        when(rentalRepository.save(any())).thenReturn(new Rental());
    }

    @Test
    void getRentals() throws Exception {
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.get("/rentals")).andReturn().getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void getRentalsIs404WhenUrlWrong() throws Exception {
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.get("/rental")).andReturn().getResponse();
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

    @Test
    void startRental() throws Exception {
        String requestBody = "[]";
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders
                .post("/start-rental?personId=1")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON)
                ).andReturn().getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void endRental() {
    }
}