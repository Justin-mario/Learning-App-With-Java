package com.learningapp.data.dto;


import com.learningapp.data.entity.LearningParty;
import com.learningapp.data.entity.LearningPartyRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LearningPartyDto {
    private String email;

    private Set<LearningPartyRoleDto> roles;

    public LearningPartyDto(LearningParty learningParty){
        email = learningParty.getEmail ();
        roles = getRoles ( learningParty.getRoles () );
    }

    private Set<LearningPartyRoleDto> getRoles(Set<LearningPartyRole> learningPartyRole){
        Set<LearningPartyRoleDto> learningPartyRoles = new HashSet<> ();
        learningPartyRole.forEach ( role ->learningPartyRoles.add (new LearningPartyRoleDto (role) ) );
        return learningPartyRoles;
    }
}
