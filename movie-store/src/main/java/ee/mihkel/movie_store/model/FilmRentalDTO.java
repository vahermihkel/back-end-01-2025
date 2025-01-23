package ee.mihkel.movie_store.model;

import lombok.Data;

// DTO --> Data Transfer Object
@Data
public class FilmRentalDTO {
    private Long id;
    private int days;
}
