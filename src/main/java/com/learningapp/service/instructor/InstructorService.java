package com.learningapp.service.instructor;

import com.learningapp.data.dto.request.InstructorInputDto;
import com.learningapp.data.dto.response.InstructorOutputDto;
import com.learningapp.exception.instructor.InstructorAlreadyExistException;
import com.learningapp.exception.instructor.InstructorNotFoundException;

public interface InstructorService {
    InstructorOutputDto addInstructor(InstructorInputDto instructorInputDto)throws InstructorAlreadyExistException;
    InstructorOutputDto findById(Long instructorId)throws InstructorNotFoundException;
    void deleteById(Long instructorId)throws InstructorNotFoundException;
}
