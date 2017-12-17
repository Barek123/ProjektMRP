package pl.marcb.MRP.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class PermissionDeniedException extends Exception{
    public PermissionDeniedException(String message) {
        super(message);
    }
}
