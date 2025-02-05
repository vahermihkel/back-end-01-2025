package ee.mihkel.veebipood.controller;

import ee.mihkel.veebipood.dto.PersonDTO;
import ee.mihkel.veebipood.entity.Person;
import ee.mihkel.veebipood.repository.PersonRepository;
import ee.mihkel.veebipood.service.PersonService;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@Log4j2
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    PersonService personService;

    @PostMapping("login")
    public String login() {
        log.info("Trying to log in...");
        return "";
    }

    @PostMapping("signup")
    public String signup(@RequestBody Person person) {
        log.info("Trying to sign up... ID: {}", person.getId());
        personRepository.save(person);
        return "";
    }

    @GetMapping("public-persons")
    public List<PersonDTO> getPublicPersons() {
        log.info("Fetching public persons");
        return personService.getPersonDTOs();
    }
}
