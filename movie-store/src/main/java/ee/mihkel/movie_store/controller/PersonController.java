package ee.mihkel.movie_store.controller;

import ee.mihkel.movie_store.entity.Person;
import ee.mihkel.movie_store.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    @PostMapping("persons")
    public List<Person> addPerson(@RequestBody Person person) {
        person.setBonusPoints(0);
        personRepository.save(person);
        return personRepository.findAll();
    }

    @DeleteMapping("persons")
    public List<Person> deletePerson(@RequestParam Long personId) {
        personRepository.deleteById(personId);
        return personRepository.findAll();
    }

    @GetMapping("persons")
    public List<Person> getPersons() {
        return personRepository.findAll();
    }

    @GetMapping("bonus-points")
    public int getBonusPoints(@RequestParam Long id) {
        return personRepository.findById(id).orElseThrow().getBonusPoints();
    }
}
