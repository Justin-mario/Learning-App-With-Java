package com.learningapp.data.entity;

import com.learningapp.data.data_enum.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.util.List;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Instructor {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotNull @NotBlank @NotEmpty
    @Column(nullable = false)
    private String firstName;

    @NotNull @NotBlank @NotEmpty
    @Column(nullable = false)
    private String lastName;

    @Enumerated(STRING)
    private Gender gender;

    private String specialization;

    @Column(length = 1000)
    private String bio;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private LearningParty learningParty;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<Course> courses;
}
