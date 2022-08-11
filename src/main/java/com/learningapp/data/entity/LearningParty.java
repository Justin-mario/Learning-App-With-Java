package com.learningapp.data.entity;

import com.learningapp.data.dto.request.LearningPartySignUpDto;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Set;

import static javax.persistence.FetchType.EAGER;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class LearningParty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull @NotEmpty(message = "Email cannot be empty")
    @NotBlank @Email(message = "Email is not valid", regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
    @Column(unique = true, nullable = false)
    private String email;

    @NotNull @NotEmpty @NotBlank
    @Column(nullable = false)
    @JsonIgnore
    private String password;

    @ManyToMany(fetch = EAGER)
    private Set<LearningPartyRole> roles;

    @CreationTimestamp
    private LocalDate dateCreated;

    private Boolean isEnabled = Boolean.FALSE;

    public LearningParty(Long id, String email, String password){
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public LearningParty(LearningPartySignUpDto learningPartySignUpDto){
        id = learningPartySignUpDto.getId ();
        email= learningPartySignUpDto.getEmail ();
        password = learningPartySignUpDto.getPassword ();
    }

}
