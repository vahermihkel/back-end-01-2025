package ee.mihkel.movie_store.repository;

import ee.mihkel.movie_store.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
