package com.learningapp.service.course;

import com.learningapp.data.dto.request.CourseInputDto;
import com.learningapp.data.dto.response.CourseOutputDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CourseServiceImpl implements CourseService{
    @Override
    public CourseOutputDto addCourse(CourseInputDto courseInputDto) {
        return null;
    }

    @Override
    public CourseOutputDto updateCourse(Long courseId, CourseOutputDto courseOutputDto) {
        return null;
    }

    @Override
    public CourseOutputDto findById(Long courseId) {
        return null;
    }

    @Override
    public void deleteById(Long courseId) {

    }

    @Override
    public List<CourseOutputDto> findAllCourses(String title, int numberOfPage) {
        return null;
    }
}
