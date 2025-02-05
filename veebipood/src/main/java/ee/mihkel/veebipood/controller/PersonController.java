package ee.mihkel.veebipood.controller;

import ee.mihkel.veebipood.entity.Person;
import ee.mihkel.veebipood.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    @PostMapping("login")
    public String login() {
        return "";
    }

    @PostMapping("signup")
    public String signup(@RequestBody Person person) {
        personRepository.save(person);
        return "";
    }
}
