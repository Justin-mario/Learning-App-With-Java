package com.learningapp.servicetest;

import com.learningapp.data.dto.request.LearningPartySignUpDto;
import com.learningapp.data.dto.response.LearningPartyOutputDto;
import com.learningapp.data.entity.LearningParty;
import com.learningapp.exception.NullException;
import com.learningapp.exception.learningparty.LearningPartyAlreadyExistException;
import com.learningapp.exception.learningparty.LearningPartyException;
import com.learningapp.exception.learningparty.LearningPartyNotFoundException;
import com.learningapp.repository.LearningPartyRepository;
import com.learningapp.security.config.PasswordConfig;
import com.learningapp.service.learningparty.LearningPartyServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;



@Slf4j
public class LearningPartyTest {

    @InjectMocks
    LearningPartyServiceImpl learningPartyService;

    @Mock
    LearningPartyRepository learningPartyRepository;

    PasswordConfig passwordEncoder = new PasswordConfig ();

    LearningParty learningParty;

    LearningPartySignUpDto signUpDto;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks ( this );
        signUpDto = new LearningPartySignUpDto ();
    }

    @Test()
    public void addLearningPartyTest(){
        signUpDto.setId ( 1L );
        signUpDto.setEmail ( "njchigozie@gmail.com" );
        signUpDto.setPassword ( passwordEncoder.passwordEncoder ().encode ("pass1234@") );

        learningParty = new LearningParty (signUpDto);
        log.info ( "sign up dto ---------------->{}", signUpDto.getEmail () );

        Mockito.when (learningPartyRepository.save ( any (LearningParty.class) )  ).thenReturn ( learningParty);
        LearningPartyOutputDto savedLearningParty = learningPartyService.addLearningParty ( signUpDto );
        verify ( learningPartyRepository, times ( 1 ) ).save ( any (LearningParty.class));
        log.info ( "saved learning party ---------------->{}", savedLearningParty.getEmail () );
        assertEquals ( learningParty.getEmail (), savedLearningParty.getEmail ()  );
    }

//    @Test()
//    public void emailCannotBeRegisteredMoreThanOnceTest(){
//        signUpDto.setId ( 1L );
//        signUpDto.setEmail ( "njchigozie@gmail.com" );
//        signUpDto.setPassword ( passwordEncoder.passwordEncoder ().encode ("pass1234@") );
//
//        learningParty = new LearningParty (signUpDto);
//        log.info ( "sign up dto ---------------->{}", signUpDto.getEmail () );
//        Mockito.when (learningPartyRepository.save ( any (LearningParty.class) )  ).thenReturn ( learningParty);
//        LearningPartyOutputDto savedLearningParty = learningPartyService.addLearningParty ( signUpDto );
//        verify ( learningPartyRepository, times ( 0 ) ).save (learningParty);
//        log.info ( "saved learning party ---------------->{}", savedLearningParty.getEmail () );
//        assertEquals ( learningParty.getEmail (), savedLearningParty.getEmail ()  );
//
//        LearningPartySignUpDto student = new LearningPartySignUpDto ();
//        student.setEmail ( "njchigozie@gmail.com" );
//        student.setPassword ( passwordEncoder.passwordEncoder ().encode ( "pass1234@" ) );
//
//        LearningParty studentLearningParty = new LearningParty (student);
//        Mockito.when (learningPartyRepository.save ( any (LearningParty.class) )  ).thenReturn ( studentLearningParty);
//        assertThrows ( LearningPartyAlreadyExistException.class, ()->  learningPartyService.addLearningParty ( student ));
//        verify ( learningPartyRepository, times ( 1 ) ).save (any (LearningParty.class));
//    }

    @Test()
    public void whenAddingLearningPartyEmailCannotBeNullTest(){
        signUpDto.setId ( 1L );
        signUpDto.setPassword ( passwordEncoder.passwordEncoder ().encode ("pass1234@") );

        learningParty = new LearningParty (signUpDto);
        log.info ( "sign up dto ---------------->{}", signUpDto.getEmail () );

        Mockito.when (learningPartyRepository.save ( any (LearningParty.class) )  ).thenReturn ( learningParty );
        assertThrows ( NullException.class, ()-> learningPartyService.addLearningParty ( signUpDto ));
        verify ( learningPartyRepository, times ( 0 ) ).save (any (LearningParty.class) );


    }

    @Test()
    public void whenAddingLearningPartyEmailCannotBeEmptyTest(){
        signUpDto.setId ( 1L );
        signUpDto.setEmail ( "" );
        signUpDto.setPassword ( passwordEncoder.passwordEncoder ().encode ("pass1234@") );

        learningParty = new LearningParty (signUpDto);
        log.info ( "sign up dto ---------------->{}", signUpDto.getEmail () );

        Mockito.when (learningPartyRepository.save ( any (LearningParty.class) )  ).thenReturn ( learningParty );
        assertThrows ( LearningPartyException.class, ()-> learningPartyService.addLearningParty ( signUpDto ));
        verify ( learningPartyRepository, times ( 0 ) ).save ( any (LearningParty.class) );
    }

    @Test
    public void whenAddingLearningPartyPasswordCannotBeNullTest(){
        signUpDto.setId ( 1L );
        signUpDto.setEmail ( "njchigozie@gmail.com" );
        learningParty = new LearningParty (signUpDto);
        log.info ( "sign up dto ---------------->{}", signUpDto.getEmail () );
        Mockito.when (learningPartyRepository.save ( any (LearningParty.class) )  ).thenReturn ( learningParty );
        assertThrows ( NullException.class, ()-> learningPartyService.addLearningParty ( signUpDto ));
        verify ( learningPartyRepository, times ( 0 ) ).save (any (LearningParty.class) );
    }

    @Test
    public void whenAddingLearningPartyPasswordCannotBeEmptyTest(){
        signUpDto.setId ( 1L );
        signUpDto.setEmail ( "njchigozie@gmail.com" );
        signUpDto.setPassword ("");
        learningParty = new LearningParty (signUpDto);
        log.info ( "sign up dto ---------------->{}", signUpDto.getEmail () );
        Mockito.when (learningPartyRepository.save ( any (LearningParty.class) )  ).thenReturn ( learningParty );
        assertThrows ( LearningPartyException.class, ()-> learningPartyService.addLearningParty ( signUpDto ));
        verify ( learningPartyRepository, times ( 0 ) ).save (any (LearningParty.class) );
    }

    @Test()
    public void getLearningPartyByIdTest(){
        signUpDto.setId ( 1L );
        signUpDto.setEmail ( "njchigozie@gmail.com" );
        signUpDto.setPassword ( passwordEncoder.passwordEncoder ().encode ("pass1234@") );

        learningParty = new LearningParty (signUpDto);
        Mockito.when (learningPartyRepository.save ( any (LearningParty.class) )  ).thenReturn ( learningParty);
        learningPartyService.addLearningParty ( signUpDto );
        verify ( learningPartyRepository, times ( 1 ) ).save ( any (LearningParty.class) );
        Long learningPartyId = 1L;
        Mockito.when ( learningPartyRepository.findById ( 1L ) ).thenReturn ( Optional.of ( learningParty ) );
        LearningPartyOutputDto learningPartyFromRepo = learningPartyService.getById ( learningPartyId );
        verify ( learningPartyRepository, times ( 1 ) ).findById ( 1L );
        assertEquals ( learningPartyId, learningPartyFromRepo.getId () );


    }

    @Test()
    public void throwExceptionWhenLearningPartyIdDoesNotExistTest(){
        signUpDto.setId ( 1L );
        signUpDto.setEmail ( "njchigozie@gmail.com" );
        signUpDto.setPassword ( passwordEncoder.passwordEncoder ().encode ("pass1234@") );

        LearningParty learningParty = new LearningParty (signUpDto);
        Mockito.when (learningPartyRepository.save ( any (LearningParty.class) )  ).thenReturn ( learningParty);
        learningPartyService.addLearningParty ( signUpDto );
        verify ( learningPartyRepository, times ( 1 ) ).save ( any (LearningParty.class) );
        Long learningPartyId = 5L;
        Mockito.when ( learningPartyRepository.findById ( learningPartyId ) ).thenThrow ( new LearningPartyNotFoundException ( "Learning partys with id " +learningPartyId+ " does not exist"  ) );
       assertThrows ( LearningPartyNotFoundException.class, () -> learningPartyService.getById ( learningPartyId ) );
        verify ( learningPartyRepository, times ( 1 ) ).findById ( learningPartyId );

    }

    @Test()
    public void getAllLearningPartyTest(){
        signUpDto.setId ( 1L );
        signUpDto.setEmail ( "njchigozie@gmail.com" );
        signUpDto.setPassword ( passwordEncoder.passwordEncoder ().encode ("pass1234@") );

        LearningPartySignUpDto student = new LearningPartySignUpDto ();
        student.setEmail ( "chigozie@gmail.com" );
        student.setPassword ( passwordEncoder.passwordEncoder ().encode ( "pass1234@" ) );

        learningParty = new LearningParty (signUpDto);
        LearningParty studentLearningParty = new LearningParty (student);

        Mockito.when (learningPartyRepository.save ( any (LearningParty.class) )  ).thenReturn ( learningParty);
        learningPartyService.addLearningParty ( signUpDto );
        Mockito.when (learningPartyRepository.save ( any (LearningParty.class) )  ).thenReturn ( studentLearningParty);
        learningPartyService.addLearningParty ( student );
        verify ( learningPartyRepository, times ( 2 ) ).save ( any (LearningParty.class) );

        List<LearningPartyOutputDto> learningPartyList = learningPartyService.getAll ();
        log.info ( learningPartyList.toString () );
        verify ( learningPartyRepository, times ( 1 ) ).findAll ();
        assertEquals ( 0, learningPartyList.size ());


    }
}
