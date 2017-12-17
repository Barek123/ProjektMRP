package pl.marcb.MRP.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class LogicException extends Exception {
    public LogicException(String message) {
        super(message);
    }
}
