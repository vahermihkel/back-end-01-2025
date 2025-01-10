package ee.mihkel.veebipood.exception;

import java.util.Date;


public class ErrorMessage {
    private Date timestamp;
    private int status;
    private String error;
//    private String path;


    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

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