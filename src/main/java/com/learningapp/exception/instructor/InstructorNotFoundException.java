package com.learningapp.exception.instructor;

public class InstructorNotFoundException extends RuntimeException {
    public InstructorNotFoundException(String message){
        super(message);
    }
}
