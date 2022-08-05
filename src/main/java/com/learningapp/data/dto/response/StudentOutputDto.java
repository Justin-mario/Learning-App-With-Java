package com.learningapp.data.dto.response;

import com.learningapp.data.entity.Course;
import com.learningapp.data.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentOutputDto {
    private Long id;

    private String firstName;

    private String lastName;

    private String dateOfBirth;

    private String phoneNumber;

    private String gender;

    private List<Course> courseList;

    public StudentOutputDto(Student student){
        firstName = student.getFirstName ();
        lastName = student.getLastName ();
        phoneNumber = student.getPhoneNumber ();
        dateOfBirth = String.valueOf ( student.getDateOfBirth () );
        gender = String.valueOf ( student.getGender () );
        courseList = student.getEnrolledCourse ();
    }
}
