package ee.mihkel.movie_store.model;

import lombok.Data;

import java.util.Date;

@Data
public class ErrorMessage {
    private Date timestamp;
    private int status;
    private String error;
}
