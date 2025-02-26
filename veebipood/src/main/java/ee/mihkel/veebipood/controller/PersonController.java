package ee.mihkel.veebipood.controller;

import ee.mihkel.veebipood.dto.PersonDTO;
import ee.mihkel.veebipood.entity.Person;
import ee.mihkel.veebipood.model.AuthToken;
import ee.mihkel.veebipood.model.EmailPassword;
import ee.mihkel.veebipood.repository.PersonRepository;
import ee.mihkel.veebipood.service.PersonService;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    ModelMapper modelMapper;

    @PostMapping("login")
    public AuthToken login(@RequestBody EmailPassword emailPassword) {
        log.info("Trying to log in...");
        return personService.getToken(emailPassword);
    }

    @PostMapping("signup")
    public PersonDTO signup(@RequestBody Person person) {
        log.info("Trying to sign up... ID: {}", person.getId());
        return modelMapper.map(personRepository.save(person), PersonDTO.class);
    }

    @GetMapping("public-persons")
    public List<PersonDTO> getPublicPersons() {
        log.info("Fetching public persons");
        return personService.getPersonDTOs();
    }

    // localhost:8080/person
    @GetMapping("person")
    public Person getPerson() {
        log.info("Fetching person");
        Long id = Long.parseLong(
                SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString()
        );
//        return personService.getPersonByToken(id);
        return personRepository.findById(id).orElseThrow();
    }

    @PutMapping("person")
    public Person editPerson(@RequestBody Person person) {
        return personRepository.save(person);
    }
}
