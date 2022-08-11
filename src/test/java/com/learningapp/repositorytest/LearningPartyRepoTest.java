package com.learningapp.repositorytest;

import com.learningapp.LearningPartyJpaConfig;
import com.learningapp.data.entity.LearningParty;
import com.learningapp.repository.LearningPartyRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import javax.annotation.Resource;
import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {
        LearningParty.class,
        LearningPartyJpaConfig.class})
@Transactional
@Slf4j
@ActiveProfiles("test")
public class LearningPartyRepoTest {

    @Resource
    private LearningPartyRepository learningPartyRepository;

    @Autowired
    PasswordEncoder encoder;

    @BeforeEach
    void setUp(){

    }

    @Test
    public void givenLearningParty_whenSave_thenGetOk() {
        LearningParty learningParty = new LearningParty (1L, "njchigozie@gmail.com", encoder.encode ("pass1234@"));
        learningPartyRepository.save(learningParty);

        Optional<LearningParty> learningParty2 = learningPartyRepository.findById ( 1L );
        assertEquals("njchigozie@gmail.com", learningParty2.get ().getEmail ());
    }

    @Test
    public void givenLearningParty_getAllSavedLearningParty() {
        LearningParty studentLearningParty = new LearningParty (1L, "njchigozie@gmail.com", encoder.encode ("pass1234@"));
        LearningParty instructorLearningParty = new LearningParty (2L, "chigozie@gmail.com", encoder.encode ("pass1234@"));

        List<LearningParty> allLearningParty = new ArrayList<> ();
        allLearningParty.add ( studentLearningParty );
        allLearningParty.add ( instructorLearningParty );
        learningPartyRepository.saveAll ( allLearningParty );

        assertEquals(2, learningPartyRepository.findAll ().size () );
    }
    @Rollback(value = false)
    @Test
    public void givenLearningParty_whenDelete_thenGetOk() {
        LearningParty learningParty = new LearningParty (1L, "njchigozie@gmail.com", encoder.encode ("pass1234@"));
        learningPartyRepository.save(learningParty);
        learningPartyRepository.deleteById ( 1L );
        assertNull ( learningParty);
    }

}
