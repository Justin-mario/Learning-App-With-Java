package com.learningapp.data.dto.response;

import com.learningapp.data.entity.LearningParty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LearningPartyOutputDto {
    private Long id;

    private String email;

    public LearningPartyOutputDto(LearningParty learningParty){
        id = learningParty.getId ();
        email = learningParty.getEmail ();
    }
}
