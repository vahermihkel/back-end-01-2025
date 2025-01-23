package ee.mihkel.movie_store.repository;

import ee.mihkel.movie_store.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilmRepository extends JpaRepository<Film, Long> {


    List<Film> findByRentalNull();
}
