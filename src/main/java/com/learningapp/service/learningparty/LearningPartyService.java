package com.learningapp.service.learningparty;

import com.learningapp.data.dto.request.LearningPartySignUpDto;
import com.learningapp.data.dto.response.LearningPartyOutputDto;
import com.learningapp.data.entity.LearningParty;
import com.learningapp.exception.learningparty.LearningPartyAlreadyExistException;
import com.learningapp.exception.learningparty.LearningPartyNotFoundException;

import java.util.List;

public interface LearningPartyService {
    LearningPartyOutputDto addLearningParty(LearningPartySignUpDto learningPartySignUpDto) throws LearningPartyAlreadyExistException;
    LearningPartyOutputDto getById(Long id) throws LearningPartyNotFoundException;
    List<LearningPartyOutputDto> getAll();
}
