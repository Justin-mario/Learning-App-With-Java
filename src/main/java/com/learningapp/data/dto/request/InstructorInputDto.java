package com.learningapp.data.dto.request;

import com.learningapp.data.data_enum.Gender;
import com.learningapp.data.entity.Instructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InstructorInputDto {
    private Long id;

    private String firstName;

    private String lastName;

    private String gender;

    private String specialization;

    private String bio;

    public InstructorInputDto(Instructor instructor){
        id = instructor.getId ();
        firstName = instructor.getFirstName ();
        lastName = instructor.getLastName ();
        gender = String.valueOf ( instructor.getGender () );
        specialization = instructor.getSpecialization ();
        bio = instructor.getBio ();
    }
}
