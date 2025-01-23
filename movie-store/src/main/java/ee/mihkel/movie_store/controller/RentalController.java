package ee.mihkel.movie_store.controller;

import ee.mihkel.movie_store.entity.Film;
import ee.mihkel.movie_store.entity.Person;
import ee.mihkel.movie_store.entity.Rental;
import ee.mihkel.movie_store.model.FilmRentalDTO;
import ee.mihkel.movie_store.repository.FilmRepository;
import ee.mihkel.movie_store.repository.PersonRepository;
import ee.mihkel.movie_store.repository.RentalRepository;
import ee.mihkel.movie_store.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RentalController {

    @Autowired
    RentalRepository rentalRepository;

    @Autowired
    RentalService rentalService;

    @Autowired
    PersonRepository personRepository;

    @GetMapping("rentals")
    public List<Rental> getRentals() {
        return rentalRepository.findAll();
    }

    @PostMapping("start-rental")
    public List<Rental> startRental(
            @RequestBody List<FilmRentalDTO> films,
            @RequestParam Long personId,
            @RequestParam(required = false, defaultValue = "0") int bonusDays) {
        // 2. ära luba laenutada filme, mis on juba laenutatud
        Person person = personRepository.findById(personId).orElseThrow();
        rentalService.checkIfAllAvailable(films, person, bonusDays);
        // loon rentali
        rentalService.saveRental(films, person, bonusDays);
        return rentalRepository.findAll();
    }

    @PostMapping("end-rental")
    public List<Rental> endRental(@RequestBody List<FilmRentalDTO> films) {
        rentalService.calculateLateFee(films);
        return rentalRepository.findAll();
    }
}
