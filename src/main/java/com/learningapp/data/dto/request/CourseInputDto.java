package com.learningapp.data.dto.request;

import com.learningapp.data.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseInputDto {
    private Long id;

    private String title;

    private String description;

    private String language;

    private List<String> imageUrls;


    public CourseInputDto(Course course){
        id = course.getId ();
        title = course.getTitle ();
        description = course.getDescription ();
        language = course.getLanguage ();
        imageUrls = course.getImageUrls ();
    }

}
