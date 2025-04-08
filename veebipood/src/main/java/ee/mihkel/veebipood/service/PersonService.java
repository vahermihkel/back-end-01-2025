package ee.mihkel.veebipood.service;

import ee.mihkel.veebipood.dto.PersonDTO;
import ee.mihkel.veebipood.entity.Person;
import ee.mihkel.veebipood.model.AuthToken;
import ee.mihkel.veebipood.model.EmailPassword;
import ee.mihkel.veebipood.repository.PersonRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    ModelMapper modelMapper;

    String superSecretKey = "7shkEZCBEW2ufZvrCiijn_o2eOMAzJaQX88Ej9TMm_s";

//    public List<PersonDTO> getPersonDTOs2() {
//        List<Person> persons = personRepository.findAll();
//        List<PersonDTO> personDTOs = new ArrayList<>();
//        for (Person p: persons) {
//            PersonDTO person = new PersonDTO();
//            person.setFirstName(p.getFirstName());
//            person.setLastName(p.getLastName());
//            personDTOs.add(person);
//        }
//        return personDTOs;
//    }

    public List<PersonDTO> getPersonDTOs() {
//        ModelMapper modelMapper = new ModelMapper();
        System.out.println(modelMapper);
        return List.of(modelMapper.map(personRepository.findAll(), PersonDTO[].class));
    }

    public AuthToken getToken(EmailPassword emailPassword) {
        Person person = personRepository.findByEmail(emailPassword.getEmail());
        if (person == null) {
            throw new RuntimeException("ERROR_EMAIL_NOT_FOUND");
        }
        if (!person.getPassword().equals(emailPassword.getPassword())) {
            throw new RuntimeException("ERROR_WRONG_PASSWORD");
        }
        AuthToken authToken = new AuthToken();

        Map<String, String> payload = new HashMap<>();
        payload.put("id", person.getId().toString());
        payload.put("email", person.getEmail());
        payload.put("role", person.getRole().toString());

        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(superSecretKey));

        String token = Jwts
                .builder()
                .claims(payload)
                .signWith(secretKey)
                .compact();
        authToken.setToken(token);
        return authToken;
    }

//    public Person getPersonByToken(String token) {
//        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(superSecretKey));
//
//        Claims payLoad = Jwts
//                .parser()
//                .verifyWith(secretKey)
//                .build()
//                .parseSignedClaims(token)
//                .getPayload();
//
//        Long id = Long.parseLong(payLoad.get("id").toString());
//        return personRepository.findById(id).orElseThrow();
//    }
}
