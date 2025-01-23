package ee.mihkel.movie_store.controller;

import ee.mihkel.movie_store.entity.Film;
import ee.mihkel.movie_store.entity.FilmType;
import ee.mihkel.movie_store.model.FilmAddDTO;
import ee.mihkel.movie_store.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FilmController {

    @Autowired
    FilmRepository filmRepository;

    @PostMapping("films")
    public List<Film> addFilm(@RequestBody FilmAddDTO filmDTO) {
        Film film = new Film(); // {id: 0, name: "", type: null, days: 0, rental: null}
//        film.setRental(null);
//        film.setDaysRented(0);
        film.setName(filmDTO.getName());
        film.setType(filmDTO.getType());
        filmRepository.save(film);
        return filmRepository.findAll();
    }

    @DeleteMapping("films")
    public List<Film> deleteFilm(@RequestParam Long filmId) {
        filmRepository.deleteById(filmId);
        return filmRepository.findAll();
    }

    @PatchMapping("film-type")
    public void changeType(@RequestParam Long filmId, @RequestParam FilmType newType) {
        Film film = filmRepository.getReferenceById(filmId);
        film.setType(newType);
        filmRepository.save(film);
    }

    @GetMapping("films")
    public List<Film> getFilms() {
        return filmRepository.findAll();
    }

    @GetMapping("available-films")
    public List<Film> getAvailableFilms() {
        return filmRepository.findByRentalNull();
    }
}
