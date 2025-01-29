package ee.mihkel.card_game.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> handleException(RuntimeException e) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setDate(new Date());
        errorMessage.setStatusCode(400);
        errorMessage.setMessage(e.getMessage());
        return ResponseEntity.badRequest().body(errorMessage);
    }
}
