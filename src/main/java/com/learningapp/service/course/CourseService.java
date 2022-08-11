package com.learningapp.service.course;

import com.learningapp.data.dto.request.CourseInputDto;
import com.learningapp.data.dto.response.CourseOutputDto;
import org.springframework.data.domain.Page;

import java.util.List;


public interface CourseService {
    CourseOutputDto addCourse(CourseInputDto courseInputDto);
    CourseOutputDto updateCourse(Long courseId, CourseOutputDto courseOutputDto);
    CourseOutputDto findById(Long courseId);
    void deleteById(Long courseId);
    List<CourseOutputDto> findAllCourses(String title, int numberOfPage);
}
