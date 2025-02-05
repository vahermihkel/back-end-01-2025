package ee.mihkel.veebipood.service;

import ee.mihkel.veebipood.dto.PersonDTO;
import ee.mihkel.veebipood.entity.Person;
import ee.mihkel.veebipood.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    ModelMapper modelMapper;

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
}
