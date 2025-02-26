package ee.mihkel.movie_store.controller;

import ee.mihkel.movie_store.entity.Film;
import ee.mihkel.movie_store.entity.Person;
import ee.mihkel.movie_store.entity.Rental;
import ee.mihkel.movie_store.repository.FilmRepository;
import ee.mihkel.movie_store.repository.PersonRepository;
import ee.mihkel.movie_store.repository.RentalRepository;
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
class FilmControllerTest {

    private MockMvc mvc;

//    @Mock
//    RentalRepository rentalRepository;
//
//    @Mock
//    PersonRepository personRepository;

    @Mock
    FilmRepository filmRepository;

    @InjectMocks
    FilmController filmController;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(filmController).build();
//        when(filmRepository.findById(any())).thenReturn(Optional.of(new Film()));
//        when(personRepository.findById(any())).thenReturn(Optional.of(new Person()));
//        when(rentalRepository.save(any())).thenReturn(new Rental());
    }

    @Test
    void getFilms() throws Exception {
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders
                .get("/films")).andReturn().getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void addFilm() throws Exception {
        String requestBody = "{}";
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders
                .post("/films")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void deleteFilm() throws Exception {
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders
                .delete("/films?filmId=1")).andReturn().getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void changeType() throws Exception {
        when(filmRepository.getReferenceById(any())).thenReturn(new Film());
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders
                .patch("/film-type?filmId=1&newType=OLD")).andReturn().getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void changeTypeError() throws Exception {
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders
                .patch("/film-type?filmId=1&newType=BLABLA")).andReturn().getResponse();
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @Test
    void getAvailableFilms() throws Exception {
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders
                .get("/available-films")).andReturn().getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }
}