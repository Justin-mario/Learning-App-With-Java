package com.learningapp.data.dto.request;

import com.learningapp.data.entity.LearningParty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LearningPartySignUpDto {
    private Long id;

    private String email;

    private String password;

    public LearningPartySignUpDto(LearningParty learningParty){
        id = learningParty.getId ();
        email = learningParty.getEmail ();
    }
}
