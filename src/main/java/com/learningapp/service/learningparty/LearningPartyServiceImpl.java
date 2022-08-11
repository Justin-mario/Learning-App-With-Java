package com.learningapp.service.learningparty;

import com.learningapp.data.dto.request.LearningPartySignUpDto;
import com.learningapp.data.dto.response.LearningPartyOutputDto;
import com.learningapp.data.entity.LearningParty;
import com.learningapp.exception.NullException;
import com.learningapp.exception.learningparty.LearningPartyAlreadyExistException;
import com.learningapp.exception.learningparty.LearningPartyException;
import com.learningapp.exception.learningparty.LearningPartyNotFoundException;
import com.learningapp.repository.LearningPartyRepository;
import com.learningapp.service.utils.learningparty.LearningPartyUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Slf4j
@RequiredArgsConstructor
public class LearningPartyServiceImpl implements LearningPartyService{

    private final LearningPartyRepository learningPartyRepository;


    @Override
    public LearningPartyOutputDto addLearningParty(LearningPartySignUpDto learningPartySignUpDto) throws LearningPartyAlreadyExistException {
       checkIfLearningPartyExistById ( learningPartySignUpDto.getEmail () );
       validateLearningPartySignUpDto ( learningPartySignUpDto );
       LearningParty learningParty = new LearningParty (learningPartySignUpDto);
        learningPartyRepository.save ( learningParty );
       return new LearningPartyOutputDto (learningParty);
    }

    @Override
    public List<LearningPartyOutputDto> getAll() {
        return learningPartyRepository.findAll ()
                .stream ().map ( LearningPartyOutputDto::new )
                .collect( Collectors.toList());
    }

    @Override
    public LearningPartyOutputDto getById(Long learningPartyId) throws LearningPartyNotFoundException {
        return checkIfLearningPartyExistById ( learningPartyId );
    }

    private void checkIfLearningPartyExistById(String email){
        Optional.ofNullable ( learningPartyRepository.findByEmail ( email ) )
                .orElseThrow ( () -> new LearningPartyAlreadyExistException ( "learning party with email " + email + " already exist" ) ) ;

    }

    private LearningPartyOutputDto checkIfLearningPartyExistById(Long learningPartyId){
        Optional<LearningParty> learningPartyFromRepository = Optional.ofNullable ( learningPartyRepository.findById ( learningPartyId )
                .orElseThrow ( () -> new LearningPartyNotFoundException ( "learning party with id " + learningPartyId + " was not found" ) ) );
        return new LearningPartyOutputDto (learningPartyFromRepository.get ());
    }

    private void validateLearningPartySignUpDto(LearningPartySignUpDto signUpDto){
        if (signUpDto.getEmail () == null){
            throw new NullException ("email cannot be null");
        }
        if (signUpDto.getEmail ().isBlank () || signUpDto.getEmail ().isBlank ()){
            throw new LearningPartyException ( "email cannot be empty" );
        }

        if (signUpDto.getPassword () == null){
            throw new NullException ( "password cannot be empty" );
        }
        if (signUpDto.getPassword ().isEmpty () || signUpDto.getPassword ().isBlank ()){
            throw new LearningPartyException ( "password cannot be empty" );
        }
    }

}
