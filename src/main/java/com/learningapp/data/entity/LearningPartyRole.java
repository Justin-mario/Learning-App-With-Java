package com.learningapp.data.entity;

import com.learningapp.data.data_enum.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LearningPartyRole {
    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(unique = true)
    @Enumerated(value = STRING)
    private Role role;

    public LearningPartyRole(Role role){
        this.role = role;
    }
}
