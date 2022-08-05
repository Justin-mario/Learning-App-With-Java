package com.learningapp.data.dto.request;


import com.learningapp.data.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentInputDto {
    private Long id;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String dateOfBirth;

    private String gender;

    public StudentInputDto(Student student){
        id = student.getId ();
        firstName = student.getFirstName ();
        lastName = student.getLastName ();
        phoneNumber = student.getPhoneNumber ();
        dateOfBirth = String.valueOf ( student.getDateOfBirth () );
        gender = String.valueOf ( student.getGender () );
    }
}
