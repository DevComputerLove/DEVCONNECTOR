package io.agileintelligence.ExceptionHandling;

import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public final ResponseEntity<Object>
        handleUserAlreadyExistsException(UserAlreadyExistException ex, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage());
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<?>
    handleUserAlreadyExistsException(InvalidLoginParametersException ex, WebRequest request){
        InvalidLoginResponse exceptionResponse = new InvalidLoginResponse();
        String Json = new Gson().toJson(exceptionResponse);
        return new ResponseEntity(Json, HttpStatus.BAD_REQUEST);
    }



}
