package com.learningapp.data.entity;

import com.learningapp.data.data_enum.Gender;
import com.learningapp.data.dto.request.StudentInputDto;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull @NotEmpty @NotBlank
    private String firstName;

    @NotNull @NotEmpty @NotBlank
    private String lastName;

    private String phoneNumber;

    private LocalDate dateOfBirth;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private LearningParty learningParty;


    @ManyToMany(cascade = CascadeType.PERSIST)
    @ToString.Exclude
    private List<Course> enrolledCourse;

    public Student(StudentInputDto studentInputDto){
        id = studentInputDto.getId ();
        firstName = studentInputDto.getFirstName ();
        lastName = studentInputDto.getLastName ();
        dateOfBirth = LocalDate.parse ( studentInputDto.getDateOfBirth () );
        gender = Gender.valueOf ( studentInputDto.getGender () );
    }
}
