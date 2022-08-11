package com.learningapp.exception.instructor;

public class InstructorAlreadyExistException extends RuntimeException {
    public InstructorAlreadyExistException(String message){
        super(message);
    }
}
