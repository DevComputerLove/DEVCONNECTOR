package io.agileintelligence.ExceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidLoginParametersException extends UsernameNotFoundException {

    public InvalidLoginParametersException(String message) {
        super(message);
    }


}
