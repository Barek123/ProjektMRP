package pl.marcb.MRP.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class OperationNotAllowedException extends Exception{
    public OperationNotAllowedException(String message) {
        super(message);
    }
}
