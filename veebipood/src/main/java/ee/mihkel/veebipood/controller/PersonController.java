package ee.mihkel.veebipood.controller;

import ee.mihkel.veebipood.dto.PersonDTO;
import ee.mihkel.veebipood.entity.Person;
import ee.mihkel.veebipood.entity.PersonRole;
import ee.mihkel.veebipood.model.AuthToken;
import ee.mihkel.veebipood.model.EmailPassword;
import ee.mihkel.veebipood.repository.PersonRepository;
import ee.mihkel.veebipood.service.EmailService;
import ee.mihkel.veebipood.service.PersonService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Autowired
    EmailService emailService;

    @GetMapping("email")
    public String sendEmail() {
        emailService.sendEmail();
        return "Email saadetud";
    }

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

    @GetMapping("persons")
    public Page<Person> getPersons(Pageable pageable) {
        log.info("Fetching all persons");
        return personRepository.findAll(pageable);
    }

    @PatchMapping("person-admin")
    public Page<Person> changePersonAdmin(@RequestParam Long personId, boolean isAdmin, Pageable pageable) {
        Person person = personRepository.findById(personId).orElseThrow();
        if (isAdmin) {
            person.setRole(PersonRole.ADMIN);
        } else {
            person.setRole(PersonRole.CUSTOMER);
        }
        return personRepository.findAll(pageable);
    }
}
