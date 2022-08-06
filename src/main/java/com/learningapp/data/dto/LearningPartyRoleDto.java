package com.learningapp.data.dto;

import com.learningapp.data.entity.LearningPartyRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LearningPartyRoleDto {
    private String role;

    public LearningPartyRoleDto(LearningPartyRole learningPartyRole){
        role = learningPartyRole.getRole ().name ();
    }
}
