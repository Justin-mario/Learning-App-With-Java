package com.learningapp.exception.learningparty;

public class LearningPartyAlreadyExistException extends RuntimeException {
    public LearningPartyAlreadyExistException(String message){
        super(message);
    }
}
