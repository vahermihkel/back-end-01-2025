package ee.mihkel.veebipood.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Setter
@Getter
public class ErrorMessage {
    private Date timestamp;
    private int status;
    private String error;
//    private String path;


    //    public String getPath() {
//        return path;
//    }
//
//    public void setPath(String path) {
//        this.path = path;
//    }
}


//{
//        "timestamp": "2025-01-08T10:50:23.769+00:00",
//        "status": 500,
//        "error": "Internal Server Error",
//        "path": "/products"
//        }