package com.learningapp.exception;

import com.learningapp.exception.learningparty.LearningPartyAlreadyExistException;
import com.learningapp.exception.learningparty.LearningPartyNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;


@SuppressWarnings({"unchecked","rawtypes"})
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request){
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse response = new ErrorResponse("Internal Server Error",details);

        return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(LearningPartyNotFoundException.class)
    public final ResponseEntity<Object> learningPartyNotFound(LearningPartyNotFoundException ex, WebRequest request){
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse response = new ErrorResponse("User not Found",details);

        return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }



    @ExceptionHandler(LearningPartyAlreadyExistException.class)
    public final ResponseEntity<Object> TransportCompanyAlreadyExist(LearningPartyAlreadyExistException ex, WebRequest request){
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse response = new ErrorResponse("Learning party already exist",details);

        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(BadRequestException.class)
    public final ResponseEntity<Object> BadRequestException(BadRequestException ex, WebRequest request){
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse response = new ErrorResponse("Request cannot be completed",details);

        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> details = new ArrayList<>();
        for(ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }
        ErrorResponse response = new ErrorResponse("Bad Request", details);
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {NullException.class})
    public final ResponseEntity<Object> handleNullPointerException(NullException ex, WebRequest request) {
        ErrorResponse response = new ErrorResponse();
        response.setMessage ( ex.getMessage () );
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }
}
