package ee.mihkel.veebipood.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice // automaatselt kõikidele kontrolleritele, kui juhtub exception, siis vahetab
// mudeli meie mudeli vastu
public class ExceptionCatcher {

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> handleException(RuntimeException e) {

        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setTimestamp(new Date());
        errorMessage.setStatus(400);
        // throw new RuntimeException("Sellise ID-ga on olemas")
        errorMessage.setError(e.getMessage());
//        errorMessage.setPath(e.toString());
        return ResponseEntity.status(400).body(errorMessage);
    }
}
