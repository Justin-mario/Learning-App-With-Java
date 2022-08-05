package com.learningapp.data.dto.response;

import com.learningapp.data.entity.Course;
import com.learningapp.data.entity.Instructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InstructorOutputDto {

    private String firstName;

    private String lastName;

    private String gender;

    private String specialization;

    private String bio;

    private List<Course> courses;

    public InstructorOutputDto(Instructor instructor){
        firstName = instructor.getFirstName ();
        lastName = instructor.getLastName ();
        gender = String.valueOf ( instructor.getGender () );
        specialization = instructor.getSpecialization ();
        bio = instructor.getBio ();
        courses = instructor.getCourses ();
    }

}
