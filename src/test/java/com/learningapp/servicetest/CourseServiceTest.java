package com.learningapp.servicetest;

import com.learningapp.data.entity.Course;
import com.learningapp.service.course.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Course.class)
@Slf4j
public class CourseServiceTest {

    @MockBean
    CourseService courseService;

    @Test
    void addCourseTest(){

    }
}
