package ee.mihkel.movie_store.repository;

import ee.mihkel.movie_store.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Long> {
}
