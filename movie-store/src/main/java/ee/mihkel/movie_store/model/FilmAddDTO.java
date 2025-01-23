package ee.mihkel.movie_store.model;

import ee.mihkel.movie_store.entity.FilmType;
import lombok.Data;

@Data
public class FilmAddDTO {
    private String name;
    private FilmType type;
}
