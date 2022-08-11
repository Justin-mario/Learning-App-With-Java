package com.learningapp.service.utils.learningparty;

import com.learningapp.data.dto.response.LearningPartyOutputDto;
import com.learningapp.data.entity.LearningParty;
import com.learningapp.exception.learningparty.LearningPartyAlreadyExistException;
import com.learningapp.exception.learningparty.LearningPartyNotFoundException;
import com.learningapp.repository.LearningPartyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LearningPartyUtils {
    private final LearningPartyRepository learningPartyRepository;

    public void checkIfLearningPartyExistById(String email){
        Optional.ofNullable ( learningPartyRepository.findByEmail ( email ) )
                .orElseThrow ( () -> new LearningPartyAlreadyExistException ( "learning party with email " + email + " already exist" ) ) ;

    }

    public LearningPartyOutputDto checkIfLearningPartyExistById(Long learningPartyId){
        Optional<LearningParty> learningPartyFromRepository = Optional.ofNullable ( learningPartyRepository.findById ( learningPartyId )
                .orElseThrow ( () -> new LearningPartyNotFoundException ( "learning party with id " + learningPartyId + " was not found" ) ) );
        return new LearningPartyOutputDto (learningPartyFromRepository.get ());
    }
}
