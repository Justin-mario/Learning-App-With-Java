package com.learningapp.data.dto.response;

import com.learningapp.data.entity.Course;
import com.learningapp.data.entity.Instructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CourseOutputDto {
    private String title;

    private String description;

    private String language;

    private List<String> imageUrls;

    private LocalDate dateCreated;

    private LocalDateTime datePublished;

    private LocalDateTime updatedAt;

    private Boolean isPublished;

    private InstructorOutputDto instructor;


    public CourseOutputDto(Course course){
        title = course.getTitle ();
        description = course.getDescription ();
        language = course.getLanguage ();
        imageUrls = course.getImageUrls ();
        dateCreated = course.getDateCreated ();
        datePublished = course.getDatePublished ();
        isPublished = course.getIsPublished ();
        updatedAt = course.getUpdatedAt ();
        instructor = getInstructor (course.getInstructor ());
    }

    private InstructorOutputDto getInstructor(Instructor instructor){
        return new InstructorOutputDto (instructor);
    }
}
