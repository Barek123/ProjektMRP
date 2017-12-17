package pl.marcb.MRP.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BusinessEntityNotFoundException extends Exception {
    public BusinessEntityNotFoundException(String message) {
        super(message);
    }
}
