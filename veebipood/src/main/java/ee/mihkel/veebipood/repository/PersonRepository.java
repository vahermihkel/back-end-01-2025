package ee.mihkel.veebipood.repository;

import ee.mihkel.veebipood.dto.PersonDTO;
import ee.mihkel.veebipood.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {

//    List<Person> findAll();
}
